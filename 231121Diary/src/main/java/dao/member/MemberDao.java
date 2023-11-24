package dao.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dao.MyDataSource;
import vo.member.Member;
import vo.member.MemberCreateDto;
import vo.member.MemberDeleteDto;
import vo.member.MemberInfoDto;
import vo.member.MemberLoginDto;

public class MemberDao {
	private static final Logger log = Logger.getGlobal();
	//회원가입
	public int createMember(MemberCreateDto dto) {
		String Sql = "INSERT INTO member (member_id, member_pw) VALUES(?, ?)";
		int validateRow = 0;
		try (
			Connection conn = MyDataSource.getConn();
			PreparedStatement stmt = conn.prepareStatement(Sql);
		){
			conn.setAutoCommit(false);
			log.info(conn.toString());
			stmt.setString(1, dto.getMemberId());
			stmt.setString(2, dto.getMemberPw());
			log.info(stmt.toString());
			validateRow = stmt.executeUpdate();
			if(validateRow == 1) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (SQLException e) {
			log.severe("SQL fail 롤백합니다."); // conn.close()는 commit 하지 않은 미완결 트랜잭션을 자동으로  Rollback시킴
			e.printStackTrace();
		}
		
		return validateRow;
		
	}
	// 로그인
	public Member LoginMember(MemberLoginDto dto) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String sql = """
				SELECT member_no memberNo, member_id memberId, createdate FROM MEMBER
				 WHERE member_id = ? AND member_pw = ?""";
		Member member = null;
		try (
			Connection conn = MyDataSource.getConn();
			PreparedStatement stmt = conn.prepareStatement(sql);
		){
			log.info(conn.toString());
			stmt.setString(1, dto.getMemberId());
			stmt.setString(2, dto.getMemberPw());
			log.info(stmt.toString());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				member = new Member.builder()
						.memberNo(rs.getInt("memberNo"))
						.memberId(rs.getString("memberId"))
						.createdate(LocalDateTime.parse(rs.getString("createdate"),formatter))
						.build();
				log.info("로그인으로 찾은 정보 : "+ member.toString());
				rs.close();
				return member;
			}else {
				log.info("로그인 정보를 찾을 수 없습니다. null을 반환합니다.");
				rs.close();
				return null;
			}
		} catch (SQLException e) {
			log.severe("SQL fail"); // conn.close()는 commit 하지 않은 미완결 트랜잭션을 자동으로  Rollback시킴
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean isUniqueId(MemberCreateDto dto) {
		String sql = "SELECT member_id memberId WHERE member_id = ?";
		try (
			Connection conn = MyDataSource.getConn();
			PreparedStatement stmt = conn.prepareStatement(sql);
		){
			log.info(conn.toString());
			stmt.setString(1, dto.getMemberId());
			log.info(stmt.toString());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				log.info("찾은 중복된 아이디 정보, false리턴 : "+ rs.getString("memberId"));
				rs.close();
				return false ;
			}else {
				log.info("유니크한 아이디입니다.");
				rs.close();
				return true;
			}
		} catch (SQLException e) {
			log.severe("SQL fail"); // conn.close()는 commit 하지 않은 미완결 트랜잭션을 자동으로  Rollback시킴
			e.printStackTrace();
			return false;
		}
	}
	public int updateMemberPw(MemberInfoDto dto) {
		int validateRow = 0;
		String sql = """
				UPDATE member SET member_pw = ? WHERE member_no = ? AND member_id = ? AND member_pw = ?""";
		try (
			Connection conn = MyDataSource.getConn();
			PreparedStatement stmt = conn.prepareStatement(sql);
		){
			conn.setAutoCommit(false);
			log.info(conn.toString());
			stmt.setString(1, dto.getMemberPwNew());
			stmt.setInt(2, dto.getMemberNo());
			stmt.setString(3, dto.getMemberId());
			stmt.setString(4, dto.getMemberPwNow());
			log.info(stmt.toString());
			validateRow = stmt.executeUpdate();
			if(validateRow == 1) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (SQLException e) {
			log.severe("SQL fail 롤백합니다."); // conn.close()는 commit 하지 않은 미완결 트랜잭션을 자동으로  Rollback시킴
			e.printStackTrace();
		}
		return validateRow;
	}
	public int deleteMemberPw(MemberDeleteDto dto) {
		int validateRow = 0;
		String sql = """
				DELETE FROM member WHERE member_no = ? AND member_id = ? AND member_pw = ?""";
		try (
			Connection conn = MyDataSource.getConn();
			PreparedStatement stmt = conn.prepareStatement(sql);
		){
			conn.setAutoCommit(false);
			log.info(conn.toString());
			stmt.setInt(1, dto.getMemberNo());
			stmt.setString(2, dto.getMemberId());
			stmt.setString(3, dto.getMemberPwNow());
			log.info(stmt.toString());
			validateRow = stmt.executeUpdate();
			if(validateRow == 1) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (SQLException e) {
			log.severe("SQL fail 롤백합니다."); // conn.close()는 commit 하지 않은 미완결 트랜잭션을 자동으로  Rollback시킴
			e.printStackTrace();
		}
		return validateRow;
	}
}
