package com.unity.base.Helper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class SignUtil {

	public static final String Md5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		char str[];
		byte strTemp[] = s.getBytes();
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte md[] = mdTemp.digest();
			int j = md.length;
			str = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}

			return new String(str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 把字节数组转换成16进制字符串
	 * 
	 * @param bArray
	 * @return
	 */
	public static final String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * MD5加密字符串，返回加密后的16进制字符串
	 * 
	 * @param origin
	 * @return
	 */
	public static String MD5EncodeToHex(String origin) {
		return bytesToHexString(MD5Encode(origin));
	}

	/**
	 * MD5加密字符串，返回加密后的字节数组
	 * 
	 * @param origin
	 * @return
	 */
	public static byte[] MD5Encode(String origin) {
		return MD5Encode(origin.getBytes());
	}

	/**
	 * MD5加密字节数组，返回加密后的字节数组
	 * 
	 * @param bytes
	 * @return
	 */
	public static byte[] MD5Encode(byte[] bytes) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			return md.digest(bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return new byte[0];
		}
	}

	/**
	 * 
	 * @return {"key":"value"}
	 */
	public static final String convertToJSON() {
		JSONObject jsonObj = new JSONObject();

		try {
			jsonObj.put("key", "value");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonObj.toString();
	}

	/**
	 * 
	 * @return [key=value]
	 */
	public static String getParams() {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();

		params.add(new BasicNameValuePair("key", "value"));

		return params.toString();
	}

	/**
	 * 
	 * @param /v3/user/get_info
	 * @return %2Fv3%2Fuser%2Fget_info
	 */
	public static String convertToURLEncoded(String params) {
		String encode = "";
		try {
			encode = URLEncoder.encode(params, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return encode;
	}

	/**
	 * 
	 * @param %2Fv3%2Fuser%2Fget_info
	 * @return /v3/user/get_info
	 */
	public static String convertToURLDecoder(String params) {
		String decode = "";
		try {

			decode = URLDecoder.decode(params, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return decode;
	}
}
