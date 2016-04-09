package com.unity.base.Helper;

import android.text.TextUtils;

import com.google.gson.Gson;

public class UserInfoFromJson {

	public static UserInfoFromJson parseJson(String jsonString) {
		UserInfoFromJson userInfo = null;
		if (!TextUtils.isEmpty(jsonString)) {
			try {

				Gson gson = new Gson();
				userInfo = gson.fromJson(jsonString, UserInfoFromJson.class);

				if (null != userInfo && null != userInfo.getUserID()
						&& !"".equals(userInfo.getUserID())) {
					return userInfo;
				}

			} catch (RuntimeException e) {

				e.printStackTrace();
			}

		}
		return null;
	}

	public String userID;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public int getUserState() {
		return userState;
	}

	public void setUserState(int userState) {
		this.userState = userState;
	}

	public int getUserValidStatus() {
		return userValidStatus;
	}

	public void setUserValidStatus(int userValidStatus) {
		this.userValidStatus = userValidStatus;
	}

	public String userName;

	public String languageCode;

	public int userState;

	public int userValidStatus;
}
