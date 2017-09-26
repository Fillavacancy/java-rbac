package com.jrbac.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 根据一个密文得到加密后的字符串
 * 
 * @author Administrator
 */
public class PasswordUtil {
    /**
     * 得到一个字符串的加密信息，两次Md5
     * 
     * @param password 明文
     * @return 密文
     */
    public static String getPassword(String password) {
        // 两次md5加密
        return new Md5Hash(new Md5Hash(password)).toString();
    }
}
