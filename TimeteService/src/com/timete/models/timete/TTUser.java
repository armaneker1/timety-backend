package com.timete.models.timete;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.timete.utils.Constants.ACCOUNT_TYPE;
import com.timete.utils.MySQLUtils;

@SuppressWarnings("serial")
public class TTUser implements Serializable {

	public int id;
	public String email;
	public String userName;
	public String firstName;
	public String lastName;
	public Date birthDate;
	public String hometown;
	public int status;
	public ACCOUNT_TYPE type; // normal account 0 verified account 1
	public List<TTSocialProvider> socialProviders;

	private static Logger log = Logger.getLogger(TTUser.class.getName());

	public TTUser() {

	}

	public TTUser(ResultSet rs) throws SQLException {
		id = rs.getInt("id");
		email = rs.getString("email");
		userName = rs.getString("userName");
		firstName = rs.getString("firstName");
		lastName = rs.getString("lastName");
		hometown = rs.getString("hometown");
		status = rs.getInt("status");
		type = ACCOUNT_TYPE.valueOf(rs.getInt("type"));
		try {
			birthDate = rs.getDate("birthdate");
		} catch (Exception e) {
			log.warn("Warn", e);
		}

	}

	/*
	 * Custom Getter & Setter
	 */
	public List<TTSocialProvider> getSocialProviders() {
		if (null == socialProviders || socialProviders.size() < 1) {
			socialProviders = MySQLUtils.getUserSocialProviderList(id);
		}
		return socialProviders;
	}

	/*
	 * Getter & Setter
	 */
	public void setSocialProviders(List<TTSocialProvider> socialProviders) {
		this.socialProviders = socialProviders;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ACCOUNT_TYPE getType() {
		return type;
	}

	public void setType(ACCOUNT_TYPE type) {
		this.type = type;
	}

	
}
