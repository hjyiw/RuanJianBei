package com.hjy.interceptors;

import com.hjy.utils.JwtUtil;
import com.hjy.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * ClassName: LoginInterceptor
 * Package: com.hjy.interceptors
 * Description:
 *
 * @Author 黄嘉宇
 * @Create 2024/5/8 14:31
 * @Version 1.0
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //验证token
        String token = request.getHeader("Authorization");
        try {
            Map<String, Object> claims  = JwtUtil.parseToken(token);
            //把业务数据存到ThreadLocal中
            ThreadLocalUtil.set(claims);
            //放行
            return true;
        } catch (Exception e) {
            //http响应码为 401
            System.out.println("到这来了");
            response.setStatus(401);
            return false;
        }
    }

    //一次请求结束后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清空ThreadLocal中的数据，防止内存泄露
        ThreadLocalUtil.remove();
    }
}
