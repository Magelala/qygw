package problog.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import problog.entity.Category.Category;
import problog.mapper.Category.CategoryMapper;
import problog.service.CategoryService;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    CategoryMapper categoryMapper;

    @Override
    public List<Category> showCategory() {
        return categoryMapper.selectList(null);
    }

    @Override
    public int addCategory(Category category) {
      return categoryMapper.insert(category);
    }

    @Override
    public int deleteCategory(Integer id) {
        return categoryMapper.deleteById(id);
    }

    @Override
    public Category updateCategory(Integer id) {
        return categoryMapper.selectById(id);
    }

    @Override
    public int update(Category category) {
        return categoryMapper.updateCategory(category);
    }
}
