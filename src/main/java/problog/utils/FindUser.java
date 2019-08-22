package problog.utils;

import problog.entity.User.User;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
/**
 * @Author : shengjun
 * @Date : create in 2019-8-16
 */

/**
 * 集合类，用于查找指定用户
 */
public class FindUser {

    public static User findUserByName(Map<Integer, User> map, String username){
        Iterator<Entry<Integer,User>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            User user = iterator.next().getValue();
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }
}
