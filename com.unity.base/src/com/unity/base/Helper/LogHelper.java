package com.unity.base.Helper;

import android.util.Log;

/**
 * 
 * @author M
 * 
 */

public class LogHelper {

	public static boolean isAllowLog = true;

	public static void d(String paramString1, String paramString2) {
		if (!isAllowLog)
			return;
		Log.d(paramString1, paramString2);
	}

	public static void e(String paramString1, String paramString2) {
		if (!isAllowLog)
			return;
		Log.e(paramString1, paramString2);
	}

	public static void i(String paramString1, String paramString2) {
		if (!isAllowLog)
			return;
		Log.i(paramString1, paramString2);
	}

	public static void v(String paramString1, String paramString2) {
		if (!isAllowLog)
			return;
		Log.v(paramString1, paramString2);
	}

	public static void w(String paramString1, String paramString2) {
		if (!isAllowLog)
			return;
		Log.w(paramString1, paramString2);
	}
}
