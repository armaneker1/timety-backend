package com.timete.models.fq;

import fi.foyt.foursquare.api.entities.Category;
import fi.foyt.foursquare.api.entities.Checkin;

/**
 * 
 * @author mehmet
 */
public class FoursquareVenue {

	public String id;
	public String name;
	public int checkinCount = 1;
	public String[] categoryIDs;
	public String[] categoryNames;

	public FoursquareVenue() {
	}

	public static FoursquareVenue populate(Checkin checkin) {
		FoursquareVenue venue = new FoursquareVenue();

		venue.id = checkin.getVenue().getId();
		venue.name = checkin.getVenue().getName();
		venue.categoryIDs = new String[checkin.getVenue().getCategories().length];
		venue.categoryNames = new String[checkin.getVenue().getCategories().length];
		int a = 0;
		for (Category cat : checkin.getVenue().getCategories()) {
			venue.categoryIDs[a] = cat.getId();
			venue.categoryNames[a] = cat.getName();
			a++;
		}
		return venue;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCheckinCount() {
		return checkinCount;
	}

	public void setCheckinCount(int checkinCount) {
		this.checkinCount = checkinCount;
	}

	public String[] getCategoryIDs() {
		return categoryIDs;
	}

	public void setCategoryIDs(String[] categoryIDs) {
		this.categoryIDs = categoryIDs;
	}

	public String[] getCategoryNames() {
		return categoryNames;
	}

	public void setCategoryNames(String[] categhoryNames) {
		this.categoryNames = categhoryNames;
	}

}
