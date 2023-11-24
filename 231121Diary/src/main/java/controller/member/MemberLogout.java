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
@WebServlet("/member/logout")
public class MemberLogout extends HttpServlet{
	private static final Logger log = Logger.getGlobal();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("Member/Logout GET");
		HttpSession session = req.getSession();
		log.info("세션 로그인정보를 삭제합니다. : "+session.getAttribute("member").toString());
		session.removeAttribute("member");
		String ReferUrl = req.getHeader("Referer");
		resp.sendRedirect(req.getContextPath()+"/index");
		return;
	}
}
