package com.unity.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;

import com.unity.base.Helper.APNUtil;
import com.unity.base.Helper.ExtendDataInfo;
import com.unity.base.Helper.LogHelper;
import com.unity.base.Helper.ProgressUtil;
import com.unity.base.Helper.UserInfo;
import com.unity.base.Helper.UserInfoListener;
import com.unity.base.Helper.UserInfoTask;
import com.unity3d.player.UnityPlayerActivity;

/**
 * 
 * @author M
 * 
 */

public class MainActivity extends UnityPlayerActivity {

	private static String TAG = "MainActivity";
	private static boolean isLogout = true;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);

		// 完美解决：APP下载安装后，点击直接打开，启动应用后，按下HOME键，再次点击桌面上的应用，会重启一个新的应用问题
		if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
			// Activity was brought to front and not created,This finishing this will get us to the last viewed activity
			finish();
			return;
		}
	}

	public void TelephonyInfo() {
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		LogHelper.i(TAG, "getLine1Number " + tm.getLine1Number());
		LogHelper.i(TAG, "getDeviceId " + tm.getDeviceId());
		LogHelper.i(TAG, "getNetworkOperator " + tm.getNetworkOperator());
		LogHelper.i(TAG, "getNetworkOperatorName " + tm.getNetworkOperatorName());
	}

	/**
	 * 进行网络检查
	 */
	public void checkNetwork() {
		// 当前没有拥有网络
		if (false == APNUtil.isNetworkAvailable(this)) {
			AlertDialog.Builder ab = new AlertDialog.Builder(this);
			ab.setMessage("网络未连接,请设置网络");
			ab.setPositiveButton("设置", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Intent intent = new Intent("android.settings.SETTINGS");
					startActivityForResult(intent, 0);
				}
			});
			ab.setNegativeButton("退出", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					System.exit(0);
				}
			});
			ab.show();
		} else {
			//add your code
		}
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GlobalParam.LOGIN: {
				onLogin();
			}
				break;
			case GlobalParam.SWITCH_USER: {
				onSwitchUser();
			}
				break;
			case GlobalParam.PAY: {
				PayInfo info = (PayInfo) msg.obj;
				Recharge(info);
			}
				break;
			case GlobalParam.LOGOUT: {
				onLogout();
			}
				break;
			case GlobalParam.EXIT: {

			}
				break;
			case GlobalParam.SUBMIT_EXTENDDATA: {
				@SuppressWarnings("unused")
				ExtendDataInfo info = (ExtendDataInfo) msg.obj;
			}
				break;
			}
		}
	};

	private void onLogin() {
		// add your code

	}

	@SuppressWarnings("unused")
	private void onLoginSuccess(String uid) {
		isLogout = false;
		LogHelper.e(TAG, uid);
	}

	@SuppressWarnings("unused")
	private void onLoginFail(String msg) {
		LogHelper.e(TAG, msg);
	}

	private void onSwitchUser() {
		isLogout = true;
		LogHelper.e(TAG, "Logout");
	}

	private void onLogout() {
		// add your code
	}

	public void LOGIN() // be called by uinty function
	{
		handler.sendEmptyMessage(GlobalParam.LOGIN);
	}

	public void LOGINOUT() // be called by uinty function
	{
		if (!isLogout) {
			isLogout = true;
			handler.sendEmptyMessage(GlobalParam.LOGOUT);
		}
	}

	public void QUITGAME(String msg) // be called by uinty function
	{
		handler.sendEmptyMessage(GlobalParam.EXIT);
	}
	
	/**
	 * 
	 * @param extString1
	 * @param extString2
	 * @param productName
	 * @param productPrice
	 * @param cpOrderId
	 * @param userName
	 * @param userID
	 * @param userLevel
	 */
	public void PAY(String extString1,
			String extString2,
			String productName, String productPrice, String cpOrderId,
			String userName, String userID, String userLevel) {

		LogHelper.d(TAG, extString1 + "," + productName + "," + productPrice
				+ "," + cpOrderId + "," + userName + "," + userID + ","
				+ userLevel);

		PayInfo info = new PayInfo();
		info.setProductPrice(productPrice);
		info.setProductName(productName + "个钻石");
		info.setProductDesc("productDesc");
		info.setCPorderId(cpOrderId);

		handler.sendMessage(handler.obtainMessage(GlobalParam.PAY, info));
	}

	/**
	 * 提交扩展数据
	 * 
	 * @param userID
	 * @param userName
	 * @param userLevel
	 * @param extString1
	 * @param extString2
	 */
	public void SUBMITEXTENDDATA(String userID, String userName,
			String userLevel, String extString1, String extString2) {

		ExtendDataInfo info = new ExtendDataInfo();
		info.setUserName(userName);
		info.setUserID(userID);
		info.setUserLevel(userLevel);
		LogHelper.d(TAG, extString1 + "," + extString2 + "," + userName + ","
				+ userID + "," + userLevel);

		handler.sendMessage(handler.obtainMessage(
				GlobalParam.SUBMIT_EXTENDDATA, info));
	}

	private void Recharge(PayInfo info) {
		LogHelper.d(TAG, info.toString());
		// add your code
	}

	@SuppressWarnings("unused")
	private void payResult(String msg) {
		LogHelper.e(TAG, msg);
	}

	@SuppressWarnings("unused")
	private void getUserInfo() {

		final UserInfoTask mUserInfoTask = UserInfoTask.newInstance();
		// 提示用户进度
		final ProgressDialog progress = ProgressUtil.show(this, "获取AccessToken", "操作正在进行中...",
				new OnCancelListener() {

					@Override
					public void onCancel(DialogInterface arg0) {
						// TODO Auto-generated method stub
						if (mUserInfoTask != null) {
							mUserInfoTask.doCancel();
						}
					}
				});

		// 请求应用服务器，用AccessToken换取UserInfo
		mUserInfoTask.doGetRequest(this, "data", new UserInfoListener() {

			@Override
			public void onGotUserInfo(UserInfo userInfo) {
				progress.dismiss();
				if (null == userInfo || !userInfo.isValid()) {

				} else {

				}
			}
		});
	}
}
