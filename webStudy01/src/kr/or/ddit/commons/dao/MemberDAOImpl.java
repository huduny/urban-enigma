package kr.or.ddit.commons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {
	
	private static MemberDAOImpl self;
	private MemberDAOImpl() {}
	public static MemberDAOImpl getInstance() {
		if(self == null)
		self = new MemberDAOImpl();
		return self;
	}
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
	

}
