package com.zcf.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zcf.exception.BusinessException;
import com.zcf.mapper.ClazzMapper;
import com.zcf.mapper.StudentMapper;
import com.zcf.pojo.Clazz;
import com.zcf.pojo.ClazzQueryParam;
import com.zcf.pojo.PageResult;
import com.zcf.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ClazzMapper clazzMapper;

    /*
    * 条件分页查询班级列表
    * */
    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());

        //2.执行分页查询
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);

        //3.返回查询结果
        Page<Clazz> p=(Page<Clazz>) clazzList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    /*
    * 根据ID删除班级
    * */
    @Override
    public void delete(Integer id) {
        //1.查询该班级学生数
        Long count=studentMapper.countByClazzId(id);
        //2.如果有，抛出业务异常，不许删除
        if(count>0){
            throw new BusinessException("班级中有学生，不允许删除");
        }
        //3.如果没有，执行删除
        clazzMapper.delete(id);
    }

    /*
    * 新增班级
    * */
    @Override
    public void insert(Clazz clazz) {
        //1.补全创建时间，更新时间
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        //2.执行新增
        clazzMapper.insert(clazz);
    }

    /*
    * 根据ID查询班级
    * */
    @Override
    public Clazz selectById(Integer id) {
        return clazzMapper.selectById(id);
    }

    /*
    * 修改班级信息
    * */
    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    /*
    * 查询所有班级
    * */
    @Override
    public List<Clazz> getAll() {
        return clazzMapper.getAll();
    }
}
