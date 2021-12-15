package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	private MemberDAOImpl dao = MemberDAOImpl.getInstance();
	
	private static MemberServiceImpl self;
	private MemberServiceImpl() {}
	public static MemberServiceImpl getInstance() {
		if(self == null)
		self = new MemberServiceImpl();
		return self;
	}

	@Override
	public ServiceResult createMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberVO> retrieveMemberList() {
		
		return dao.selectMemberList();
	}

	@Override
	public MemberVO retrieveMember(String memId) {
		// TODO Auto-generated method stub
		return dao.selectMember(memId);
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}

}
