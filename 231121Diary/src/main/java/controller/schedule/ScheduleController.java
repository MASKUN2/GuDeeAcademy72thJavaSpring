package controller.schedule;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.schedule.ScheduleDao;
import vo.member.Member;
@WebServlet("/schedule")
public class ScheduleController extends HttpServlet{
	private static final Logger log = Logger.getGlobal();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String paramDate = req.getParameter("date");
		Member member = (Member) req.getSession().getAttribute("member");
		String[] yearMonth = paramDate.split("-");
		log.info(yearMonth[0]);
		log.info(yearMonth[1]);
		
		ScheduleDao dao = new ScheduleDao();
		Map<Integer, String> map = dao.retrieveDateSchedule(member.getMemberId(), paramDate);
		req.setAttribute("map", map);
		req.setAttribute("date", paramDate);
		req.setAttribute("requestYear", yearMonth[0]);
		req.setAttribute("requestMonth", yearMonth[1]);
		
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/view/schedule/date.jsp");
		requestDispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String paramDate = req.getParameter("date");
		String paramControl = req.getParameter("control");
		Member member = (Member) req.getSession().getAttribute("member");
		String memberId = member.getMemberId();
		int scheduleNo = 0;
		
		try {
			scheduleNo = Integer.parseInt(req.getParameter("scheduleNo"));
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		ScheduleDao dao = new ScheduleDao();
		
		switch (paramControl) {
			case "add" : {
				String emoticon = req.getParameter("emoticon");
				String newMemo = emoticon + req.getParameter("newMemo");
				dao.addSchedule(memberId,newMemo, paramDate);
				break;
			}
			case "edit" :{
				String editMemo = req.getParameter("editMemo");
				dao.aditSchedule(editMemo, scheduleNo);
				break;
			}
			case "remove" :{
				dao.removeSchedule(scheduleNo);
				break;
			}
		}
		
		resp.sendRedirect(req.getContextPath()+"/schedule?date="+paramDate);
	
	}

}
