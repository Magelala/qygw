package problog.oa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import problog.domain.User.User;
import problog.utils.FindUser;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OaApplicationTests {

    static Map<Integer, User> data;

    static {
        data = new HashMap<Integer, User>();
        data.put(1,new User("admin","88888888"));
        data.put(2,new User("zhangsan","123456"));
        data.put(3,new User("lisi","00000000"));
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void test(){
        System.out.println(FindUser.findUserByName(data,"admin"));
    }

}
