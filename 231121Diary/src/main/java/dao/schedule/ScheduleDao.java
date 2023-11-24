package dao.schedule;

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

public class ScheduleDao {
	private final Logger log = Logger.getGlobal();
	
	public List<String> retrieveMonthSchedule (String memberId, String yearMonth) {
		String Sql = """
				SELECT CAST(DATE_FORMAT(schedule_date,'%d') AS UNSIGNED) 'date' ,COUNT(*) count, GROUP_CONCAT(substr(schedule_memo,1, 5) SEPARATOR '<br>') memo FROM `schedule` 
				WHERE member_id = ? AND DATE_FORMAT(schedule_date, '%Y-%m') = ? 
				GROUP BY schedule_date ORDER BY schedule_date 
				""";
		try (
			Connection conn = MyDataSource.getConn();
			PreparedStatement stmt = conn.prepareStatement(Sql);
		){
			log.info(conn.toString());
			stmt.setString(1, memberId);
			stmt.setString(2, yearMonth);
			log.info(stmt.toString());
			ResultSet rs = stmt.executeQuery();
			ArrayList<String> list = new ArrayList<>(32);
			for(int i = 0; i < 32;i++) {
				list.add("");
			}
			while(rs.next()) {
				list.add(rs.getInt("date"), rs.getString("memo"));
			}
			return list;

		} catch (SQLException e) {
			log.info("SQL fail 롤백합니다."); // conn.close()는 commit 하지 않은 미완결 트랜잭션을 자동으로  Rollback시킴
			e.printStackTrace();
			return null;
		}
		
	}

	public Map<Integer, String> retrieveDateSchedule(String memberId, String paramDate) {
		String Sql = """
				SELECT schedule_no scheduleNo, schedule_memo memo FROM `schedule` 
				 WHERE member_id = ? AND schedule_date = STR_TO_DATE(?,'%Y-%m-%d') 
				 ORDER BY schedule_no """;
		try (
			Connection conn = MyDataSource.getConn();
			PreparedStatement stmt = conn.prepareStatement(Sql);
		){
			log.info(conn.toString());
			stmt.setString(1, memberId);
			stmt.setString(2, paramDate);
			log.info(stmt.toString());
			ResultSet rs = stmt.executeQuery();
			Map<Integer, String> map = new HashMap<>();
			while(rs.next()) {
				map.put(rs.getInt("scheduleNo"), rs.getString("memo"));
			}
			return map;

		} catch (SQLException e) {
			log.info("SQL fail 롤백합니다."); // conn.close()는 commit 하지 않은 미완결 트랜잭션을 자동으로  Rollback시킴
			e.printStackTrace();
			return null;
		}
		
	}

	public int addSchedule(String memberId, String newMemo, String paramDate) {
		String Sql = """
				INSERT INTO `schedule` (member_id,schedule_date,schedule_memo)VALUES
				(?,STR_TO_DATE(?,'%Y-%m-%d'),?)""";
		try (
			Connection conn = MyDataSource.getConn();
			PreparedStatement stmt = conn.prepareStatement(Sql);
		){
			conn.setAutoCommit(false);
			log.info(conn.toString());
			stmt.setString(1, memberId);
			stmt.setString(2, paramDate);
			stmt.setString(3, newMemo);
			log.info(stmt.toString());
			int validation = stmt.executeUpdate();
			if(validation == 1) {
				conn.commit();
			}else {
				conn.rollback();
			}
			return validation;

		} catch (SQLException e) {
			log.info("SQL fail 롤백합니다."); // conn.close()는 commit 하지 않은 미완결 트랜잭션을 자동으로  Rollback시킴
			e.printStackTrace();
			return 0;
		}
		
	}

	public int aditSchedule(String editMemo, int scheduleNo) {
		String Sql = """
				UPDATE schedule SET schedule_memo = ? WHERE schedule_no = ?""";
		try (
			Connection conn = MyDataSource.getConn();
			PreparedStatement stmt = conn.prepareStatement(Sql);
		){
			conn.setAutoCommit(false);
			log.info(conn.toString());
			stmt.setString(1, editMemo);
			stmt.setInt(2, scheduleNo);
			log.info(stmt.toString());
			int validation = stmt.executeUpdate();
			if(validation == 1) {
				conn.commit();
			}else {
				conn.rollback();
			}
			return validation;

		} catch (SQLException e) {
			log.info("SQL fail 롤백합니다."); // conn.close()는 commit 하지 않은 미완결 트랜잭션을 자동으로  Rollback시킴
			e.printStackTrace();
			return 0;
		}
		
	}

	public int removeSchedule(int scheduleNo) {
		String Sql = """
				DELETE FROM schedule WHERE schedule_no = ?""";
		try (
			Connection conn = MyDataSource.getConn();
			PreparedStatement stmt = conn.prepareStatement(Sql);
		){
			conn.setAutoCommit(false);
			log.info(conn.toString());
			stmt.setInt(1, scheduleNo);
			log.info(stmt.toString());
			int validation = stmt.executeUpdate();
			if(validation == 1) {
				conn.commit();
			}else {
				conn.rollback();
			}
			return validation;

		} catch (SQLException e) {
			log.info("SQL fail 롤백합니다."); // conn.close()는 commit 하지 않은 미완결 트랜잭션을 자동으로  Rollback시킴
			e.printStackTrace();
			return 0;
		}
		
	}
}
