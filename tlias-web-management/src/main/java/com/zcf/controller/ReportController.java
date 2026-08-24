package com.zcf.controller;


import com.zcf.pojo.ClazzCountOption;
import com.zcf.pojo.JobOption;
import com.zcf.pojo.Result;
import com.zcf.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;
    /*
    * 统计员工职位人数
    * */
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计员工职位人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /*
    * 统计员工性别
    * */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("统计员工性别");
        List<Map<String,Object>> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }

    /*
    * 统计各班人数
    * */
    @GetMapping("/studentCountData")
    public Result getClazzCountData(){
        log.info("统计各班人数");
        ClazzCountOption clazzCountOption = reportService.getClazzCountData();
        return Result.success(clazzCountOption);
    }

    /*
    * 统计学生学历分布
    * */
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("统计学生学历分布");
        List<Map<String,Object>> degreeList = reportService.getStudentDegreeData();
        return Result.success(degreeList);
    }

}
