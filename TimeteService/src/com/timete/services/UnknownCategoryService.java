package com.timete.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.timete.utils.MySQLUtils;

public class UnknownCategoryService implements Runnable {

	public List<String> unknownCats = new ArrayList<String>();
	private static Logger log = Logger.getLogger(UnknownCategoryService.class
			.getName());
	private static Integer LOCK = 1;

	@Override
	public void run() {
		try {
			synchronized (LOCK) {
				MySQLUtils.insertUnknownCategorys(unknownCats);
			}
		} catch (Exception e) {
			log.error("Error", e);
		}
	}

}
