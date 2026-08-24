package com.zcf.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zcf.mapper.EmpExprMapper;
import com.zcf.mapper.EmpMapper;
import com.zcf.pojo.*;
import com.zcf.service.EmpLogService;
import com.zcf.service.EmpService;
import com.zcf.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;



    /*
     * 分页查询
     * */
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        //2.执行查询
        List<Emp> empList =empMapper.list(empQueryParam);

        //3.解析查询结果，并封装
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }


    /*
     * 新增员工
     * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Emp emp) throws Exception {
        try {
            //1.保存员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            //2.保存员工工作经历信息
            List<EmpExpr> exprList =emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                //遍历集合，为empId赋值
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //记录操作日志
            empLogService.insertLog(new EmpLog(null,LocalDateTime.now(),"新增员工"+emp));
        }
    }

    /*
    * 删除员工
    * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<Integer> ids) throws Exception {
        try {
            //1.删除员工基本信息
            empMapper.deleteByIds(ids);
            //2.删除员工工作经历信息
            empExprMapper.deleteByEmpIds(ids);
        } finally {
            //记录操作日志
            empLogService.insertLog(new EmpLog(null,LocalDateTime.now(),"删除员工"+ids));
        }
    }

    /*
    * 根据id查询员工信息
    * */
    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    /*
    * 修改员工信息
    * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        //1.修改员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //2.根据ID修改员工工作经历信息
        //2.1先删除
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        //2.2再新增
        List<EmpExpr> exprList =emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }
    }

    /*
    * 查询所有员工信息
    * */
    @Override
    public List<Emp> getAll() {
        return empMapper.getAll();
    }

    /*
     *员工登录
     * */
    @Override
    public LoginInfo login(Emp emp) {
        //1.调用Mapper接口,根据用户名和密码查询员工
        Emp e =empMapper.selectByUsernameAndPassword(emp);

        //2.判断是否存在这个员工，如果存在，组装登陆成功信息
        if(e != null){
            log.info("登录成功,员工信息:{}", e);

            //生成JWT令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            String token = JwtUtils.generateJwt(claims);

            return new LoginInfo(e.getId(),e.getUsername(),e.getName(),token);
        }

        //3.不存在，返回null
        return null;
    }


}
