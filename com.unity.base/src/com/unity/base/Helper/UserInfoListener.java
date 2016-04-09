package com.unity.base.Helper;

/**
 * 此接口由应用客户端与应用服务器协商决定。
 */
public interface UserInfoListener {

	public void onGotUserInfo(UserInfo userInfo);

}
