package com.zcf.service;

import com.zcf.pojo.Emp;
import com.zcf.pojo.EmpQueryParam;
import com.zcf.pojo.LoginInfo;
import com.zcf.pojo.PageResult;

import java.util.List;

public interface EmpService {
    /*
    * 分页查询
    * */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /*
    * 新增员工
    * */
    void save(Emp emp) throws Exception;

    /*
    * 删除员工
    * */
    void delete(List<Integer> ids) throws Exception;

    /*
    * 根据id查询员工信息
    * */
    Emp getInfo(Integer id);

    /*
    * 修改员工信息
    * */
    void update(Emp emp);

    /*
    * 查询所有员工信息
    * */
    List<Emp> getAll();

    /*
    * 员工登录
    * */
    LoginInfo login(Emp emp);
}
