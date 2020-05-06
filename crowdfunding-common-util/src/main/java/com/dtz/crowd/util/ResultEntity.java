package com.dtz.crowd.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;

/**
 * 统一整个项目中ajax请求返回的参数
 * 未来也可用于分布式架构各个模块间调用时返回统一的数据类型
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultEntity<T> {

    private final static String SUCCESS = "SUCCESS";
    private final static String FAILED = "FAILED";

    //请求处理的结果
    private String result;

    //请求处理失败时返回的错误消息
    private String message;

    //要返回的数据
    private T data;

    /**
     * 请求成功且不需要返回数据使用的工具方法
     * @return
     */
    public static <Type> ResultEntity<Type> successWithoutData() {
        return new ResultEntity<Type>(SUCCESS, null, null);
    }

    /**
     * 请求处理成功且需要返回数据的工具方法
     * @param data 要返回的数据
     * @param <Type>
     * @return
     */
    public static <Type> ResultEntity<Type> successWithData(Type data) {
        return new ResultEntity<Type>(SUCCESS, null, data);
    }

    /**
     * 请求处理失败时使用的工具方法
     * @param err_message 失败的错误消息
     * @param <Type>
     * @return
     */
    public  static <Type> ResultEntity<Type> failed(String err_message) {
        return new ResultEntity<Type>(FAILED, err_message, null);
    }
    
}
