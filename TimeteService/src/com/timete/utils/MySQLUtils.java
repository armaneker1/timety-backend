package com.timete.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.timete.models.fq.FoursquareWeight;
import com.timete.models.timete.TTSocialProvider;
import com.timete.models.timete.TTUser;

public class MySQLUtils {

	private static Logger log = Logger.getLogger(MySQLUtils.class.getName());

	/*
	 * Constant
	 */
	public static String TBL_USERS = "timete_users";
	public static String TBL_USERS_SOCIALPROVIDER = "timete_user_socialprovider";
	public static String TBL_UNKNOWN_CATEGORY = "timete_unknown_category";
	public static String TBL_FQ_CAT_MAPPING = "timete_fq_category_mapping";
	public static String TBL_FQ_CAT_WEIGHT_SCORE = "timete_fq_category_weight_score";

	/*
	 * Static
	 */
	private static Connection conn;

	public static Connection getConnection() throws Exception {
		try {

			if (conn == null || conn.isClosed()) {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(
						PropertyUtils.getProperty("mysql.conn.url"),
						PropertyUtils.getProperty("mysql.conn.username"),
						PropertyUtils.getProperty("mysql.conn.password"));
				registerShutdownHook(conn);
			}
			return conn;

		} catch (Exception e) {
			throw e;
		}
	}

	public static void registerShutdownHook(final Connection conn)
			throws Exception {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e) {
					log.error("Error", e);
				}

			}
		});
	}

	public static void close(Connection con) {

		try {
			if (con != null) {
				// register on shutdwn
				// con.close();
			}
		} catch (Exception e) {
			log.error("Error", e);
		}

	}

	public static void close(PreparedStatement st) {

		try {
			if (st != null) {
				st.close();
			}
		} catch (Exception e) {
			log.error("Error", e);
		}

	}

	public static void close(ResultSet rs) {

		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			log.error("Error", e);
		}

	}

	public static boolean insertUnknownCategory(String categoryName,
			String userId, String eventId, String socialType, int status) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String SQL = "INSERT  INTO "
				+ TBL_UNKNOWN_CATEGORY
				+ " (categoryName,userId,eventId,socialType,status) VALUES (?,?,?,?,?)";
		try {
			con = getConnection();
			st = con.prepareStatement(SQL);
			st.setString(1, categoryName);
			st.setString(2, userId);
			st.setString(3, eventId);
			st.setString(4, socialType);
			st.setInt(5, status);
			return st.execute();
		} catch (Exception e) {
			log.warn("Warn", e);
		} finally {
			close(rs);
			close(st);
			close(con);
		}
		return false;
	}

	public static void insertUnknownCategorys(List<String> unknownCats) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String SQL = "INSERT  INTO "
				+ TBL_UNKNOWN_CATEGORY
				+ " (categoryName,userId,eventId,socialType,status) VALUES (?,?,?,?,?)";
		try {
			con = getConnection();
			st = con.prepareStatement(SQL);
			for (int i = 0; i < unknownCats.size(); i++) {
				try {
					String[] params = unknownCats.get(i).split("~");
					st.setString(1, params[0]);
					st.setString(2, params[1]);
					st.setString(3, params[2]);
					st.setString(4, params[3]);
					st.setString(5, params[4]);
					//st.setString(6, params[5]);
					st.addBatch();
				} catch (Exception e) {
					log.error("Error", e);
				}
			}
			st.executeBatch();
		} catch (Exception e) {
			log.warn("Warn", e);
		} finally {
			close(rs);
			close(st);
			close(con);
		}
	}

	public static void insertFQCategories(List<String[]> cats) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String SQL = "INSERT  INTO " + TBL_FQ_CAT_MAPPING
				+ " (fq_category_name,fb_category_name) VALUES (?,?)";
		try {
			con = getConnection();
			st = con.prepareStatement(SQL);
			for (int i = 0; i < cats.size(); i++) {
				try {
					String[] params = cats.get(i);
					st.setString(1, params[0]);
					st.setString(2, params[1]);
					st.addBatch();
				} catch (Exception e) {
					log.error("Error", e);
				}
			}
			st.executeBatch();
		} catch (Exception e) {
			log.warn("Warn", e);
		} finally {
			close(rs);
			close(st);
			close(con);
		}
	}

	public static void insertFQCategoriesScore(List<String[]> cats) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		String SQL = "INSERT  INTO "
				+ TBL_FQ_CAT_WEIGHT_SCORE
				+ " (source,category_name,time,checkin,weight,total,constant) VALUES (?,?,?,?,?,?,?)";
		try {
			con = getConnection();
			st = con.prepareStatement(SQL);
			for (int i = 0; i < cats.size(); i++) {
				try {
					String[] params = cats.get(i);
					st.setString(1, params[0]);
					st.setString(2, params[1]);
					st.setInt(3, Integer.parseInt(params[2]));
					st.setInt(4, Integer.parseInt(params[3]));
					st.setDouble(5, Double.parseDouble(params[4]));
					st.setDouble(6, Double.parseDouble(params[6]));
					st.setDouble(7, Double.parseDouble(params[5]));
					st.addBatch();
				} catch (Exception e) {
					log.error("Error", e);
				}
			}
			st.executeBatch();
		} catch (Exception e) {
			log.warn("Warn", e);
		} finally {
			close(rs);
			close(st);
			close(con);
		}
	}

	public static List<TTUser> getUserListForRegister(int limit) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM " + TBL_USERS
				+ " WHERE saved=0 ORDER BY id desc LIMIT ?";
		List<TTUser> users = new ArrayList<TTUser>();
		try {
			con = getConnection();
			st = con.prepareStatement(SQL);
			st.setInt(1, limit);
			rs = st.executeQuery();
			TTUser usr = null;
			while (rs.next()) {
				try {
					usr = new TTUser(rs);
				} catch (Exception e) {
					log.error("Error", e);
				}

				if (usr != null) {
					users.add(usr);
				}
			}
			if (users.size() > 0) {
				return users;
			}
		} catch (Exception e) {
			log.error("Error", e);
		} finally {
			close(rs);
			close(st);
			close(con);
		}
		return null;
	}

	public static List<TTSocialProvider> getProviderListForRegister(int limit) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM " + TBL_USERS_SOCIALPROVIDER
				+ " WHERE status<? ORDER BY user_id desc LIMIT ?";
		List<TTSocialProvider> providers = new ArrayList<TTSocialProvider>();
		try {
			con = getConnection();
			st = con.prepareStatement(SQL);
			// 0 yeni 1 locklı 2 bitti
			// statusu 2 den buyuk olanlar bu islem yapilmis anlamndadir
			st.setInt(1, 1);
			st.setInt(2, limit);
			rs = st.executeQuery();
			TTSocialProvider provider = null;
			while (rs.next()) {
				try {
					provider = new TTSocialProvider(rs);
				} catch (Exception e) {
					log.error("Error", e);
				}

				if (provider != null) {
					providers.add(provider);
				}
			}
			if (providers.size() > 0) {
				return providers;
			}
		} catch (Exception e) {
			log.error("Error", e);
		} finally {
			close(rs);
			close(st);
			close(con);
		}
		return null;
	}

	public static List<TTSocialProvider> getAllProviderList() {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM " + TBL_USERS_SOCIALPROVIDER
				+ " ORDER BY user_id desc";
		List<TTSocialProvider> providers = new ArrayList<TTSocialProvider>();
		try {
			con = getConnection();
			st = con.prepareStatement(SQL);
			// 0 yeni 1 locklı 2 bitti
			// statusu 2 den buyuk olanlar bu islem yapilmis anlamndadir
			rs = st.executeQuery();
			TTSocialProvider provider = null;
			while (rs.next()) {
				try {
					provider = new TTSocialProvider(rs);
				} catch (Exception e) {
					log.error("Error", e);
				}

				if (provider != null) {
					providers.add(provider);
				}
			}
			if (providers.size() > 0) {
				return providers;
			}
		} catch (Exception e) {
			log.error("Error", e);
		} finally {
			close(rs);
			close(st);
			close(con);
		}
		return null;
	}

	public static boolean changeUserStatus(int userId, int status) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String SQL = "UPDATE  " + TBL_USERS + " SET saved=? WHERE id=?";
		try {
			con = getConnection();
			st = con.prepareStatement(SQL);
			st.setInt(1, status);
			st.setInt(2, userId);
			return st.execute();
		} catch (Exception e) {
			log.error("Error", e);
		} finally {
			close(rs);
			close(st);
			close(con);
		}
		return false;
	}

	public static boolean changeSocialProviderStatus(String providerId,
			String provider, int status) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String SQL = "UPDATE  " + TBL_USERS_SOCIALPROVIDER
				+ " SET status=? WHERE oauth_uid=? and oauth_provider=?";
		try {
			con = getConnection();
			st = con.prepareStatement(SQL);
			st.setInt(1, status);
			st.setString(2, providerId);
			st.setString(3, provider);
			return st.execute();
		} catch (Exception e) {
			log.error("Error", e);
		} finally {
			close(rs);
			close(st);
			close(con);
		}
		return false;
	}

	public static boolean unlockSocialProviderStatus(
			List<TTSocialProvider> providers, int status) {
		if (providers != null && providers.size() > 0) {
			Connection con = null;
			PreparedStatement st = null;
			ResultSet rs = null;
			String SQL = "UPDATE  " + TBL_USERS_SOCIALPROVIDER
					+ " SET status=? WHERE oauth_uid=? and oauth_provider=?";
			try {
				con = getConnection();
				st = con.prepareStatement(SQL);
				for (int i = 0; i < providers.size(); i++) {
					// 0 yeni
					// 1 locklı
					// 2 bitti
					st.setInt(1, status);
					st.setString(2, providers.get(i).getOauth_uid());
					st.setString(3, providers.get(i).getOauth_provider());
					st.addBatch();
				}
				st.executeBatch();
				return true;
			} catch (Exception e) {
				log.error("Error", e);
			} finally {
				close(rs);
				close(st);
				close(con);
			}
		}
		return false;
	}

	public static boolean lockSocialProviderStatus(
			List<TTSocialProvider> providers) {
		if (providers != null && providers.size() > 0) {
			Connection con = null;
			PreparedStatement st = null;
			ResultSet rs = null;
			String SQL = "UPDATE  " + TBL_USERS_SOCIALPROVIDER
					+ " SET status=? WHERE oauth_uid=? and oauth_provider=?";
			try {
				con = getConnection();
				st = con.prepareStatement(SQL);
				for (int i = 0; i < providers.size(); i++) {
					st.setInt(1, 1);
					st.setString(2, providers.get(i).getOauth_uid());
					st.setString(3, providers.get(i).getOauth_provider());
					st.addBatch();
				}
				st.executeBatch();
				return true;
			} catch (Exception e) {
				log.error("Error", e);
			} finally {
				close(rs);
				close(st);
				close(con);
			}
		}
		return false;
	}

	public static List<TTSocialProvider> getUserSocialProviderList(int userId) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM " + TBL_USERS_SOCIALPROVIDER
				+ " WHERE user_id = ?";
		List<TTSocialProvider> providers = new ArrayList<TTSocialProvider>();
		try {
			con = getConnection();
			st = con.prepareStatement(SQL);
			st.setInt(1, userId);
			rs = st.executeQuery();
			TTSocialProvider provider = null;
			while (rs.next()) {
				try {
					provider = new TTSocialProvider(rs);
				} catch (Exception e) {
					log.error("Error", e);
				}

				if (provider != null) {
					providers.add(provider);
				}
			}
			if (providers.size() > 0) {
				return providers;
			}
		} catch (Exception e) {
			log.error("Error", e);
		} finally {
			close(rs);
			close(st);
			close(con);
		}
		return null;
	}

	public static HashMap<String, String> getFQmappingList() {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM " + TBL_FQ_CAT_MAPPING;
		HashMap<String, String> result = new HashMap<String, String>();
		result.put(Constants.WEEKINMILISECOND_MAP, System.currentTimeMillis()
				+ "");
		try {
			con = getConnection();
			st = con.prepareStatement(SQL);
			rs = st.executeQuery();
			while (rs.next()) {
				result.put(rs.getString("fq_category_name"),
						rs.getString("fb_category_name"));
			}
			if (result.size() > 1) {
				return result;
			}
		} catch (Exception e) {
			log.error("Error", e);
		} finally {
			close(rs);
			close(st);
			close(con);
		}
		return null;
	}

	public static HashMap<String, FoursquareWeight> getFQWeightList() {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM " + TBL_FQ_CAT_WEIGHT_SCORE;
		HashMap<String, FoursquareWeight> result = new HashMap<String, FoursquareWeight>();
		FoursquareWeight f = new FoursquareWeight();
		f.setCategoryName(System.currentTimeMillis() + "");
		result.put(Constants.WEEKINMILISECOND_MAP, f);
		try {
			con = getConnection();
			st = con.prepareStatement(SQL);
			rs = st.executeQuery();
			while (rs.next()) {
				result.put(rs.getString("category_name"), new FoursquareWeight(
						rs));
			}
			if (result.size() > 1) {
				return result;
			}
		} catch (Exception e) {
			log.error("Error", e);
		} finally {
			close(rs);
			close(st);
			close(con);
		}
		return null;
	}

}
