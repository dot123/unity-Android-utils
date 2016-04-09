package com.unity.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * 
 * ��Home�����˳�Ӧ�ú�����������Ӧ���޷����ص�����ҳ��������
 * 
 * @author M
 * 
 */

public class BaseActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���������APP���ذ�װ�󣬵��ֱ�Ӵ򿪣�����Ӧ�ú󣬰���HOME�����ٴε�������ϵ�Ӧ�ã�������һ���µ�Ӧ������
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
