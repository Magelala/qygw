package problog.service;



import org.springframework.stereotype.Service;
import problog.entity.Category.Category;

import java.util.List;


public interface CategoryService {
    //显示分类列表，返回所有分类列表
    List<Category> showCategory();
    //添加新的分类
    int addCategory(Category category);
    //删除分类
    int deleteCategory(Integer id);

    //根据ID查询
    Category updateCategory(Integer id);

    //更新分类
    int update(Category category);

}
