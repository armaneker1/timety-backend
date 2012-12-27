package com.timete.models.fq;

import java.sql.ResultSet;

import org.apache.log4j.Logger;

public class FoursquareWeight {

	private static Logger log = Logger.getLogger(FoursquareWeight.class
			.getName());

	public String source;
	public String categoryName;
	public int time = 0;
	public int checkin;
	public double total;
	public double weight;
	public double constant;

	public FoursquareWeight(ResultSet rs) {
		try {
			time = rs.getInt("time");
			checkin = rs.getInt("checkin");
			weight = rs.getDouble("weight");
			total = rs.getDouble("total");
			source = rs.getString("source");
			categoryName = rs.getString("category_name");
			constant=rs.getDouble("constant");
		} catch (Exception e) {
			log.error("Error", e);
		}

	}

	public FoursquareWeight() {
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getCheckin() {
		return checkin;
	}

	public void setCheckin(int checkin) {
		this.checkin = checkin;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getConstant() {
		return constant;
	}

	public void setConstant(double constant) {
		this.constant = constant;
	}
	
	
}
