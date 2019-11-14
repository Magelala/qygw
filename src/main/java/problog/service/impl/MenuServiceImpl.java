package problog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import problog.entity.authorization.Menu;
import problog.entity.response.ResResult;
import problog.mapper.authorization.MenuMapper;
import problog.mapper.authorization.MenuRoleMapper;
import problog.service.MenuService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:MenuServiceImpl
 * @Author:Timelin
 **/
@Service(value = "menuService")
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private MenuRoleMapper menuRoleMapper;

    // 查询所有
    @Override
    public ResResult<Map<String, StringBuffer>> findAll() {

        ResResult<Map<String, StringBuffer>> resResult = null;
        try {
            List<Menu> menus = menuMapper.queryAll();

            Map<String, StringBuffer> classify = classify(menus);

            resResult =  new ResResult<Map<String, StringBuffer>>(0,"查询所有成功",classify);
            resResult.setCount(classify.size());



            return resResult;
        } catch (Exception e){
            e.printStackTrace();
        }
        return  new ResResult(0,"失败",null);
    }


    // 使用Map<String,StringBuffer>将权限归纳为 1 数据中心  2 广告管理  3 文章管理  4 用户管理  5 页面管理
    public Map<String,StringBuffer>  classify(List<Menu>  menus) {
        // 遍历
        AntPathMatcher antPathMatcher = new AntPathMatcher();

        StringBuffer dateManage = new StringBuffer();
        StringBuffer advManage = new StringBuffer();
        StringBuffer artManage = new StringBuffer();
        StringBuffer userManage = new StringBuffer();
        StringBuffer pageManage = new StringBuffer();

        Map<String, StringBuffer > listMap = new HashMap<>();

        // 归类
        for (Menu menu : menus) {

            // 数据中心
            if (antPathMatcher.match( menu.getUrl(),"/data/")) {

                String id = menu.getId().toString();
                dateManage.append(id+"_");
            }


            // 广告管理
            // 1 广告设置
            if (antPathMatcher.match( menu.getUrl(),"/advertise/")) {
                String id = menu.getId().toString();
                advManage.append(id+"_");

            }

            // 2 企业简介
            if (antPathMatcher.match( menu.getUrl(),"/companyProfile/")) {

                String id = menu.getId().toString();
                advManage.append(id+"_");
            }


            // 文章管理

            if (antPathMatcher.match( menu.getUrl(),"/article/")) {

                String id = menu.getId().toString();
                artManage.append(id+"_");
            }

            // 用户管理
            // 1 用户管理
            if (antPathMatcher.match( menu.getUrl(),"/user/")) {

                String id = menu.getId().toString();
                userManage.append(id+"_");
            }

            // 2 角色设置
            if (antPathMatcher.match( menu.getUrl(),"/role/")) {

                String id = menu.getId().toString();
                userManage.append(id+"_");
            }

            // 3 权限设置
            if (antPathMatcher.match( menu.getUrl(),"/menu/")) {

                String id = menu.getId().toString();
                userManage.append(id+"_");

            }


            // 页面管理
            // 1 导航栏管理
            if (antPathMatcher.match( menu.getUrl(),"/nav/")) {
                String id = menu.getId().toString();
                pageManage.append(id+"_");
            }

            // 2 底部信息管理
            if (antPathMatcher.match( menu.getUrl(),"/foot/")) {
                String id = menu.getId().toString();
                pageManage.append(id+"_");
            }
            // 3 公司logo管理
            if (antPathMatcher.match( menu.getUrl(),"/logo/")) {
                String id = menu.getId().toString();
                pageManage.append(id+"_");
            }
        }

        // 封装
        listMap.put("数据中心",dateManage);
        listMap.put("广告管理",advManage);
        listMap.put("文章管理",artManage);
        listMap.put("用户管理",userManage);
        listMap.put("页面管理",pageManage);

        return listMap;

    }





    // 多表查询 menu ,menu_role,role
    @Override
    public List<Menu> getAllMenu() {
        return menuMapper.getAllMenu();
    }

    @Override
    public List<Menu> listByRoleId(Long roleId) {
        return menuMapper.listByRoleId(roleId);
    }

    // 给角色分配 权限
    @Override
    public ResResult addMenuRole(Integer rid, String mids) {


        ResResult resResult =null;
        try {

            // 参数判空
            if(rid.equals("") || rid==null || mids.equals("") || mids==null){
                return resResult.fail(0);
            }
            // 分割mid 字符串，以‘_’为标记，转换成整数数组
            String[] split = mids.split("_");
            List<Integer> midList = new ArrayList<>();

            for (int i= 0; i < split.length;i++){

                Integer mid = Integer.valueOf(split[i]);
                if(mid.equals("") || mid==null){
                    continue;
                }
                midList.add(mid);
            }
            // 数值判空
            if(midList.size()<=0){
                return resResult.fail(0);
            }

            // 先删除，后保存
            menuRoleMapper.deleteByRId(rid);

            // 保存新的数据
            for (Integer m :midList){

                menuRoleMapper.addByRidMid(rid,m);
            }

            resResult = new ResResult(0,"权限分配成功",null);
            resResult.setCount(midList.size());
            return resResult;


        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
