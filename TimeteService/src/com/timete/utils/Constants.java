package com.timete.utils;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;

import com.timete.models.fq.FoursquareWeight;
import com.timete.neo4j.utils.Neo4jUtils;

public class Constants {

	private static Logger log = Logger.getLogger(Constants.class.getName());

	public final static String FACEBOOK = "facebook";
	public final static Double FACEBOOK_INTEREST_WEIGHT = 10d;
	public final static String TWITTER = "twitter";
	public final static String FOURSQUARE = "foursquare";

	/*
	 * User value
	 */
	public enum ACCOUNT_TYPE {
		NORMAL_ACCOUNT(0), VERIFIED_ACCOUNT(1);

		private int value;

		private ACCOUNT_TYPE(int v) {
			value = v;
		}

		public int getValue() {
			return value;
		}

		public static ACCOUNT_TYPE valueOf(int v) {
			if (v == VERIFIED_ACCOUNT.value) {
				return VERIFIED_ACCOUNT;
			} else {
				return NORMAL_ACCOUNT;
			}
		}
	}

	/*
	 * Foursquare mapping
	 */
	private static HashMap<String, String> FQCategoryMapping;
	private static HashMap<String, FoursquareWeight> FQCategoryWeight;
	public final static long WEEKINMILISECOND = (24 * 60 * 60 * 1000) * 7;
	public final static String WEEKINMILISECOND_MAP = "TIME";

	/*
	 * Neo4j
	 */
	public static Index<Node> rootIndex;

	public static String PROP_ROOT_ID = "root_id";
	public static String PROP_ROOT_USR = "USER_ROOT";
	public static String PROP_ROOT_CAT = "CATEGORY_ROOT";
	public static String PROP_ROOT_GRP = "GROUP_ROOT";
	public static String PROP_INTEREST_WEIGHT = "INTEREST_WEIGHT";

	static {
		/*
		 * Root Index
		 */
		Transaction tx = Neo4jUtils.graphDb.beginTx();
		try {
			rootIndex = Neo4jUtils.graphDb.index().forNodes("ROOT_INDEX");
			tx.success();
		} catch (Exception e) {
			log.error("Error", e);
			tx.failure();
		} finally {
			tx.finish();
		}
	}

	public static HashMap<String, String> getFQCategoryMapping() {
		boolean check = false;
		long time_diff = 0l;
		try {
			time_diff = System.currentTimeMillis()
					- Long.parseLong(FQCategoryMapping
							.get(WEEKINMILISECOND_MAP));
		} catch (Exception e) {
			time_diff = 0l;
			log.error("Error", e);
		}
		if (null == FQCategoryMapping)
			check = true;
		else if (FQCategoryMapping.size() < 1)
			check = true;
		else if (time_diff <= 0 || time_diff > WEEKINMILISECOND)
			check = true;
		if (check) {
			FQCategoryMapping = MySQLUtils.getFQmappingList();
		}
		return FQCategoryMapping;
	}

	public static HashMap<String, FoursquareWeight> getFQCategoryWeight() {
		boolean check = false;
		long time_diff = 0l;
		try {
			time_diff = System.currentTimeMillis()
					- Long.parseLong(FQCategoryWeight.get(WEEKINMILISECOND_MAP)
							.getCategoryName());
		} catch (Exception e) {
			time_diff = 0l;
			log.error("Error", e);
		}
		if (null == FQCategoryWeight)
			check = true;
		else if (FQCategoryWeight.size() < 1)
			check = true;
		else if (time_diff <= 0 || time_diff > WEEKINMILISECOND)
			check = true;
		if (check) {
			FQCategoryWeight = MySQLUtils.getFQWeightList();
		}
		return FQCategoryWeight;
	}

}
