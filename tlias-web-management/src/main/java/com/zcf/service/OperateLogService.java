package com.zcf.service;

import com.zcf.pojo.OperateLog;
import com.zcf.pojo.PageResult;

public interface OperateLogService {
    PageResult<OperateLog> getOperateLogPage(Integer page, Integer pageSize);
}
