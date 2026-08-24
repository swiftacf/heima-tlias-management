package com.zcf.mapper;

import com.zcf.pojo.Dept;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Mapper
public interface DeptMapper {
    /*
    * 查询全部部门数据
    * */
    //方式一：手动结果映射
   /* @Results({
            @Result(column="create_time",property="createTime"),
            @Result(column="update_time",property="updateTime")
    })*/
    //方式二：起别名
//    @Select("select id, name, create_time createTime, update_time updateTime from dept order by update_time desc;")
    @Select("select id, name, create_time, update_time from dept order by update_time desc;")
    List<Dept> findAll();

    //根据ID删除部门数据
    @Delete("delete from dept where id=#{id}")
    void deleteById(Integer id);

    //添加部门数据
    @Insert("insert into dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    //根据ID查询部门数据
    @Select("select id, name, create_time, update_time from dept where id=#{id}")
    Dept getById(Integer id);

    //修改部门数据
    @Update("update dept set name=#{name}, update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);
}
