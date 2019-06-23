package com.scan.sgindustry.tools;

public class CompareUtils {
	
	/**
	 * 判断两个字符串是否相等
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean compareStr(String str1, String str2) {
		char[] byte1 = str1.toCharArray();
		char[] byte2 = str2.toCharArray();
		if(byte1.length != byte2.length) {
			return false;
		}
		byte ret = 0;
		for(int i = 0; i < byte1.length; i++) {
			ret |= byte1[i] ^ byte2[i];
		}
		return ret == 0;
	}

}
