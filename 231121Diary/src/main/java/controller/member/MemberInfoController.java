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
import vo.member.MemberInfoDto;
@WebServlet("/member/info")
public class MemberInfoController extends HttpServlet{
	private static final Logger log = Logger.getGlobal();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("Member/info get");
		RequestDispatcher requestDispatcher =  req.getRequestDispatcher("/WEB-INF/view/member/delete.jsp");
		requestDispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("Member/info post");
		String pwNow = req.getParameter("pwNow");
		String pwNew = req.getParameter("pwNew");
		String pwNewCheck = req.getParameter("pwNewCheck");
		Member member = (Member) req.getSession().getAttribute("member");
		MemberInfoDto dto = new MemberInfoDto.builder().memberNo(member.getMemberNo())
								.memberId(member.getMemberId()).memberPwNow(pwNow).memberPwNew(pwNew)
								.memberPwNewCheck(pwNewCheck).build();
		
		Validator validator = new Validator();
		ValidationReport validationReport = validator.validate(dto, (MemberInfoDto info)->
		{	
			Boolean isPass = true;
			StringBuilder vlog = new StringBuilder();
			if(!info.getMemberPwNew().equals(info.getMemberPwNewCheck())) {
				isPass = false;
				vlog.append("비밀번호 확인이 불일치합니다.");
			}else if(info.getMemberPwNow().equals(info.getMemberPwNew())){
				isPass = false;
				vlog.append("지금 비밀번호와 같은 비밀번호로 변경할 수 없습니다.");
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
		int validation = memberDao.updateMemberPw(dto);
		
		if (validation == 1) {
			//200
			log.info("updateMemberPw OK");
			resp.sendRedirect(req.getContextPath()+"/member/logout");
			return;
		}else {
			//400
			log.info("updateMemberPw fail");
			resp.sendRedirect(req.getHeader("Referer"));
			return;
		}
	}
}
