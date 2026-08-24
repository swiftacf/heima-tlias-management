package com.zcf.controller;

import com.zcf.pojo.PageResult;
import com.zcf.pojo.Result;
import com.zcf.pojo.Student;
import com.zcf.pojo.StudentQueryParam;
import com.zcf.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {


    @Autowired
    private StudentService studentService;
    /*
    * 学员列表查询
    * */
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam) {
        log.info("分页查询学员{}", studentQueryParam);
        PageResult<Student> studentPageResult=studentService.page(studentQueryParam);
        return Result.success(studentPageResult);
    }

    /*
    * 添加学员
    * */
    @PostMapping
    public Result add(@RequestBody Student student) {
        log.info("添加学员{}", student);
        studentService.add(student);
        return Result.success();
    }

    /*
    * 根据ID查询学员
    * */
    @GetMapping("/{id}")
    public Result get(@PathVariable Long id) {
        log.info("根据ID查询学员{}", id);
        Student student = studentService.get(id);
        return Result.success(student);
    }

    /*
    * 修改学员信息
    * */
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改学员信息{}", student);
        studentService.update(student);
        return Result.success();
    }

    /*
    * 根据ID批量删除学员信息
    * */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("根据ID删除学员信息{}", ids);
        studentService.delete(ids);
        return Result.success();
    }

    /*
    * 学生违纪处理
    * */
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Long id, @PathVariable Integer score) {
        log.info("学生违纪处理{}, {}", id, score);
        studentService.violation(id, score);
        return Result.success();
    }
}
