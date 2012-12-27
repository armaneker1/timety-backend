package com.timete.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.neo4j.graphdb.Node;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.CategorizedFacebookType;
import com.restfb.types.Page;
import com.timete.models.timete.TTSocialProvider;
import com.timete.neo4j.constants.Contants.USER_TYPE;
import com.timete.neo4j.models.Category;
import com.timete.neo4j.models.User;
import com.timete.neo4j.services.CategoryService;
import com.timete.neo4j.services.UserService;
import com.timete.services.TTServiceRegister;
import com.timete.utils.FacebookUtils;
import com.timete.utils.MySQLUtils;

public class Test {

	public static void main(String[] args) {
		Thread register = new Thread(new TTServiceRegister());
		register.start();
	}

	public static void fbTest() {
		try {
			List<TTSocialProvider> list = MySQLUtils.getAllProviderList();
			for (int j = 0; j < list.size(); j++) {
				try {

					TTSocialProvider provider = list.get(j);
					String aToken = provider.getOauth_token();
					List<CategorizedFacebookType> likes = FacebookUtils
							.getUserLikes(aToken);

					List<CategorizedFacebookType> unknownCats = new ArrayList<CategorizedFacebookType>();
					List<CategorizedFacebookType> knownCats = new ArrayList<CategorizedFacebookType>();
					for (int i = 0; i < likes.size(); i++) {
						try {
							CategorizedFacebookType fbEvent = likes.get(i);
							Node category = CategoryService.getCategoryL2(
									Category.nameString, fbEvent.getCategory());
							if (category == null) {
								unknownCats.add(fbEvent);
							} else {
								knownCats.add(fbEvent);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					System.err.println("Unknow Categories");
					try {
						for (int i = 0; i < unknownCats.size(); i++) {
							CategorizedFacebookType t = unknownCats.get(i);

							FacebookClient facebookClient = new DefaultFacebookClient(
									provider.getOauth_token());
							try {
								Page page = facebookClient.fetchObject(
										t.getId(), Page.class);
								System.err.println("\n\nId       : "
										+ t.getId());
								System.err.println("Name     : " + t.getName());
								System.err.println("Category : "
										+ t.getCategory());
								System.err.println("Type     : " + t.getType());
								System.err.println("MetaData : "
										+ t.getMetadata());
								System.err.println("Page      : "
										+ page.getDescription());
							} catch (Exception e) {
								// System.err.println("Page      : unknown :"
								// + e.toString());
							}

						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					System.err.println("Categories");
					try {
						for (int i = 0; i < unknownCats.size(); i++) {
							CategorizedFacebookType t = unknownCats.get(i);
							FacebookClient facebookClient = new DefaultFacebookClient(
									provider.getOauth_token());
							try {
								Page page = facebookClient.fetchObject(
										t.getId(), Page.class);
								System.err.println("Page      : "
										+ page.getDescription());
							} catch (Exception e) {
								System.err.println("\n\nId       : "
										+ t.getId());
								System.err.println("Name     : " + t.getName());
								System.err.println("Category : "
										+ t.getCategory());
								System.err.println("Type     : " + t.getType());
								System.err.println("MetaData : "
										+ t.getMetadata());
								System.err.println("Page      : unknown :"
										+ e.toString());
							}

						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String usr_path = "D:\\user_init_.txt";

	public static void init() {

		File usr_file = new File(usr_path);
		BufferedReader reader = null;
		try {
			if (usr_file.exists()) {
				reader = new BufferedReader(new FileReader(usr_file));
				String line = "";

				while ((line = reader.readLine()) != null) {
					String[] sp = line.split("\t");
					String id = sp[0];
					String userName = sp[1];
					User u = new User(Integer.parseInt(id), userName, "", "",
							USER_TYPE.NORMAL);
					UserService.insertUser(u);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			UserService.getUser("", "");
			CategoryService.getCategoryL1("", "");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
