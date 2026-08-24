package com.zcf.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zcf.mapper.OperateLogMapper;
import com.zcf.pojo.OperateLog;
import com.zcf.pojo.PageResult;
import com.zcf.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperateLogServiceImpl implements OperateLogService {

    @Autowired
    private OperateLogMapper OperateLogMapper;

    @Override
    public PageResult<OperateLog> getOperateLogPage(Integer page, Integer pageSize) {
        //1.设置分页参数
        PageHelper.startPage(page, pageSize);
        //2.查询数据
        List<OperateLog> operateLogList = OperateLogMapper.list();
        //3.封装分页数据
        Page<OperateLog> p = (Page<OperateLog>) operateLogList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }
}
