package dbinit;

import java.sql.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
/**웹앱에서 사용되는 데이터베이스를 초기화할 목적의 클래스입니다.
 * 실행환경(학원-집)변화에 영향을 받지 않기 위해 고안했습니다. 
 * 서블릿 리스너로 구현됩니다.
 * */
@WebListener
public class DBinitializer implements ServletContextListener{
	/**웹앱시작 이벤트리스너 메소드입니다.
	 * 접속 DB를 지정하지 않는 root로 접속한후 Drop DB를 실행하고 Create DB, Create table, Insert seed data를 수행합니다.
	 * 목적 DB는 diary이며 DBinitSqlVo.RAWINITSQL의 하드코딩된 SQL을 스플릿하여 배치로 만들어 쿼리를 실행합니다.
	 * 이후 웹앱에서 사용되는 conn은 커넥션 풀에서 관리되는 것을 사용합니다.
	 * */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("init listener called trying to DBinitialize...");
		final String URL = "jdbc:mariadb://localhost:3306";
		final String DBUSER = "root";
		final String DBPW = "java1234";
		Statement stmt = null;
		Connection conn= null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(URL, DBUSER, DBPW);
			String[] initSqlBatch = DBinitSqlVo.RAWINITSQL.split(";");
			stmt = conn.createStatement();
            for (String query : initSqlBatch) {
                stmt.addBatch(query);
            }
            int[] updateCounts = stmt.executeBatch();
            for (int updateCount : updateCounts) {
                System.out.println("Query executed, update count: " + updateCount);
            }
            System.out.println("Batch execution successful.");			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("DB Initialization is done.");
	}

}
