package controller.member;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.schedule.ScheduleDao;
import vo.member.Member;
@WebServlet("/index")
public class MemberHomeController extends HttpServlet{
	private static final Logger log = Logger.getGlobal();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LocalDate requestDate;
		int requestYear ;
		int requestMonth ;
		int lengthOfMonth;
		int dayOfWeek;
		
		requestDate = LocalDate.now().withDayOfMonth(1);
		requestMonth = requestDate.getMonthValue();
		requestYear = requestDate.getYear();
		
		lengthOfMonth = requestDate.lengthOfMonth();
		dayOfWeek = requestDate.getDayOfWeek().getValue();
		
		try {
			List<String> memoList = null;
			Member member = (Member) req.getSession().getAttribute("member");
			ScheduleDao dao = new ScheduleDao();
			memoList = dao.retrieveMonthSchedule(member.getMemberId(),requestDate.format(DateTimeFormatter.ofPattern("Y-M")));
			req.setAttribute("memoList", memoList);
		} catch (Exception e) {
			log.info("로그인정보가 없습니다.");
			e.printStackTrace();
		}
		
		req.setAttribute("requestYear", requestYear);
		req.setAttribute("requestMonth", requestMonth);
		req.setAttribute("lengthOfMonth", lengthOfMonth);
		req.setAttribute("dayOfWeek", dayOfWeek);
		
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
		requestDispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String paramRequestYear = req.getParameter("requestYear");
		String paramRequestMonth = req.getParameter("requestMonth");
		String paramControl = req.getParameter("control");
		
		LocalDate requestDate;
		int requestYear ;
		int requestMonth ;
		int lengthOfMonth;
		int dayOfWeek;

		requestDate = LocalDate.of(Integer.parseInt(paramRequestYear), Integer.parseInt(paramRequestMonth), 1);
		
		if(paramControl.equals("month-1")) {
			requestDate = requestDate.minusMonths(1L);
			requestYear = requestDate.getYear();
			requestMonth = requestDate.getMonthValue();
		}else{
			requestDate = requestDate.plusMonths(1L);
			requestYear = requestDate.getYear();
			requestMonth = requestDate.getMonthValue();
		}
		
		lengthOfMonth = requestDate.lengthOfMonth();
		dayOfWeek = requestDate.getDayOfWeek().getValue();
		
		try {
			List<String> memoList = null;
			Member member = (Member) req.getSession().getAttribute("member");
			ScheduleDao dao = new ScheduleDao();
			memoList = dao.retrieveMonthSchedule(member.getMemberId(),requestDate.format(DateTimeFormatter.ofPattern("Y-MM")));
			req.setAttribute("memoList", memoList);
		} catch (Exception e) {
			log.info("로그인정보가 없습니다.");
			e.printStackTrace();
		}
		
		req.setAttribute("requestYear", requestYear);
		req.setAttribute("requestMonth", requestMonth);
		req.setAttribute("lengthOfMonth", lengthOfMonth);
		req.setAttribute("dayOfWeek", dayOfWeek);
		
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
		requestDispatcher.forward(req, resp);
	}
	
	public void getMemoList() {
		
	}
}









