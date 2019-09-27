package com.unity.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.unity.base.Helper.ResourseUtil;

public class SplashActivity extends Activity {
	// 延迟1.5秒
	private static final long SPLASH_DELAY_MILLIS = 1500;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 完美解决：APP下载安装后，点击直接打开，启动应用后，按下HOME键，再次点击桌面上的应用，会重启一个新的应用问题
		if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
			// Activity was brought to front and not created,This finishing this will get us to the last viewed activity
			finish();
			return;
		}

		setContentView(ResourseUtil.getLayoutId(this.getPackageName(), "splash"));
		// 使用Handler的postDelayed方法，1.5秒后执行跳转到MainActivity
		new Handler().postDelayed(new Runnable() {
			public void run() {
				goHome();
			}
		}, SPLASH_DELAY_MILLIS);
	}

	private void goHome() {
		Intent intent = new Intent(SplashActivity.this, MainActivity.class);
		SplashActivity.this.startActivity(intent);
		this.finish();
	}
}