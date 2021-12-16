
package kr.or.ddit.simpleMappers;

import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.text.CaseUtils;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class SqlMapperAndDataMapper implements SqlMapClient{
	private Properties sqlMap;
	
	public SqlMapperAndDataMapper(String xmlPath) {
		sqlMap = new Properties();
//		String xmlPath = "/kr/or/ddit/simpleMappers/Member.xml";
		try(
			InputStream in = MemberDAOImpl.class.getResourceAsStream(xmlPath);
		){
			sqlMap.loadFromXML(in);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
//	INSERT INTO member (
//	   		mem_id, mem_pass, mem_name,
//	    	mem_regno1, mem_regno2, mem_bir,
//	    	mem_zip, mem_add1, mem_add2,
//	    	mem_hometel, mem_comtel, mem_hp,
//	    	mem_mail, mem_job, mem_like,
//	    	mem_memorial, mem_memorialday, mem_mileage
//			) VALUES (
//	   		#mem_id#, #mem_pass#, #mem_name#,
//	    	#mem_regno1#, #mem_regno2#, #mem_bir#,
//	    	#mem_zip#, #mem_add1#, #mem_add2#,
//	    	#mem_hometel#, #mem_comtel#, #mem_hp#,
//	    	#mem_mail#, #mem_job#, #mem_like#,
//	    	#mem_memorial#, #mem_memorialday#, 3000
//			)
	public static List<String> parseRealQuery(StringBuffer query){
		List<String> inlineParameterNames = new ArrayList<String>();
		StringBuffer parsedQuery = new StringBuffer();
		
		Pattern regex = Pattern.compile("#(\\w+)#");
		Matcher matcher = regex.matcher(query);
		while(matcher.find()) {
			String paramName = matcher.group(1);//memId
			inlineParameterNames.add(paramName);
			matcher.appendReplacement(parsedQuery, "?");
		}
		matcher.appendTail(parsedQuery);
		
		query.delete(0, query.length());
		query.append(parsedQuery);
		
		return inlineParameterNames;
	}
	
	
	public PreparedStatement generateQueryStatment(Connection conn, String queryId, Object paramObj) throws SQLException {
		String sql = sqlMap.getProperty(queryId);
		StringBuffer query = new StringBuffer(sql);
		List<String> inlineParameterNames = parseRealQuery(query);
		
		PreparedStatement pstmt = conn.prepareStatement(query.toString());
		
		if (inlineParameterNames.size()>1) {//이름도 넣기
//			pstmt.setString(1, member.getMemId());//인라인파라미터 갯수만큼 나와야한다.
			Class paramObjType = paramObj.getClass();
			for (int i = 0; i < inlineParameterNames.size(); i++) {
				try {
					String propName = inlineParameterNames.get(i);
					PropertyDescriptor pd = new PropertyDescriptor(propName, paramObjType);
					Method getter = pd.getReadMethod();//getter(읽어오기)
					Object paramValue = getter.invoke(paramObj);
					if (Integer.class.equals(getter.getReturnType())) {
						pstmt.setInt(i+1, (Integer) paramValue);
				}else{
					pstmt.setString(i+1, (String) paramValue);
				}//if end
				}catch (Exception e) {
					throw new SQLException(e);
				}//try end
				
			}// for end
			
		}else if(inlineParameterNames.size()==1){//값만 넣기
			if(Integer.class.equals(paramObj.getClass())) {
				pstmt.setInt(1, (Integer) paramObj);
			}else {
				pstmt.setString(1, (String) paramObj);
			}
		}
		
		
		
		return pstmt;
	}
	
	public String[] getColumnNames(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int size = rsmd.getColumnCount();
		String[] columnNames = new String[size];
		for (int i = 0; i < columnNames.length; i++) {
			columnNames[i] = rsmd.getColumnName(i+1);
			
		}
		
		return columnNames;
		
	}
	
	/**
	 * resultset의 테이블을 자바 오브젝트로 바인딩.
	 * @param rs
	 * @param resultType
	 * @return
	 * @throws SQLException
	 */
	public Object dataMapping(ResultSet rs, Class resultType) throws SQLException {
		Object result = null;
		
			try {
//			member = new MemberVO();
				result = resultType.newInstance();
				String[] columnNames = getColumnNames(rs);
				for(String snake : columnNames) {
					String propName = CaseUtils.toCamelCase(snake, false, '_');
					PropertyDescriptor pd = new PropertyDescriptor(propName, resultType);
//			member.setMEMiD(rs.getString("mem_id"));
					Method setter = pd.getWriteMethod();
					Class propType = pd.getPropertyType(); //전역변수의 타입
					if (Integer.class.equals(propType)) {
						setter.invoke(result, rs.getInt(snake));
					}else {
						setter.invoke(result, rs.getString(snake));
					}
					
					
				}
			} catch (Exception e) {
				throw new SQLException(e);
			}
		
		
		return result;
	}

	@Override
	public List queryForList(String queryId, Class resultType) {
		return queryForList(queryId, resultType, null);
	}

	@Override
	public List queryForList(String queryId, Class resultType, Object paramObj) {
		List list = new ArrayList<>();
		
		try(
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stmt = generateQueryStatment(conn, queryId, paramObj);
			){
				ResultSet rs = stmt.executeQuery();
				System.out.println(rs);
				MemberVO vo = null;
				while (rs.next()) {
					Object result = dataMapping(rs, resultType);
					list.add(result);
				}
			
			return list;
		}catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public Object queryForObject(String queryId, Class resultType) {
		
		return queryForObject(queryId, resultType, null);
	}

	@Override
	public Object queryForObject(String queryId, Class resultType, Object paramObj) {
		Object obj = null;
	      try(
	         Connection conn = ConnectionFactory.getConnection();
	         PreparedStatement pstmt = generateQueryStatment(conn, queryId, paramObj);
	      ){
	         
	         ResultSet rs = pstmt.executeQuery();
	         if(rs.next()) {
	            obj = dataMapping(rs, resultType);
	         }
	         // 2.조회한 데이터에 따라 그 데이터를 바인드하는 result type vo 가 달라짐. --> data mapper
	      
	         return obj;
	      } catch (SQLException e) {
	         throw new RuntimeException(e);
	      }
	}

	@Override
	public int insert(String queryId, Object paramObj) {
		
		try(
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = generateQueryStatment(conn, queryId, paramObj);
				){
			
			return pstmt.executeUpdate();
			
		}catch (SQLException e) {
			throw new RuntimeException();
		}
		
		
		
	}
	}
	

