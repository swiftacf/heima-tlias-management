package com.zcf.service;

import com.zcf.pojo.Clazz;
import com.zcf.pojo.ClazzQueryParam;
import com.zcf.pojo.PageResult;

import java.util.List;

public interface ClazzService{
    /*
    * 条件分页查询班级列表
    * */
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    /*
    * 根据ID删除班级
    * */
    void delete(Integer id);

    /*
    * 新增班级
    * */
    void insert(Clazz clazz);

    /*
    * 根据ID查询班级
    * */
    Clazz selectById(Integer id);

    /*
    * 修改班级信息
    * */
    void update(Clazz clazz);

    /*
    * 查询所有班级
    * */
    List<Clazz> getAll();
}
