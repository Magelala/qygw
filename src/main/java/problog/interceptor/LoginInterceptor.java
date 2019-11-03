package problog.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import problog.entity.User.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台拦截器
 * 说明：对地址进行拦截，必须经过验证之后才能够访问
 * @Author : shengjun
 * @Date : create in 2019-8-7
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理；
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User)request.getSession().getAttribute("user");
        if (user == null){
            //如果用户为空，跳转到错误的页面
           request.setAttribute("msg","没有权限请先登录!");
           request.getRequestDispatcher("/user/login").forward(request,response);
            return false;
        }else{
            return true;
        }
    }


}
