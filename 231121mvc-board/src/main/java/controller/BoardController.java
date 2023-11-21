package controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;
import vo.Board;
@WebServlet("/boardList")
public class BoardController extends HttpServlet{
	private static final Logger log = Logger.getGlobal();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int currentPage = 1;
		final int rowPerPage = 10;
		boolean isLastPage = false;
		
		//handling unacceptable request parameter 
		String requestPageParameter = req.getParameter("requestPage");
		try {
			currentPage = Integer.valueOf(requestPageParameter);
		} catch (NumberFormatException e) {
			log.warning("String -> integer Parsing이 불가능하여 형변환을 중지했습니다. 기본 입력값으로 진행합니다.("+currentPage+") 입력된 문자열 : "+requestPageParameter);
		}
		
		//retrieve 
		BoardDao dao = new BoardDao();
		int beginRow = (currentPage-1)*rowPerPage;
		List<Board> boardList = dao.selectBoardListByPage(beginRow,rowPerPage);
		
		//check isLastPage
		if(boardList.size() < rowPerPage) {
			isLastPage = true;
		}
		//set Attributes and foward
		req.setAttribute("boardList", boardList);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("isLastPage", isLastPage);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/view/boardList.jsp");
		requestDispatcher.forward(req, resp);
	}
}