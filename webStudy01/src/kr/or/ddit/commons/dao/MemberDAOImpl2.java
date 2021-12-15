package kr.or.ddit.commons.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl2 implements MemberDAO {
	
	private static MemberDAOImpl2 self;
	private MemberDAOImpl2() {}
	public static MemberDAOImpl2 getInstance() {
		if(self == null)
		self = new MemberDAOImpl2();
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
		
		try(
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
		){
			StringBuffer sql = new StringBuffer();
			sql.append(" select mem_id, mem_pass, mem_name ");
			sql.append(" from member ");
			sql.append(" where mem_id = '"+memId+"' ");
			ResultSet rs = stmt.executeQuery(sql.toString());
			
			while(rs.next()) {
				
				vo.setMemId(rs.getString(1));
				vo.setMemPass(rs.getString(2));
				vo.setMemName(rs.getString(3));
			}
			
		}catch (SQLException e) {
			throw new RuntimeException();
		}
		
		
		return vo;
	}

}
