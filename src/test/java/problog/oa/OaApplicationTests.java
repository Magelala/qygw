package problog.oa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import problog.entity.Article.ArticleContent;
import problog.entity.User.User;
import problog.mapper.Article.ArticleContentMapper;
import problog.mapper.carousel.CarouselMapper;
import problog.service.ArticleService;
import problog.utils.FindUser;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OaApplicationTests {

    static Map<Integer, User> data;

    @Resource
    private CarouselMapper carouselMapper;

    @Autowired
    private ArticleService articleService;

    static {
        data = new HashMap<Integer, User>();
        data.put(1,new User("admin","88888888"));
        data.put(2,new User("zhangsan","123456"));
        data.put(3,new User("lisi","00000000"));
    }

    @Test
    public void contextLoads() {
        List<ArticleContent> list = articleService.getArticleByTitle("安利进驻国际");
    }

    @Test
    public void testDelete(){
        Integer[] arr = {52,53};
        int i = carouselMapper.deleteList(arr);
        System.out.println(i);
    }

    @Test
    public void test(){
        System.out.println(FindUser.findUserByName(data,"admin"));
    }

    @Test
    public void showArticle(){
        List<ArticleContent> list = articleService.showArticle();
        for (ArticleContent articleContent:list){
            System.out.println(articleContent);
        }
    }

}
