package com.dtz.crowd.util;

import javax.servlet.http.HttpServletRequest;

public class CrowdUtil {

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
