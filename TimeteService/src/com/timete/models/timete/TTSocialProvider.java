package com.timete.models.timete;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class TTSocialProvider implements Serializable {

	public int user_id;
	public String oauth_uid;
	public String oauth_provider;
	public String oauth_token;
	public String oauth_token_secret;
	public int status;

	public TTSocialProvider() {

	}

	public TTSocialProvider(ResultSet rs) throws SQLException {
		user_id = rs.getInt("user_id");
		oauth_uid = rs.getString("oauth_uid");
		oauth_token = rs.getString("oauth_token");
		oauth_provider = rs.getString("oauth_provider");
		oauth_token_secret = rs.getString("oauth_token_secret");
		status=rs.getInt("status");
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getOauth_uid() {
		return oauth_uid;
	}

	public void setOauth_uid(String oauth_uid) {
		this.oauth_uid = oauth_uid;
	}

	public String getOauth_provider() {
		return oauth_provider;
	}

	public void setOauth_provider(String oauth_provider) {
		this.oauth_provider = oauth_provider;
	}

	public String getOauth_token() {
		return oauth_token;
	}

	public void setOauth_token(String oauth_token) {
		this.oauth_token = oauth_token;
	}

	public String getOauth_token_secret() {
		return oauth_token_secret;
	}

	public void setOauth_token_secret(String oauth_token_secret) {
		this.oauth_token_secret = oauth_token_secret;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
