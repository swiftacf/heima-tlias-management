package com.zcf.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zcf.mapper.StudentMapper;
import com.zcf.pojo.PageResult;
import com.zcf.pojo.Student;
import com.zcf.pojo.StudentQueryParam;
import com.zcf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /*
    * 分页查询学员
    * */
    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        //2.进行分页查询
        List<Student> studentList = studentMapper.list(studentQueryParam);
        //3.返回结果
        Page<Student> p = (Page<Student>) studentList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    /*
    * 新增学员
    **/
    @Override
    public void add(Student student) {
        student.setViolationCount((short) 0);
        student.setViolationScore((short) 0);
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.add(student);
    }

    /*
    * 根据ID查询学员
    * */
    @Override
    public Student get(Long id) {
        return studentMapper.get(id);
    }

    /*
    * 修改学员信息
    * */
    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    /*
    * 根据ID删除学员信息
    * */
    @Override
    public void delete(List<Integer> ids) {
        studentMapper.delete(ids);
    }

    /*
    * 学生违纪处理
    * */
    @Override
    public void violation(Long id, Integer score) {
        studentMapper.violation(id, score);
    }
}
