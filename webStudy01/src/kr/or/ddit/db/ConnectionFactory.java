package kr.or.ddit.db;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *  data베이스와 연결 객체를 생성하는 객체.
 *  @author PC22
 *	Factory Object[Method] Pattern	
 */
public class ConnectionFactory {
	private static String url;
	private static String user;
	private static String password;
	private static String driverClassName;
	static {
		// dbinfo.properties 역직렬화 -> properties 객체화, classPath resource
		String dbInfoPath = "/kr/or/ddit/db/dbInfo.properties";
		try(
		InputStream is = ConnectionFactory.class.getResourceAsStream(dbInfoPath);
		){
			Properties props = new Properties();
			props.load(is);
			url = props.getProperty("url");
			user = props.getProperty("user");
			password = props.getProperty("password");
			driverClassName = props.getProperty("driverClassName");
			Class.forName(driverClassName);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		// 2. 드라이버 class 로딩
	}
	//static 코드 블럭 static이 없으면 생성자와 같고, static이 붙으면 클래스가 메모리에 적재 되는 순간 메모리에 올라간다.
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

}

