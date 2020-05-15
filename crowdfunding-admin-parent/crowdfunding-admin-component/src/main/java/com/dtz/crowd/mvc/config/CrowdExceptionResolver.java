package com.dtz.crowd.mvc.config;

import com.dtz.crowd.constant.CrowdConstant;
import com.dtz.crowd.exception.LoginAcctAlreadyInUseException;
import com.dtz.crowd.exception.LoginFailedException;
import com.dtz.crowd.util.CrowdUtil;
import com.dtz.crowd.util.ResultEntity;
import com.google.gson.Gson;
import org.jetbrains.annotations.Nullable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 基于注解的异常处理类
 */
//表示当前类是一个异常处理类，每一个方法对应一个异常处理
@ControllerAdvice
public class CrowdExceptionResolver {

    /**
     * 当更新admin是发现账户已被使用时发生的异常
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = LoginAcctAlreadyInUseException.class)
    public ModelAndView resolverNullPointException(LoginAcctAlreadyInUseException exception,
                                                   HttpServletRequest request,
                                                   HttpServletResponse response) throws IOException {
        String viewName = "admin-add";
        return commonResolver(exception, request, response, viewName);

    }

    /**
     * 发生空指针异常时
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView resolverNullPointException(NullPointerException exception,
                                                   HttpServletRequest request,
                                                   HttpServletResponse response) throws IOException {
        String viewName = "error";
        return commonResolver(exception, request, response, viewName);

    }

    /**
     * 登录时发生异常
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView resolverNullPointException(LoginFailedException exception,
                                                   HttpServletRequest request,
                                                   HttpServletResponse response) throws IOException {
        String viewName = "admin-login";
        return commonResolver(exception, request, response, viewName);

    }



    @Nullable
    private ModelAndView commonResolver(Exception exception, HttpServletRequest request, HttpServletResponse response, String viewName) throws IOException {
        // 1、判断是否为ajax请求
        boolean isAjax = CrowdUtil.judgeResult(request);

        // 2、如果是ajax请求
        if (isAjax) {
            // 3、获取异常数据
            String message = exception.getMessage();

            // 4、创建ResultEntity对象
            ResultEntity<Object> failed_message = ResultEntity.failed(message);
            // 5、创建Gson对象
            Gson gson = new Gson();
            // 6、将ResultEntity对象转为json字符串
            String json = gson.toJson(failed_message);
            // 7、将json字符串写入响应流中
            response.getWriter().write(json);

            // 8、ajax请求，不需要返回ModelAndView，故返回null
            return null;
        }
        // 9、不是ajax请求，创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        // 10、将Exception对象返回
        modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION, exception);
        // 11、设置目标视图名称
        modelAndView.setViewName(viewName);
        // 12、返回modelAndView
        return modelAndView;
    }

}
