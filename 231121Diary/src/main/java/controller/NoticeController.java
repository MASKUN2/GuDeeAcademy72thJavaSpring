package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.notice.NoticeDao;
@WebServlet("/notice")
public class NoticeController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int noticeNo = Integer.parseInt(req.getParameter("noticeNo"));
		NoticeDao dao = new NoticeDao();
		Map<String, Object> noticeDetail = new HashMap<>();
		List<Map<String, Object>> commentList = new ArrayList<>();
		dao.getNoticeDetail(noticeNo, noticeDetail, commentList);
		noticeDetail.put("test", "commentList");
		req.setAttribute("noticeDetail", noticeDetail);
		req.setAttribute("commentList", commentList);
		
		List<String> list = new ArrayList<>();
		list.add("sdfsdf");
		
		req.setAttribute("list", list);
		
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/view/notice.jsp");
		System.out.println(noticeDetail.get("noticeNo"));
		requestDispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
	}
}
