package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import vo.Board;

public class BoardDao {
	private static final Logger log = Logger.getGlobal();
	
	//constructor
	public BoardDao() {
	}

	//현재 페이지 게시글 조회
	public List<Board> selectBoardListByPage(int beginRow, int rowPerPage) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		List<Board> boardList = new ArrayList<>();;
		DataSource ds = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = """
				SELECT board_no boardNo, board_title boardTitle, board_content boardContent,
				 board_writer boardWriter, createdate, updatedate FROM board ORDER BY board_no DESC 
				 LIMIT ?, ?""";
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc/mariadb");
			conn = ds.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			log.info(stmt.toString());
			rs = stmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setBoardNo(rs.getInt("boardNo"));
				board.setBoardTitle(rs.getString("boardTitle"));
				board.setBoardContent(rs.getString("boardContent"));
				board.setBoardWriter(rs.getString("boardWriter"));
				board.setCreatedate(LocalDateTime.parse(rs.getString("createdate"),formatter));
				board.setUpdatedate(LocalDateTime.parse(rs.getString("updatedate"),formatter));
				boardList.add(board);
			}
			log.info(String.format("retrieved rows : %d", boardList.size()));
		} catch (SQLException | NamingException e) {
			log.warning("Exception occurred");
			e.printStackTrace();
			
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				log.warning("Exception occurred");
				e.printStackTrace();
			}
		}
		if (boardList.size() == 0) {
			log.warning("DAO did not found any row");
			return null;
		}
		return boardList;
	}
}