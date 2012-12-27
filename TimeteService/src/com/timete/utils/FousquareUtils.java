package com.timete.utils;

import java.util.HashMap;

import com.timete.models.fq.FoursquareWeight;

public class FousquareUtils {

	public static double getFoursquareWeight(String catName, int checkinCount) {
		HashMap<String, FoursquareWeight> FQCategoryWeight = Constants
				.getFQCategoryWeight();
		if (FQCategoryWeight != null && FQCategoryWeight.size() > 0) {
			FoursquareWeight f = FQCategoryWeight.get(catName);
			if (f != null) {
				// gecici olarak kaldır
				// if (f.getCheckin() <= checkinCount)
				double a = f.getTotal() / f.getCheckin();
				return (a * checkinCount) + f.getConstant();

			}
		}
		return 0d;
	}

}
