package com.timete.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyUtils {

	private static String PROPERTIES_FILE = "settings.properties";
	private static Properties properties;
	private static Logger log = Logger.getLogger(PropertyUtils.class.getName());

	public enum Property {
		NE4J_DBPATH("neo4j.dbpath"), MYSQL_CONN_URL("mysql.conn.url"), MYSQL_CONN_USERNAME(
				"mysql.conn.username"), MYSQL_CONN_PASSWORD(
				"mysql.conn.password"), FACEBOOK_APPID("facebook.appId"), FACEBOOK_APPSECRET(
				"facebook.appSecret"), FOURSQUARE_CLIENTID(
				"foursquare.clientId"), FOURSQUARE_CLIENTSECRET(
				"foursquare.clientSecret"), TWITTER_CONSUMERKEY(
				"twitter.consumerKey"), TWITTER_CONSUMERSECRET(
				"twitter.consumerSecret");

		public String value;

		private Property(String value) {
			this.value = value;
		}
	};

	static {
		try {
			properties = new Properties();
			properties.load(FileUtils.getResourceIS(PROPERTIES_FILE));
		} catch (Exception e) {
			log.error(e.toString(), e);
		}
	}

	public static String getProperty(String key) {
		return getProperties().getProperty(key);
	}

	public static String getProperty(String key, String defaultValue) {
		return getProperties().getProperty(key, defaultValue);
	}

	public static void setProperty(String key, String value) {
		getProperties().setProperty(key, value);
	}

	public static void store() {
		try {
			getProperties().store(new FileOutputStream(PROPERTIES_FILE), null);
		} catch (Exception e) {
			log.error(e.toString(), e);
		}
	}

	private static Properties getProperties() {
		if (null == properties) {
			try {
				properties = new Properties();
				File settings = new File(PROPERTIES_FILE);
				if (!settings.exists()) {
					settings.createNewFile();
				}
				properties.load(new FileInputStream(settings));
			} catch (Exception e) {
				log.error(e.toString(), e);
			}
		}
		return properties;
	}

}
