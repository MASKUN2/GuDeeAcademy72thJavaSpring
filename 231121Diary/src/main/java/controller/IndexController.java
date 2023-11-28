package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import api.ApiExplorer;
import api.ApiVo;
import dao.notice.NoticeDao;
import dao.schedule.ScheduleDao;
import vo.Date;
import vo.member.Member;
@WebServlet("/index")
public class IndexController extends HttpServlet{
	private static final Logger log = Logger.getGlobal();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LocalDate requestDate;
		int requestYear ;
		int requestMonth ;
		int lengthOfMonth;
		int dayOfWeek;
		
		//when have requested Year Month not now;
		try {
			int paramRequestYear = (int) req.getAttribute("requestYear");
			int paramRequestMonth = (int) req.getAttribute("requestMonth");
			requestDate = LocalDate.of(paramRequestYear, paramRequestMonth, 1);
		}catch (Exception e) {
			requestDate = LocalDate.now().withDayOfMonth(1);
			log.info("post로 받은 요청날짜 getting에 fail, then going on with NOW date;");
		}
		
		requestMonth = requestDate.getMonthValue();
		requestYear = requestDate.getYear();
		lengthOfMonth = requestDate.lengthOfMonth();
		dayOfWeek = requestDate.getDayOfWeek().getValue();
		
		List<Date> dateList = null;
		try {
			Member member = (Member) req.getSession().getAttribute("member");
			ScheduleDao dao = new ScheduleDao();
			dateList = dao.retrieveMonthSchedule(member.getMemberId(),requestDate);
			req.setAttribute("dateList", dateList);
		} catch (Exception e) {
			log.info("로그인정보가 없습니다.");
			e.printStackTrace();
		}
		System.out.println(" breakPOint");
		try {
			List<ApiVo> holidayList= ApiExplorer.getHolidays(requestDate);
			System.out.println(" holidayList" + holidayList == null);
			if(holidayList != null && dateList != null) {
				System.out.println(" Steam");
				dateList.forEach(d -> {
					for(ApiVo apiVo : holidayList) {
						System.out.println(" d.getdate: "+d.getDate()+"holiday:" +Integer.parseInt(apiVo.getLocdate().substring(6, 8)));
						if (d.getDate() == Integer.parseInt(apiVo.getLocdate().substring(6, 8))){
							d.setDateName(apiVo.getDateName());
							d.setHoliday(true);
						}
					}
				});
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(dateList != null) {
		System.out.println("매핑테스트");
		dateList.forEach(System.out::println);
		}
		NoticeDao noticeDao = new NoticeDao();
		
		List<Map<String, Object>> noticeList= noticeDao.getNoticeList();
		
		
		req.setAttribute("requestYear", requestYear);
		req.setAttribute("requestMonth", requestMonth);
		req.setAttribute("lengthOfMonth", lengthOfMonth);
		req.setAttribute("dayOfWeek", dayOfWeek);
		req.setAttribute("noticeList", noticeList);
		
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/view/index.jsp");
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

		requestDate = LocalDate.of(Integer.parseInt(paramRequestYear), Integer.parseInt(paramRequestMonth), 1);
		
		if(paramControl.equals("month-1")) {
			requestDate = requestDate.minusMonths(1L);
			requestYear = requestDate.getYear();
			requestMonth = requestDate.getMonthValue();
		}else if(paramControl.equals("month+1")){
			requestDate = requestDate.plusMonths(1L);
			requestYear = requestDate.getYear();
			requestMonth = requestDate.getMonthValue();
		}else {
			requestYear = requestDate.getYear();
			requestMonth = requestDate.getMonthValue();
		}
		
		req.setAttribute("requestYear", requestYear);
		req.setAttribute("requestMonth", requestMonth);
		
		doGet(req, resp);
	}

}









