package com.ming.common.util.bussiness;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * 类名：SaltUtil<br>
 * 功能：密码加盐工具包<br>
 * 版本：1.0<br>
 * 日期：2017-01-13<br>
 * 说明：暂无
 * @author Ming
 *
 */
public class SaltUtil {
	/**
	 * 获取随机数（16进制）
	 * @return
	 */
	public static String getRandom() {
		Random ranGen = new SecureRandom();
		byte[] aesKey = new byte[32];
		ranGen.nextBytes(aesKey);
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < aesKey.length; i++) {
			String hex = Integer.toHexString(0xff & aesKey[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString().toUpperCase();
	}
	
	/**
	 * 对字符串进行SHA-256加密
	 * 
	 * @param strSrc
	 *            待加密的字符串
	 * @return String 加密后的字符串
	 */
	public static String shaEncrypt(String strSrc) {
		MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes.toUpperCase();
	}
	
	/**
	 * 将加密后的byte数组转换为16位字符串
	 * 
	 * @param bts 待转换的数组
	 * @return 转换后的字符串
	 */
	private static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }
}
