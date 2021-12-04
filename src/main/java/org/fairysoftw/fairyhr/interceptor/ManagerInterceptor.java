package org.fairysoftw.fairyhr.interceptor;

import org.fairysoftw.fairyhr.service.DepartmentService;
import org.fairysoftw.fairyhr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ManagerInterceptor implements HandlerInterceptor {
    private final DepartmentService departmentService;
    private final UserService userService;

    @Autowired
    public ManagerInterceptor(DepartmentService departmentService, UserService userService) {
        this.departmentService = departmentService;
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        var id = (String) request.getSession().getAttribute("id");
        var user = userService.selectById(id);
        if (user != null) {
            var departments = departmentService.selectByUserId(id);
            return departments.get(0).getManagers().stream().anyMatch((u) -> u.getId().equals(user.getId()));
        }
        else {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
