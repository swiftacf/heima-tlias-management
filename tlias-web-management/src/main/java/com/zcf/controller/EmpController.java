package com.zcf.controller;


import com.zcf.pojo.Emp;
import com.zcf.pojo.EmpQueryParam;
import com.zcf.pojo.PageResult;
import com.zcf.pojo.Result;
import com.zcf.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/*
* 员工管理Controller
* */
@RestController
@Slf4j
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;
    /*
    * 分页查询
    * */


    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("分页查询:{}",empQueryParam);
        PageResult<Emp> empPageResult = empService.page(empQueryParam);
        return Result.success(empPageResult);
    }

/*
* 新增员工
* */
    @PostMapping
    public Result save(@RequestBody Emp emp) throws Exception {
        log.info("新增员工:{}",emp);
        empService.save(emp);
        return Result.success();
    }

    /*
    * 删除员工-数组
    * */
    /*@DeleteMapping
    public Result delete(Integer[] ids){
        log.info("删除员工:{}", Arrays.toString(ids));
//        empService.deleteBatch(ids);
        return Result.success();
    }*/

    /*
    * 删除员工-List
    * */
    @DeleteMapping
    public Result deleteBatch(@RequestParam List<Integer> ids) throws Exception {
        log.info("删除员工:{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /*
    * 根据ID查询员工
    * */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据ID查询员工,id={}",id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    /*
    * 修改员工信息
    * */
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工信息:{}",emp);
        empService.update(emp);
        return Result.success();
    }

    /*
    * 查询所有员工信息
    * */
    @GetMapping("/list")
    public Result getAll(){
        log.info("查询所有员工信息");
        List<Emp> empList = empService.getAll();
        return Result.success(empList);
    }
}