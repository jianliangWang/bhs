package com.wjl.system.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SignUtil {

    public static String MD5(String source) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source.getBytes());
            byte[] b = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte c : b) {
                int val = ((int) c) & 0xff;
                if (val < 16)
                    sb.append("0");
                sb.append(Integer.toHexString(val));
            }
            return sb.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String MD5StrLower(String str){
        return MD5(str).toLowerCase();
    }

    public static void main(String[] args) {
        System.out.println(MD5StrLower("hello"));
    }
}
