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
import vo.member.Member;
import vo.member.MemberCreateDto;
import vo.member.MemberDeleteDto;
import vo.member.MemberInfoDto;
@WebServlet("/member/delete")
public class MemberDeleteController extends HttpServlet{
	private static final Logger log = Logger.getGlobal();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("Member/delete get");
		RequestDispatcher requestDispatcher =  req.getRequestDispatcher("/WEB-INF/view/member/delete.jsp");
		requestDispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("Member/delete post");
		String pwNow = req.getParameter("pwNow");
		String pwNowCheck = req.getParameter("pwNowCheck");
		Member member = (Member) req.getSession().getAttribute("member");
		MemberDeleteDto dto = new MemberDeleteDto.builder().memberNo(member.getMemberNo())
								.memberId(member.getMemberId()).memberPwNow(pwNow).memberPwNowCheck(pwNowCheck).build();
		
		Validator validator = new Validator();
		ValidationReport validationReport = validator.validate(dto, (MemberDeleteDto info)->
		{	
			Boolean isPass = true;
			StringBuilder vlog = new StringBuilder();
			if(!info.getMemberPwNow().equals(info.getMemberPwNowCheck())) {
				isPass = false;
				vlog.append("비밀번호 확인이 불일치합니다.");
			}else {
				vlog.append("validate 통과");
			}
			return new ValidationReport(isPass,vlog.toString());
		});
		
		if(!validationReport.getIsPass()) {
			//400
			log.info(validationReport.getValidationLog());
			resp.sendRedirect(req.getHeader("Referer"));
			return;
		}
		
		MemberDao memberDao = new MemberDao();
		int validation = memberDao.deleteMemberPw(dto);
		
		if (validation == 1) {
			//200
			log.info("deleteMemberPw OK");
			resp.sendRedirect(req.getContextPath()+"/member/logout");
			return;
		}else {
			//400
			log.info("deleteMemberPw fail");
			resp.sendRedirect(req.getHeader("Referer"));
			return;
		}
	}
}
