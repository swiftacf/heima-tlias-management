package com.zcf.mapper;

import com.zcf.pojo.EmpExpr;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
* 员工工作经历
* */

@Mapper
public interface EmpExprMapper {
    /*
    * 批量新增员工工作经历
    * */
    void insertBatch(List<EmpExpr> exprList);

    /*
    * 批量删除员工工作经历信息
    * */
    void deleteByEmpIds(List<Integer> empIds);
}
