package dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionProperty {
	InputStream input = null;
	public static final String CONFIG_NAME = "config.properties";
	public static final Properties GLOBAL_COFIG = new Properties();
	public ConnectionProperty() throws FileNotFoundException, IOException {
		input = getClass().getClassLoader().getResourceAsStream(CONFIG_NAME);
		GLOBAL_COFIG.load(input);
		
	}
	public static String getProperty(String property) {
		return GLOBAL_COFIG.getProperty(property);
	}
}
