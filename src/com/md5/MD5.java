package com.md5;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5 {
	public static final String KEY_MD5="MD5";
	public static String getResult(String inputStr) {
		System.out.println("加密前的数据为："+inputStr);
		BigInteger bigInteger=null;
		try {
			MessageDigest md=MessageDigest.getInstance(KEY_MD5);
			byte[] inputData = inputStr.getBytes();
			md.update(inputData);
			bigInteger = new BigInteger(md.digest());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("MD5加密后的数据为："+bigInteger.toString(20));
		//return inputStr;
		return bigInteger.toString(20);
		//return null;
	}
}
