package problog.security.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:CustomExpiredSessionStrategy
 * @Author:Timelin
 **/
public class CustomExpiredSessionStrategy implements SessionInformationExpiredStrategy {

    private ObjectMapper objectMapper = new ObjectMapper();
   private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        HttpServletRequest request = event.getRequest();
        HttpServletResponse response = event.getResponse();
        Map<Object, Object> map = new HashMap<>(16);
        map.put("code",0);
        map.put("msg","已经有另一台机器登录，您被迫下线。"+event.getSessionInformation().getLastRequest());
        // Map -> Json
        String json = objectMapper.writeValueAsString(map);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
        System.out.println(map.get("msg"));
       // request.getSession().setAttribute("sessionMsg",map.get("msg"));
        request.setAttribute("sessionMsg",map.get("msg"));

        request.getRequestDispatcher("/error/session").forward(request,response);

      // response.sendRedirect("/loginOut");
       // request.getRequestDispatcher("/login").forward(request,response);
        // 如果是跳转html页面，url代表跳转的地址
      //   redirectStrategy.sendRedirect(request,response, "/loginOut");
    }
}
