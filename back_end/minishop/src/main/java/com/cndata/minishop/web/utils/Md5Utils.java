package com.cndata.minishop.web.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类。
 * @author Administrator
 * 2016-07-15
 */
public class Md5Utils {

	/**
	 * 对给定字符串使用md5算法加密，返回加密后的字符串。
	 * @param rawString 需要加密的字符串
	 * @return 加密后的字符串
	 */
	public static String encode(String rawString){
		String digest = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(rawString.getBytes());
			byte[] bytes = md.digest();
			digest = byte2hex(bytes);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		return digest;
	}

	private static String byte2hex(byte[] bytes) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < bytes.length; n++) {
			stmp = (java.lang.Integer.toHexString(bytes[n] & 0XFF));
			if (stmp.length() == 1){
				hs = hs + "0" + stmp;
			}else{
				hs = hs + stmp;
			}
		}
		return hs;
	}
}
