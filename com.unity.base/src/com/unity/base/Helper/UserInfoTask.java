package com.unity.base.Helper;

import android.content.Context;

public class UserInfoTask {

	private static final String TAG = "UserInfoTask";
	private static final String APP_SERVER_URL_GET_USER = "";
	private static final String APP_SERVER_URL_POST_USER = "";
	private SdkHttpTask sSdkHttpTask;

	public static UserInfoTask newInstance() {
		return new UserInfoTask();
	}

	public void doGetRequest(Context context, String data,
			final UserInfoListener listener) {
		String url = APP_SERVER_URL_GET_USER + data;

		// 如果存在，取消上一次请求
		if (sSdkHttpTask != null) {
			sSdkHttpTask.cancel(true);
		}

		// 新请求
		sSdkHttpTask = new SdkHttpTask(context);

		sSdkHttpTask.doGet(new SdkHttpListener() {

			@Override
			public void onResponse(String response) {

				LogHelper.e(TAG, "onResponse=" + response);
				UserInfo userInfo = UserInfo.parse(response);
				listener.onGotUserInfo(userInfo);
				sSdkHttpTask = null;
			}

			@Override
			public void onCancelled() {
				listener.onGotUserInfo(null);
				sSdkHttpTask = null;
			}

		}, url);
	}

	public void doPostRequest(Context context, String params,
			final UserInfoListener listener) {
		// 如果存在，取消上一次请求
		if (sSdkHttpTask != null) {
			sSdkHttpTask.cancel(true);
		}

		// 新请求
		sSdkHttpTask = new SdkHttpTask(context);

		sSdkHttpTask.doPost(new SdkHttpListener() {

			@Override
			public void onResponse(String response) {
				// TODO Auto-generated method stub
				LogHelper.e(TAG, "onResponse=" + response);
				UserInfo userInfo = UserInfo.parse(response);
				listener.onGotUserInfo(userInfo);
				sSdkHttpTask = null;
			}

			@Override
			public void onCancelled() {
				// TODO Auto-generated method stub
				listener.onGotUserInfo(null);
				sSdkHttpTask = null;
			}
		}, params, APP_SERVER_URL_POST_USER);
	}

	public boolean doCancel() {
		return (sSdkHttpTask != null) ? sSdkHttpTask.cancel(true) : false;
	}
}
