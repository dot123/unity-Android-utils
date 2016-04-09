package com.unity.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.unity.base.Helper.ResourseUtil;

public class SplashActivity extends Activity {
	// �ӳ�1.5��
	private static final long SPLASH_DELAY_MILLIS = 1500;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���������APP���ذ�װ�󣬵��ֱ�Ӵ򿪣�����Ӧ�ú󣬰���HOME�����ٴε�������ϵ�Ӧ�ã�������һ���µ�Ӧ������
		if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
			// Activity was brought to front and not created,This finishing this will get us to the last viewed activity
			finish();
			return;
		}

		setContentView(ResourseUtil.getLayoutId(this.getPackageName(), "splash"));
		// ʹ��Handler��postDelayed������1.5���ִ����ת��MainActivity
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