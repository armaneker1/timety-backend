package com.timete.utils;

import java.io.InputStream;

public class FileUtils {

	public static InputStream  getResourceIS(String fileName) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		return loader.getResourceAsStream(fileName);
	}
}
