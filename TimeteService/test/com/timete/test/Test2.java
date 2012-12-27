package com.timete.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.timete.utils.MySQLUtils;

public class Test2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = null;
		try {
			// exceli csv e cevir headerları sil virgulleri ; yap
			//  sayı olacak yerleri sayı yap sadcece
			List<String[]> fqRows = new ArrayList<String[]>();
			br = new BufferedReader(new FileReader(new File(
					"D:\\fq_cat_score.csv")));
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] cols = readRowFQCatScore(line);
				fqRows.add(cols);
			}
			MySQLUtils.insertFQCategoriesScore(fqRows);
			System.err.println();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
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

}
