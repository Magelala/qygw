package problog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import problog.entity.User.Author;
import problog.entity.response.ResResult;

import java.util.List;

@Service
@Transactional
public interface UserService {


    // 查询所有
    ResResult<List<Author>> findAll();

    // 分页查询所有
    ResResult<List<Author>> showPage(Integer page, Integer limit);

    // 批量删除
    ResResult deleteList(Integer[] ids);

    // 修改
    ResResult updateAuthor(Author author);

    // 根据id 查询
    ResResult<Author> findOneById(Integer id);

    // 添加
    ResResult addAuthor(Author author);


    // 模糊查询
    ResResult<List<Author>> search(Author author, Integer page, Integer rows);




}
