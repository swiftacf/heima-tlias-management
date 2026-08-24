package com.zcf.mapper;

import com.zcf.pojo.Clazz;
import com.zcf.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {

    /*
    * 条件分页查询班级列表
    * */
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    /*
    * 根据ID删除班级
    * */
    @Delete("DELETE FROM clazz WHERE id = #{id}")
    void delete(Integer id);

    /*
    * 新增班级
    * */
    void insert(Clazz clazz);

    /*
    * 根据ID查询班级
    * */
    @Select("SELECT * FROM clazz WHERE id = #{id}")
    Clazz selectById(Integer id);

    /*
    * 修改班级信息
    * */
    void update(Clazz clazz);

    /*
    * 查询所有班级
    * */
    @Select("SELECT * FROM clazz")
    List<Clazz> getAll();
}
