package controller.member;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/index")
public class MemberHomeController extends HttpServlet{
	private static final Logger log = Logger.getGlobal();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String paramRequestYear = req.getParameter("requestYear");
		String paramRequestMonth = req.getParameter("requestMonth");
		
		int requestYear ;
		int requestMonth ;
		int lengthOfMonth;
		int dayOfWeek;
		
		LocalDate requestDate;
		if(paramRequestYear != null && paramRequestMonth != null) {
			requestYear = Integer.parseInt(paramRequestYear);
			requestMonth = Integer.parseInt(paramRequestMonth);
			requestDate = LocalDate.of(requestYear, requestMonth, 1);
		}else {
			requestDate = LocalDate.now().withDayOfMonth(1);
			requestMonth = requestDate.getMonthValue();
			requestYear = requestDate.getYear();
		}
		
		lengthOfMonth = requestDate.lengthOfMonth();
		dayOfWeek = requestDate.getDayOfWeek().getValue();
		
		req.setAttribute("requestYear", requestYear);
		req.setAttribute("requestMonth", requestMonth);
		req.setAttribute("lengthOfMonth", lengthOfMonth);
		req.setAttribute("dayOfWeek", dayOfWeek);
		req.setAttribute("test", 1);
		
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
		requestDispatcher.forward(req, resp);
	}
	
}









