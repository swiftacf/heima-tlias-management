package com.zcf.service;

import com.zcf.pojo.PageResult;
import com.zcf.pojo.Student;
import com.zcf.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    /*
    * 分页查询学员
    * */
    PageResult<Student> page(StudentQueryParam studentQueryParam);

    /*
    * 新增学员
    * */
    void add(Student student);

    /*
    * 根据ID查询学员
    * */
    Student get(Long id);

    /*
    * 修改学员信息
    * */
    void update(Student student);

    /*
    * 根据ID删除学员信息
    * */
    void delete(List<Integer> ids);

    /*
    * 学生违纪处理
    * */
    void violation(Long id, Integer score);
}
