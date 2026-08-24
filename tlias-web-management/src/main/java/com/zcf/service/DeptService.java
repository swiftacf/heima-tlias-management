package com.zcf.service;

import com.zcf.pojo.Dept;

import java.util.List;

public interface DeptService {
    /*
    * 查询全部部门数据
    * */
    List<Dept> findAll();

    //根据ID删除部门数据
    void deleteById(Integer id);

    //添加部门数据
    void add(Dept dept);

    //根据ID查询部门数据
    Dept getById(Integer id);

    //修改部门数据
    void update(Dept dept);
}
