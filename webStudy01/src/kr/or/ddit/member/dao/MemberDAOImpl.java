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
		return (MemberVO) mapper.queryForObject("selectMemberForAuth", MemberVO.class, memId);
	}
	@Override
	public int insertMember(MemberVO member) {
		return mapper.insert("insertMember", member);
	}
	

	
	@Override
	public List<MemberVO> selectMemberList() {
		return (List<MemberVO>) mapper.queryForList("selectMemberList", MemberVO.class);
	}//end selectList
	
	@Override
	public MemberVO selectMember(String memId) {
	    return (MemberVO)mapper.queryForObject("selectMember", MemberVO.class, memId);

	
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
