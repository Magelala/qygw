package problog.service;


import org.springframework.stereotype.Service;
import problog.entity.Category.Category;

import java.util.List;


public interface CategoryService {
    //显示分类列表，返回所有分类列表
    List<Category> showCategory();
}
