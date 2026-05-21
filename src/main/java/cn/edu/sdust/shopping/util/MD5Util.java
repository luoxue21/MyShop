package cn.edu.sdust.shopping.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5加密工具类
 */
public class MD5Util {
    
    /**
     * MD5加密
     */
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }
    
    /**
     * 验证密码
     */
    public static boolean verifyPassword(String inputPassword, String dbPassword) {
        return md5(inputPassword).equals(dbPassword);
    }
}
