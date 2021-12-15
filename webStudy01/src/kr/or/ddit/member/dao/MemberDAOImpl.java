package kr.or.ddit.member.dao;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.text.CaseUtils;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.simpleMappers.SqlMapperAndDataMapper;
import kr.or.ddit.vo.DataBasePropertyVO;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {
	
	private static MemberDAOImpl self;
	private MemberDAOImpl() {}
	public static MemberDAOImpl getInstance() {
		if(self == null)
		self = new MemberDAOImpl();
		return self;
	}
	
	private SqlMapperAndDataMapper mapper = new SqlMapperAndDataMapper("/kr/or/ddit/simpleMappers/Member.xml");
	private static MemberVO vo = new MemberVO();
	
	@Override
	public MemberVO selectMemberForAuth(String memId) {
		/*
		 * 1.데이터베이스 접속
		 * -드라이버 찾기
		 * -로딩
		 * -커넥션
		 * -쿼리문
		 * -실행
		 * -결과값핸들링
		 * -close
		 * -값맅리턴
		 */
		StringBuffer sql = new StringBuffer();
		sql.append(" select mem_id, mem_pass, mem_name ");
		sql.append(" from member ");
		sql.append(" where mem_id = ? ");
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
		){
			stmt.setString(1, memId);
			ResultSet rs = stmt.executeQuery();
			System.out.println(rs);
			MemberVO vo = null;
			if (rs.next()) {
				vo = MemberVO.builder()
							.memId(rs.getString("mem_id"))
							.memPass(rs.getString("mem_pass"))
							.memName(rs.getString("mem_name"))
							.build();
			}
			
		}catch (SQLException e) {
			throw new RuntimeException();
		}
		
		
		return vo;
	}
	@Override
	public int insertMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}
	

	
	@Override
	public List<MemberVO> selectMemberList() {
		/*
		 * SELECT MEM_ID, MEM_NAME, MEM_HP, MEM_ADD1, MEM_MAIL, MEM_MILEAGE, MEM_BIR
			FROM MEMBER ;
		 */
		
		List<MemberVO> list = new ArrayList<>();
		
		try(
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stmt = mapper.generateQueryStatment(conn, "selectMemberList");
			){
				ResultSet rs = stmt.executeQuery();
				System.out.println(rs);
				MemberVO vo = null;
				while (rs.next()) {
					Object result = mapper.dataMapping(rs, MemberVO.class);
//					vo = MemberVO.builder()
//								.memId(rs.getString("mem_id"))
//								.memName(rs.getString("mem_name"))
//								.memHp(rs.getString("MEM_HP"))
//								.memAdd1(rs.getString("MEM_ADD1"))
//								.memMail(rs.getString("MEM_MAIL"))
//								.memMileage(rs.getInt("MEM_MILEAGE"))
//								.memBir(rs.getString("MEM_BIR"))
//								.build();
					list.add((MemberVO) result);
				}
			
			return list;
		}catch (SQLException e) {
			throw new RuntimeException();
		}
	}//end selectList
	
	@Override
	public MemberVO selectMember(String memId) {
	      MemberVO member = null;
	      try(
	         Connection conn = ConnectionFactory.getConnection();
	         PreparedStatement pstmt = mapper.generateQueryStatment(conn, "selectMember");
	      ){
	         pstmt.setString(1, memId);
	         ResultSet rs = pstmt.executeQuery();
	         if(rs.next()) {
	            member = (MemberVO) mapper.dataMapping(rs, MemberVO.class);
	         }
	         // 2.조회한 데이터에 따라 그 데이터를 바인드하는 result type vo 가 달라짐. --> data mapper
	      
	         return member;
	      } catch (SQLException e) {
	         throw new RuntimeException(e);
	      }

	
	}//end
	
	@Override
	public int updateMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}//end
	@Override
	public int deleteMember(String memId) {
		// TODO Auto-generated method stub
		return 0;
	}//end
	

}
