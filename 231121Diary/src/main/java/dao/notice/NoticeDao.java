package dao.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import dao.MyDataSource;

public class NoticeDao {
	private final Logger log = Logger.getGlobal();
	
	public List<Map<String, Object>> getNoticeList() {
		String Sql = """
				SELECT notice_no noticeNo, notice_title noticeTitle FROM notice ORDER BY createdate DESC""";
		try (
			Connection conn = MyDataSource.getConn();
			PreparedStatement stmt = conn.prepareStatement(Sql);
		){
			log.info(stmt.toString());
			ResultSet rs = stmt.executeQuery();
			ArrayList<Map<String, Object>> list = new ArrayList<>();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("noticeNo", rs.getInt("noticeNo"));
				map.put("noticeTitle", rs.getString("noticeTitle"));
				list.add(map);
			}
			return list;

		} catch (SQLException e) {
			log.severe("SQL fail 롤백합니다."); // conn.close()는 commit 하지 않은 미완결 트랜잭션을 자동으로  Rollback시킴
			e.printStackTrace();
			return null;
		}
	}
	
	public void getNoticeDetail(int noticeNo, Map<String, Object> noticeDatil, List<Map<String, Object>> commentList) {
		String SqlNotice = """
				SELECT notice_no noticeNo, author, notice_title noticeTitle, notice_content noticeContent FROM notice WHERE notice_no = ?""";
		String SqlComment = """
				SELECT comment_no commentNo, author, comement_content commentContent FROM notice_comment WHERE notice_no = ?""";
		try (
			Connection conn = MyDataSource.getConn();
			PreparedStatement stmtNotice = conn.prepareStatement(SqlNotice);
			PreparedStatement stmtComent = conn.prepareStatement(SqlComment);
		){
			stmtNotice.setInt(1, noticeNo);
			stmtComent.setInt(1, noticeNo);
			log.info(stmtNotice.toString());
			ResultSet rsNotice = stmtNotice.executeQuery();
			if(rsNotice.next()) {
				 noticeDatil.put("noticeNo", rsNotice.getInt("noticeNo"));
				 noticeDatil.put("author", rsNotice.getString("author"));
				 noticeDatil.put("noticeTitle", rsNotice.getString("noticeTitle"));
				 noticeDatil.put("noticeContent", rsNotice.getString("noticeContent"));
				 log.info("rsNotice");
			}
			ResultSet rsComment = stmtComent.executeQuery();
			while(rsComment.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("commentNo", rsComment.getInt("commentNo"));
				map.put("author", rsComment.getString("author"));
				map.put("commentContent", rsComment.getString("commentContent"));
				commentList.add(map);
			}
			rsNotice.close();
			rsComment.close();

		} catch (SQLException e) {
			log.severe("SQL fail 롤백합니다."); // conn.close()는 commit 하지 않은 미완결 트랜잭션을 자동으로  Rollback시킴
			e.printStackTrace();
		}
		
	}
}
