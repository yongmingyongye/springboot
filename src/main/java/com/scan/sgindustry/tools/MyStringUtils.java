package com.scan.sgindustry.tools;

public class MyStringUtils {
    
    private final static int[] sizeTable = {9, 99, 999, 9999, 99999, 999999, 9999999,99999999, 999999999, Integer.MAX_VALUE};
	
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
	
	/**
	 * 字符串自增1，并返回原字符串相同长度的字符串
	 * @param str
	 * @return
	 */
	public static String stringAutoincrement(String str) throws Exception {
	    int length = str.length();
	    int result = Integer.parseInt(str) + 1;
	    int intSize = stringSize(result);
	    StringBuilder sb = new StringBuilder();
	    for(int i = 0; i < (length - intSize); i++) {
	        sb.append("0");
	    }
	    sb.append(result);
	    return sb.toString();
	}
	
	/**
	 * 计算整数的位数
	 * @param x
	 * @return
	 */
	public static int stringSize(int x) {
	    for(int i = 0; ;i++) {
	        if(x <= sizeTable[i]) {
	            return i + 1;
	        }
	    }
	}

}
