package kr.or.ddit.commons.dao;

import kr.or.ddit.vo.MemberVO;

public interface MemberDAO {
	/**
	 * 식별자로 한사람의 사용자를 조회
	 * @author memid 조회할 사람의 아이디
	 * @return 존재하지 않는 경우, null 반환.
		 */
	public MemberVO selectMemberForAuth(String memId);
}
