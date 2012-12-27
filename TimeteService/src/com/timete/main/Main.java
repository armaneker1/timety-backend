package com.timete.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.neo4j.graphdb.GraphDatabaseService;

import com.timete.neo4j.constants.Contants.USER_TYPE;
import com.timete.neo4j.models.Category;
import com.timete.neo4j.models.User;
import com.timete.neo4j.services.CategoryService;
import com.timete.neo4j.services.GroupService;
import com.timete.neo4j.services.UserService;
import com.timete.neo4j.utils.Neo4jUtils;
import com.timete.services.TTServiceRegister;
import com.timete.utils.Constants;
import com.timete.utils.MySQLUtils;
import com.timete.utils.PropertyUtils;
import com.timete.utils.PropertyUtils.Property;

public class Main {

	private static Logger log = Logger.getLogger(Main.class.getName());
	public static int registerDelay = 1;
	public static boolean dbStarted = false;

	/*
	 * Init
	 */

	private static String usr_path = PropertyUtils.getProperty("conf.path")
			+ "user_init_.txt";
	private static String fb_cat_path = PropertyUtils.getProperty("conf.path")
			+ "category_init.txt";

	private static String fq_cat_path = PropertyUtils.getProperty("conf.path")
			+ "fq_cat_init.csv";

	private static String fq_cat_score_path = PropertyUtils
			.getProperty("conf.path") + "fb_cat_score.csv";

	public static void main(String[] args) {

		/*
		 * Init
		 */
		if (getInit()) {
			log.info("Init starting");
			init();
			log.info("Init ended");
		} else {
			/*
			 * start db
			 */
			log.info("db starting");
			@SuppressWarnings("unused")
			GraphDatabaseService db = Neo4jUtils.graphDb;
		}

		try {
			UserService.getUser("", "");
			CategoryService.getCategoryL1("", "");
			GroupService.getGroup("", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*
		 * Social Provider service start
		 */
		log.info("Register Service started");
		Timer timer = new Timer();
		long delay = registerDelay * 1000;
		timer.schedule(new RegisterTask(), 0, delay);
	}

	/*
	 * Init methods
	 */
	public static boolean getInit() {
		try {
			File f = new File(
					PropertyUtils.getProperty(Property.NE4J_DBPATH.value));
			if (f.exists()) {
				if (f.listFiles().length < 1) {
					return true;
				}
			} else {
				f.mkdirs();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
		return false;
	}

	public static void init() {

		File usr_file = new File(usr_path);
		File fb_cat_file = new File(fb_cat_path);
		File fq_cat_file = new File(fq_cat_path);
		BufferedReader reader = null;
		try {
			// exceli csv e cevir headerları sil virgulleri ; yap
			//

			List<String[]> fbRows = new ArrayList<String[]>();
			List<String[]> fqRows = new ArrayList<String[]>();
			reader = new BufferedReader(new FileReader(fq_cat_file));
			String line = "";
			String[] pCols = new String[7];
			while ((line = reader.readLine()) != null) {
				String[] cols = readRowFQCat(line, pCols);
				if (null != cols[6] && cols[6].length() > 0)
					fbRows.add(cols);
				fqRows.add(cols);
			}

			List<String[]> last = new ArrayList<String[]>();
			List<String> tekrar = new ArrayList<String>();
			for (int i = 0; i < fbRows.size(); i++) {
				String[] cols = fbRows.get(i);
				String[] fqs = cols[6].split(",");
				for (int j = 0; j < fqs.length; j++) {
					/*
					 * "-" ler bi diiyi ifade ediyor
					 */
					if (fqs[j].contains("-")) {
						try {
							String[] arr = fqs[j].split("-");
							int start = Integer.parseInt(arr[0]);
							int end = Integer.parseInt(arr[1]);
							if (end < start) {
								int tmp = start;
								start = end;
								end = tmp;
							}
							String[] tmp = new String[fqs.length
									+ (end - start)];
							for (int ij = 0; ij < fqs.length; ij++) {
								if (ij != j)

									tmp[ij] = fqs[ij];
								else
									tmp[ij] = start + "";
							}
							for (int ij = fqs.length; ij < tmp.length; ij++) {
								tmp[ij] = (++start) + "";
							}
							fqs = tmp;
						} catch (Exception e) {
							log.error("Error", e);
						}

					}
					String[] ele = new String[3];
					String[] ele2 = new String[3];
					String[] oldEle = new String[7];
					boolean check = false;
					int indx = -1;
					try {
						indx = Integer.parseInt(fqs[j]);
						indx--;
						if (indx < fqRows.size() && indx >= 0) {
							oldEle = fqRows.get(indx);
							if (oldEle[3] != null && oldEle[3].length() > 0) {
								ele[0] = oldEle[3];
								if (oldEle[2] != null && oldEle[2].length() > 0) {
									ele2[0] = oldEle[2];
									check = true;
								}
							} else if (oldEle[2] != null
									&& oldEle[2].length() > 0) {
								ele[0] = oldEle[2];
							} else if (oldEle[1] != null
									&& oldEle[1].length() > 0) {
								ele[0] = oldEle[1];
							}
							if (cols[5] != null && cols[5].length() > 0) {
								ele[1] = cols[5];
								if (check) {
									ele2[1] = cols[5];
								}
							} else if (cols[4] != null && cols[4].length() > 0) {
								ele[1] = cols[4];
								if (check) {
									ele2[2] = cols[4];
								}
							}
							if (check) {
								if (!tekrar.contains(ele2[0])
										&& ele2[0] != null && ele2[1] != null) {
									last.add(ele2);
									tekrar.add(ele2[0]);
								}
								check = false;
							}
							if (!tekrar.contains(ele[0]) && ele[0] != null
									&& ele[1] != null) {
								last.add(ele);
								tekrar.add(ele[0]);
							}
						}
					} catch (Exception e) {
						System.err.println(cols[0] + " " + j);
						e.printStackTrace();
					}
				}
			}

			MySQLUtils.insertFQCategories(last);

		} catch (Exception e) {
			log.info("Error", e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		reader = null;
		try {
			// exceli csv e cevir headerları sil virgulleri ; yap
			// sayı olacak yerleri sayı yap sadcece
			List<String[]> fqRows = new ArrayList<String[]>();
			reader = new BufferedReader(new FileReader(fq_cat_score_path));
			String line = "";
			while ((line = reader.readLine()) != null) {
				String[] cols = readRowFQCatScore(line);
				fqRows.add(cols);
			}
			MySQLUtils.insertFQCategoriesScore(fqRows);

		} catch (Exception e) {
			log.info("Error", e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		reader = null;
		try {
			if (fb_cat_file.exists()) {
				reader = new BufferedReader(new FileReader(fb_cat_file));
				String line = "";
				List<String> list = new ArrayList<String>();
				List<String> list2 = new ArrayList<String>();
				List<Category> cats = new ArrayList<Category>();
				List<Category> subCats = new ArrayList<Category>();
				Category parent = null;
				int i = 0;
				int j = 0;
				while ((line = reader.readLine()) != null) {
					String[] sp = line.split("\t");
					String cat = sp[0];
					String sbCat = sp[1];
					if (!list.contains(cat)) {
						list.add(cat);
						parent = new Category(i, cat, Constants.FACEBOOK, null);
						i++;
						cats.add(parent);
					}
					if (!list2.contains(sbCat)) {
						Category c = new Category(j, sbCat, Constants.FACEBOOK,
								parent);
						// MySQLUtils.insertCategoryRef(cat, sbCat, 10);
						j++;
						subCats.add(c);
					}
				}
				if (CategoryService.insertCategoryL1(cats)) {
					log.info("level1 categories are inserted to neo4j");
				} else {
					log.info("level1 categories couldn't be inserted to neo4j");
				}
				if (CategoryService.insertCategoryL2(subCats)) {
					log.info("level2 categories are inserted to neo4j");
				} else {
					log.info("level2 categories couldn't be inserted to neo4j");
				}
			}
		} catch (Exception e) {
			log.info("Error", e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		reader = null;
		try {
			if (usr_file.exists()) {
				reader = new BufferedReader(new FileReader(usr_file));
				String line = "";

				while ((line = reader.readLine()) != null) {
					String[] sp = line.split("\t");
					String id = sp[0];
					String userName = sp[1];
					User u = new User(Integer.parseInt(id), userName,"","",USER_TYPE.NORMAL);
					UserService.insertUser(u);
				}
			}
		} catch (Exception e) {
			log.info("Error", e);
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String[] readRowFQCat(String line, String[] pCols) {
		int idx = -1;
		String[] cols = new String[7];
		int i = 0;
		while ((idx = line.indexOf(",")) >= 0 && i < 6) {
			cols[i] = line.substring(0, idx);
			if (null == cols[i] || cols[i].length() < 1
					&& (i == 1 || i == 2 || i == 4 || i == 5)) {
				cols[i] = pCols[i];
			} else if (i == 1 || i == 2 || i == 4 || i == 5) {
				pCols[i] = cols[i];
			}
			if (pCols[i] != null)
				pCols[i] = pCols[i].replace(";", ",");
			if (cols[i] != null)
				cols[i] = cols[i].replace(";", ",");
			line = line.substring(idx + 1);
			i++;
		}
		if (line != null && line.length() > 0 && line.startsWith("\"")) {
			line = line.substring(1, line.length() - 1);
		}
		if (line != null) {
			line = line.replaceAll(" ", "");
			line = line.replace(";", ",");
		}
		cols[6] = line;
		return cols;
	}

	public static String[] readRowFQCatScore(String line) {
		int idx = -1;
		String[] cols = new String[7];
		int i = 0;
		while ((idx = line.indexOf(",")) >= 0 && i < 6) {
			cols[i] = line.substring(0, idx);
			if (cols[i] != null)
				cols[i] = cols[i].replace(";", ",");
			line = line.substring(idx + 1);
			i++;
		}
		if (line != null && line.length() > 0 && line.startsWith("\"")) {
			line = line.substring(1, line.length() - 1);
		}
		if (line != null) {
			line = line.replace(";", ",");
		}
		cols[6] = line;
		return cols;
	}

	/*
	 * Tasklar
	 */
	public static class RegisterTask extends TimerTask {

		@Override
		public void run() {
			if (Neo4jUtils.dbStatus) {
				Thread register = new Thread(new TTServiceRegister());
				register.start();
			} else {
				log.error("DB not started");
			}
		}

	}
}
