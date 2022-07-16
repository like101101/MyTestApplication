package com.example.mytestapp.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class MaintainInterceptor implements HandlerInterceptor {
    private int maintainTime = 31;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        int accessTime = LocalDateTime.now().getMinute();
        if (accessTime == maintainTime){
            response.getWriter().write("This website is close for maintenance, please try again later");
            return false;
        }else{
            response.getWriter().write("Welcome, please login");
            return true;
        }

    }
}
