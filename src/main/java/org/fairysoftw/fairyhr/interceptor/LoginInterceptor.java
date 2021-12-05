package org.fairysoftw.fairyhr.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器类，没有登录的用户若要访问系统则会被重定向到登录页面
 *
 * @version 1.0
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 拦截请求，判断用户是否登录，没有则重定向到登录页面
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("id") != null) {
            return true;
        }
        else {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
    }

    /**
     * 调用超类的postHandle方法
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 调用超类的afterCompletion方法
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
