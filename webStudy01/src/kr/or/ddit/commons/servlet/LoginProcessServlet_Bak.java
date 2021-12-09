package kr.or.ddit.commons.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.commons.service.AutehnticateServiceImpl;
import kr.or.ddit.commons.service.AuthenticateService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/login/loginProcess.do_Bak")
public class LoginProcessServlet_Bak extends HttpServlet{
	private AuthenticateService service = AutehnticateServiceImpl.getInstance();
//	public void setService(AuthenticateService service) {
//		this.service = service;
//	}
//	AutehnticateServiceImpl service;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
//		4. 인증을 하기 위해서는 인증로직이 필요하다. 경우의 수-아이디존재 안함, 아이디존재 함, 비밀번호 잘못됨, 비밀번호 일치, 로그인됨
		String memId = req.getParameter("memId");
		String memPass = req.getParameter("memPass");
		
		MemberVO inputData = new MemberVO(memId,memPass);

		ServiceResult result =  service.authenticate(inputData);
//		4-1. 존재하지 않는 사용자
//		loginForm으로 이동. message 공유
		if (result==ServiceResult.NOTEXIST) {
			String message = "존재하지 않는 사용자 입니다.";
			HttpSession session = req.getSession();
			session.setAttribute("session", message);
			
			String dest = "/login/loginForm.jsp";
			String location =req.getContextPath() + dest;
			resp.sendRedirect(location);
		}
//		4-2. 비밀번호 오류
//			loginForm으로 이동. message 공유
		if (result==ServiceResult.INVALIDPASSWORD) {
			String message = "비밀번호 오류 입니다.";
			HttpSession session = req.getSession();
			session.setAttribute("session", message);
			
			String dest = "/login/loginForm.jsp";
			String location =req.getContextPath() + dest;
			resp.sendRedirect(location);
		}
		
//		4-3. 인증 통과
//			웰컴 페이지로 이동. 
		if (result==ServiceResult.OK) {
//			String message = "비밀번호 오류 입니다.";
			HttpSession session = req.getSession();
//			session.setAttribute("session", message);
			
			String dest = "/index.jsp";
			String location =req.getContextPath() + dest;
			resp.sendRedirect(location);
		}
		
		
//			이때는 이미 처리가 다 끝나서 redirect가 필요하다. (서버에 안남길려고-서버의자원 절약)
//			
//			나머지 경우들은 message를 가지고 가야되서 scope가 필요하다. 어떤방식으로 이동할지에 따라서 forward(request scope) 
//			redirect(session scope)
		
//		String message = "존재하지 않는 사용자 입니다.";
////		HttpSession session = req.getSession();
////		session.setAttribute("session", message);
//		String dest = "/login/loginForm.jsp";
//		
//		req.setAttribute("request", message);
//		RequestDispatcher dis = req.getRequestDispatcher(dest);
//		dis.forward(req, resp);
//
////		String location =req.getContextPath() + dest;
////		resp.sendRedirect(location);
		
		
	}
}
