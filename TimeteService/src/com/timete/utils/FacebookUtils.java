package com.timete.utils;

import java.util.List;

import org.apache.log4j.Logger;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.CategorizedFacebookType;
import com.restfb.types.Page;

public class FacebookUtils {

	
	public String fbUserAccesToken;
	private static Logger log = Logger.getLogger(FacebookUtils.class.getName());

	public List<CategorizedFacebookType> getUserLikes() {
		try {
			FacebookClient facebookClient = new DefaultFacebookClient(
					fbUserAccesToken);
			Connection<CategorizedFacebookType> userLikes = facebookClient.fetchConnection(
					"me/likes", CategorizedFacebookType.class);
			return userLikes.getData();
		} catch (Exception e) {
			log.error(e.toString(), e);
		}
		return null;
	}

	public static List<CategorizedFacebookType> getUserLikes(String fbUserAccesToken) {
		try {
			FacebookClient facebookClient = new DefaultFacebookClient(
					fbUserAccesToken);
			Connection<CategorizedFacebookType> userLikes = facebookClient.fetchConnection(
					"me/likes", CategorizedFacebookType.class);
			return userLikes.getData();
		} catch (Exception e) {
			log.error(e.toString(), e);
		}
		return null;
	}

	public static Page getPage(String id) {
		try {
			FacebookClient facebookClient = new DefaultFacebookClient();
			Page page = facebookClient.fetchObject(id, Page.class);
			return page;
		} catch (Exception e) {
			log.error(e.toString(), e);
		}
		return null;
	}

}
