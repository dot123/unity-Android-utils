package com.unity.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * 
 * 按Home按键退出应用后重新启动该应用无法返回到最后打开页面解决方案
 * 
 * @author M
 * 
 */

public class BaseActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 完美解决：APP下载安装后，点击直接打开，启动应用后，按下HOME键，再次点击桌面上的应用，会重启一个新的应用问题
		if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
			// Activity was brought to front and not created,This finishing this will get us to the last viewed activity
			finish();
			return;
		}

		Intent intent = new Intent(BaseActivity.this, MainActivity.class);
		BaseActivity.this.startActivity(intent);
		finish();
	}
}
