package com.zcf.service.impl;


import com.zcf.mapper.EmpMapper;
import com.zcf.mapper.StudentMapper;
import com.zcf.pojo.ClazzCountOption;
import com.zcf.pojo.JobOption;
import com.zcf.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private StudentMapper studentMapper;

    /*
    * 统计员工职位人数
    * */
    @Override
    public JobOption getEmpJobData() {
        //1.调用Mapper接口，获取统计数据
        List<Map<String,Object>> list=empMapper.CountEmpJobData();

        //2.组装结果，并返回
        List<Object> jobList= list.stream().map(dataMap->dataMap.get("pos")).toList();
        List<Object> dataList= list.stream().map(dataMap->dataMap.get("num")).toList();
        return new JobOption(jobList,dataList);
    }

    /*
    * 统计员工性别
    * */
    @Override
    public List<Map<String,Object>> getEmpGenderData() {
        return empMapper.CountEmpGenderData();
    }

    /*
    * 统计各班人数
    * */
    @Override
    public ClazzCountOption getClazzCountData() {
        //1.调用Mapper接口，获取统计数据
        List<Map<String,Object>> list=studentMapper.countStudentByClazz();
        //2.组装结果，并返回
        List<Object> clazzList= list.stream().map(dataMap->dataMap.get("clazzName")).toList();
        List<Object> dataList= list.stream().map(dataMap->dataMap.get("num")).toList();
        return new ClazzCountOption(clazzList,dataList);
    }

    /*
    * 统计学生学历分布
    * */
    @Override
    public List<Map<String,Object>> getStudentDegreeData() {
        return studentMapper.countStudentByDegree();
    }
}
