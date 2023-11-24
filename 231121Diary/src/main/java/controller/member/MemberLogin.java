package controller.member;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.member.MemberDao;
import vo.member.Member;
import vo.member.MemberCreateDto;
import vo.member.MemberLoginDto;
@WebServlet("/member/login")
public class MemberLogin extends HttpServlet{
	private static final Logger log = Logger.getGlobal();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("Member/Login GET");
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/view/member/login.jsp");
		requestDispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("member/login POST");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		MemberLoginDto dto = new MemberLoginDto.Builder().memberId(id).memberPw(pw).build();
		MemberDao memberDao = new MemberDao();
		Member member = memberDao.LoginMember(dto);
		
		if (member != null) {
			//200
			log.info("로그인성공");
			HttpSession session = req.getSession();
			session.setAttribute("member", member);
			log.info("세션에 로그인정보가 저장되었습니다. : "+session.getAttribute("member").toString());
			resp.sendRedirect(req.getContextPath()+"/index");
			return;
		}else {
			//400
			log.info("로그인실패");
			resp.sendRedirect(req.getContextPath()+"/member/login");
			return;
		}
	}
}
