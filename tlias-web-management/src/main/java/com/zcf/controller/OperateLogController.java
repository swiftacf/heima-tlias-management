package com.zcf.controller;


import com.zcf.pojo.OperateLog;
import com.zcf.pojo.PageResult;
import com.zcf.pojo.Result;
import com.zcf.service.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OperateLogController {

    @Autowired
    private OperateLogService operateLogService;

    @GetMapping("/log/page")
    public Result OperateLogController(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("分页查询操作日志，页码：{}，每页大小：{}", page, pageSize);
        PageResult<OperateLog> pageResult=operateLogService.getOperateLogPage(page, pageSize);
        return Result.success(pageResult);
    }
}
