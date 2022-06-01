package egovframework.main.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/* MainController */
@Controller
public class MainController {
	// Logger 찍기
	public static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

	// DB 접근을 위한 사용자와 비밀번호 절대변수
	private static final String USER = "TOY_ADMIN", PASSWORD = "1234",
			DATABASE_URL = "jdbc:mysql://localhost:3306/TOY_PROJECT";
	
	// Driver 확인 메서드, private 선언 MainController에서만 사용 
	private void driverClass() {
		// Driver 확인 변수
		Connection conn = null;

		LOGGER.debug(" ========== Check Driver Class ========== ");
		if (conn == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver")/* .newInstance() */;
				conn = DriverManager.getConnection(
						DATABASE_URL, USER, PASSWORD);
				LOGGER.debug(" ========== DB Connection Success! ========== ");
			} catch (SQLException ex) {
				LOGGER.debug(" ========== SQLException: " + ex + " ========== ");
			} catch (Exception e) {
				LOGGER.debug(" ========== Exception: " + e + " ========== ");
			} finally {
				LOGGER.debug(" ========== Current Connection ========== ");
				LOGGER.debug(" ========== " + conn + " ========== ");
			}
		}
	}

	// 메인 페이지
	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String main(Model mainModel) throws Exception {
		// DB Driver 확인
		driverClass();
		LOGGER.debug(" ========== Welcome to eGovWeb Page!");
		return "main";
	}
}
