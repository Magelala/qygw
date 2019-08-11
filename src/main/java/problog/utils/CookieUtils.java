package problog.utils;

import javax.servlet.http.Cookie;

/**
 * @Author : shengjun
 * @Date : create in 2019-8-8
 */
public class CookieUtils {

    public static Cookie findCookie(Cookie[] cookies, String name) {
        if (cookies==null) {
            return null;
        }else {
            for (Cookie cookie:cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie;
                }
            }
            return null;
        }
    }
}
