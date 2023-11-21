package controller.member;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.member.MemberDao;
import validation.ValidationReport;
import validation.Validator;
import vo.member.MemberCreateDto;
@WebServlet("/member/info")
public class MemberRetrieveController extends HttpServlet{
	private static final Logger log = Logger.getGlobal();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("Member/info get");
		RequestDispatcher requestDispatcher =  req.getRequestDispatcher("/WEB-INF/view/member/info.jsp");
		requestDispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("Member/create post");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		MemberCreateDto dto = new MemberCreateDto.Builder().memberId(id).memberPw(pw).build();
		
		Validator validator = new Validator();
		ValidationReport validationReport= validator.validateMemberCreation(dto);
		
		if(!validationReport.getIsPass()) {
			//400
			log.info(validationReport.getValidationLog());
			resp.sendRedirect(req.getContextPath()+"/member/create");
			return;
		}
		
		MemberDao memberDao = new MemberDao();
		int validation = memberDao.createMember(dto);
		
		if (validation == 1) {
			//200
			log.info("회원가입성공");
			resp.sendRedirect(req.getContextPath()+"/member/login");
			return;
		}else {
			//400
			log.info("회원가입실패");
			resp.sendRedirect(req.getContextPath()+"/member/create");
			return;
		}
	}
}
