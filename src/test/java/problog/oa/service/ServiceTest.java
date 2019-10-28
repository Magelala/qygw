package problog.oa.service;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import problog.entity.Article.ArticleContent;
import problog.entity.Category.Category;
import problog.entity.User.Author;
import problog.entity.User.usersUser;
import problog.mapper.Article.ArticleContentMapper;
import problog.mapper.User.AuthorMapper;
import problog.mapper.User.UsersMapper;
import problog.service.ArticleService;
import problog.service.CategoryService;
import problog.service.MailService;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
    @Autowired
    private MailService mailService;
    @Autowired
    private TemplateEngine templateEngine;
    @Resource
    ArticleService articleService;

    @Resource
    CategoryService categoryService;
    @Resource
    ArticleContentMapper articleContentMapper;

//    @Test
//    public void testSelect(){
//        System.out.println(("----- selectAll method test ------"));
//        List<usersUser> userList = usersMapper.selectList(null);
//        Assert.assertEquals(5, userList.size());
//        userList.forEach(System.out::println);
//    }

    @Test
    public void testSelect(){
        Category category = new Category();
        category.setName("英语");
        category.setDescription("考研英语难难难");
        int i = categoryService.addCategory(category);
        System.out.println(i);



    }
    @Test
    public void insertTest() {
        ArticleContent content = new ArticleContent();
        content.setContext("sadasda");
        content.setTitle("haha");
        content.setSummary("sadasdas");
        content.setKeywords("dddd");
        content.setModifiedByDate(new Timestamp(System.currentTimeMillis()));
        content.setCreateByDate(new Timestamp(System.currentTimeMillis()));
        int i = articleContentMapper.insert(content);
        System.out.println(i);

    }

    @Test
    public void testSource() {

    }

    /**
     * 发送简单纯文本邮件
     */
    @Test
    public void sendSimpleMail() {
        mailService.sendSimpleMail("969064814@qq.com", "发送邮件测试", "大家好，这是我用springboot进行发送邮件测试");
    }

    /**
     * 发送HTML邮件
     */
    @Test
    public void sendHtmlMail() {
        String content = "<html><body><h3><font color=\"red\">" + "大家好，这是springboot发送的HTML邮件" + "</font></h3></body></html>";
        mailService.sendHtmlMail("969064814@qq.com", "发送邮件测试", content);
    }

    /**
     * 发送带附件的邮件
     */
    @Test
    public void sendAttachmentMail() {
        String content = "<html><body><h3><font color=\"red\">" + "大家好，这是springboot发送的HTML邮件，有附件哦" + "</font></h3></body></html>";
        String filePath = "static/img/_382553689_IMG_20180602_195214_1527940337000_wifi.jpg";
        mailService.sendAttachmentMail("969064814@qq.com", "发送邮件测试", content, filePath);
    }

    /**
     * 发送带图片的邮件
     */
    @Test
    public void sendInlineResourceMail() {
        String rscPath = "static/img/_382553689_IMG_20180602_195214_1527940337000_wifi.jpg";
        String rscId = "skill001";
        String content = "<html><body><h3><font color=\"red\">" + "大家好，这是springboot发送的HTML邮件，有图片哦" + "</font></h3>"
                + "<img src=\'cid:" + rscId + "\'></body></html>";
        mailService.sendInlineResourceMail("969064814@qq.com", "发送邮件测试", content, rscPath, rscId);
    }

    /**
     * 指定模板发送邮件
     */
    @Test
    public void testTemplateMail() {
        //向Thymeleaf模板传值，并解析成字符串
        Context context = new Context();
        context.setVariable("id", "001");
        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail("969064814@qq.com", "这是一个模板文件", emailContent);
    }





    /**
     @Test
    //添加新的文章
    public void addNewArticleContent(){
      //添加操作
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //设置创建发表日期
        //更新
        ArticleContent article = new ArticleContent();
//        article.setId(8);
        article.setContext("hahahahahah");

        article.setCreateByDate(timestamp);

        int i = articleContentMapper.insert(article);
        System.out.println(i);
    }
     */

//    @Test
//    public void testshow(){
//        List<ArticleContent> list = articleContentMapper.selectList(null);
//
//        list.forEach(System.out::println);
//    }

//    @Test
//    public void findlistById(ArticleContent articleContent){
//        List<ArticleContent> list = articleContentMapper.selectList(new LambdaQueryWrapper<ArticleContent>().eq(ArticleContent::getTitle, articleContent.getTitle()));
//        System.out.println(list);




//    @Test
//    //添加新的文章
//    public void addNewArticleContent(){
//        //添加操作
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        //设置创建发表日期
//        //更新
//        ArticleContent article = new ArticleContent();
////        article.setId(8);
//        article.setContext("hahahahahah");
//        article.setArticleId(2);
//        article.setCreateByDate(timestamp);
//        article.setIsTop(1);
//        int i = articleContentMapper.insert(article);
//        System.out.println(i);
//    }
    @Test
    public void delectTest(){
        int article = articleService.deleteArticle(2);
        System.out.println(article);
    }
}

