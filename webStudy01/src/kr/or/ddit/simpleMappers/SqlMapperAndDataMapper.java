package kr.or.ddit.simpleMappers;

import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.text.CaseUtils;

import kr.or.ddit.member.dao.MemberDAOImpl;

public class SqlMapperAndDataMapper {
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
	
	public PreparedStatement generateQueryStatment(Connection conn, String queryId) throws SQLException {
		String sql = sqlMap.getProperty(queryId);
		return conn.prepareStatement(sql);
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
	
}
