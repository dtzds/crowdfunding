package com.dtz.crowd.util;

import com.dtz.crowd.constant.CrowdConstant;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CrowdUtil {


    public static String md5(String source) {
        //1、判断source是否有效
        if (source == null || source.length() == 0) {
            //2、如果不是有效的字符串抛出异常
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
        }
        try {
            //3、获取MessageDigest对象
            String algorithm = "md5";   //加密算法
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);


            //4、获取明文字符串对应的字节数组
            byte[] input = source.getBytes();

            //5、获取加密后的字节数组加密后（执行加密）
            byte[] output = messageDigest.digest(input);

            //6、创建BigInteger对象
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, output);

            //7、按照16进制将BigInteger的值转为字符串
            int radix = 16;
            String encode = bigInteger.toString(radix);

            return encode;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 判断请求是否为ajax请求
     * true：是ajax请求
     * false：不是ajax请求
     * @param request
     * @return
     */
    public static boolean judgeResult(HttpServletRequest request) {

        String acceptHeader = request.getHeader("Accept");
        String XRequestWithHeader = request.getHeader("X-Requested-With");

        return (acceptHeader != null && acceptHeader.contains("application/json")) ||
                (XRequestWithHeader != null && XRequestWithHeader.contains("XMLHttpRequest"));
    }
}
