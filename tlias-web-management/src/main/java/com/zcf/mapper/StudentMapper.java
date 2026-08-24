package com.zcf.mapper;


import com.zcf.pojo.Student;
import com.zcf.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    /*
    * 根据ID统计学生数量
    * */
    Long countByClazzId(Integer clazzId);

    /*
    * 分页查询学生
    * */
    List<Student> list(StudentQueryParam studentQueryParam);

    /*
    * 添加学生
    * */
    void add(Student student);

    /*
    * 根据ID查询学生
    * */
    @Select("SELECT * FROM student WHERE id = #{id}")
    Student get(Long id);

    /*
    * 修改学员信息
    * */
    void update(Student student);

    /*
    * 根据ID删除学生信息
    * */
    void delete(List<Integer> ids);

    /*
    * 学生违纪处理
    * */
    void violation(Long id, Integer score);

    /*
    * 统计各班人数
    * */
    List<Map<String,Object>> countStudentByClazz();

    /*
    * 统计学生学历分布
    * */
    List<Map<String,Object>> countStudentByDegree();
}
