package org.fairysoftw.fairyhr.interceptor;

import org.fairysoftw.fairyhr.service.DepartmentService;
import org.fairysoftw.fairyhr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 拦截器类，非对应部门的管理员无法访问对应部门的管理界面。
 *
 * @version 1.0
 */
@Component
public class ManagerInterceptor implements HandlerInterceptor {
    private final DepartmentService departmentService;
    private final UserService userService;

    /**
     * 构造函数，通过Spring自动装配。
     */
    @Autowired
    public ManagerInterceptor(DepartmentService departmentService, UserService userService) {
        this.departmentService = departmentService;
        this.userService = userService;
    }

    /**
     * 拦截请求，判断用户是否登录，没有登录则引导到登录界面，若登录则判断是否是对应部门的管理员，是的话才允许进行管理。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        var user_id = (String) request.getSession().getAttribute("id");
        var department_id = (String) pathVariables.get("id");
        var user = userService.selectById(user_id);
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        if (department_id == null) {
            var departments = departmentService.selectByUserId(user.getId());
            for (var department : departments) {
                if (department.getManagers().stream().anyMatch((u) -> u.getId().equals(user.getId()))) {
                    return true;
                }
            }
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        else {
            var department = departmentService.selectById(department_id);
            if (department != null) {
                if(department.getManagers() != null && department.getManagers().stream().anyMatch((u) -> u.getId().equals(user.getId()))) {
                    return true;
                } else {
                    response.sendRedirect(request.getContextPath() + "/department");
                    return false;
                }
            }
            else {
                response.sendRedirect(request.getContextPath() + "/department");
                return false;
            }
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
