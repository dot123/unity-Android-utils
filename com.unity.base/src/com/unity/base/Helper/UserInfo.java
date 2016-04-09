package com.unity.base.Helper;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

public class UserInfo {

	private String id;
	private String name;
	private String sex;
	private String nick;
	private static String TAG = "UserInfo";

	public static UserInfo parse(String jsonString) {
		UserInfo userInfo = new UserInfo();
		if (!TextUtils.isEmpty(jsonString)) {
			try {

				JSONObject jsonObj = new JSONObject(jsonString);
				JSONObject data = jsonObj.optJSONObject("data");
				if (data.has("uid")) {
					String id = data.getString("uid");
					LogHelper.e(TAG, "uid:" + id);
				}

			} catch (JSONException e) {
				e.printStackTrace();
				return null;
			}

		}
		return userInfo;
	}

	/**
	 *  分割字符串
	 * @param str
	 * @param split
	 * @return
	 */
	public static String[] convertStrToArray(String str, String split) {
		String[] strArray = null;
		strArray = str.split(split);
		return strArray;
	}

	public boolean isValid() {
		return !TextUtils.isEmpty(id);
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
}
