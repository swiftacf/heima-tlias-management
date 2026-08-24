package com.zcf.service;

import com.zcf.pojo.ClazzCountOption;
import com.zcf.pojo.JobOption;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /*
    * 统计员工职位人数
    * */
    JobOption getEmpJobData();

    /*
    * 统计员工性别
    * */
    List<Map<String,Object>> getEmpGenderData();

    /*
    * 统计各班人数
    * */
    ClazzCountOption getClazzCountData();

    /*
    * 统计学生学历分布
    * */
    List<Map<String,Object>> getStudentDegreeData();
}
