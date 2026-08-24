package com.zcf.mapper;


import com.zcf.pojo.Emp;
import com.zcf.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


/*
* 员工信息
* */

@Mapper
public interface EmpMapper {

    /*
    * 条件查询员工信息
    * */
    public List<Emp> list(EmpQueryParam empQueryParam);


    /*
    * 新增员工基本信息
    * */
    @Options(useGeneratedKeys = true, keyProperty = "id")//获取到生成的主键--主键返回
    @Insert("insert into emp (username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)\n" +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime});")
    void insert(Emp emp);

    /*
    * 删除员工基本信息
    * */
    void deleteByIds(List<Integer> ids);

    /*
    * 根据id查询员工信息以及工作经历信息
    * */
    Emp getById(Integer id);

    /*
    * 根据id修改员工基本信息以及工作经历信息
    * */
    void updateById(Emp emp);

    /*
    * 统计员工职位分布
    * */
    List<Map<String, Object>> CountEmpJobData();

    /*
    * 统计员工性别分布
    * */
    @MapKey("name")
    List<Map<String, Object>> CountEmpGenderData();

    /*
    * 查询所有员工
    * */
    @Select("select id, username, name, gender, phone, job, salary, image,\n" +
            "       entry_date, dept_id, create_time, update_time\n" +
            "from emp order by id;")
    List<Emp> getAll();

    /*
    * 根据用户名和密码查询员工信息
    * */
    @Select("select id,username,name from emp where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
