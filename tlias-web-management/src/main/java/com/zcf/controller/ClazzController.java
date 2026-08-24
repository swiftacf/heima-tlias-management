package com.zcf.controller;


import com.zcf.pojo.Clazz;
import com.zcf.pojo.ClazzQueryParam;
import com.zcf.pojo.PageResult;
import com.zcf.pojo.Result;
import com.zcf.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {


    @Autowired
    private ClazzService clazzService;

    /*
    * 分页查询班级信息
    * */
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){
        log.info("班级分页查询:{}", clazzQueryParam);
        PageResult<Clazz> clazzPageResult = clazzService.page(clazzQueryParam);
        return Result.success(clazzPageResult);
    }

    /*
    * 根据ID删除班级
    * */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据ID删除班级:{}", id);
        clazzService.delete(id);
        return Result.success();
    }

    /*
    * 新增班级
    * */
    @PostMapping
    public Result insert(@RequestBody Clazz clazz){
        log.info("新增班级:{}", clazz);
        clazzService.insert(clazz);
        return Result.success();
    }

    /*
    * 根据ID查询班级
    * */
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        log.info("根据ID查询班级:{}", id);
        Clazz clazz = clazzService.selectById(id);
        return Result.success(clazz);
    }

    /*
    * 修改班级信息
    * */
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改班级信息:{}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    /*
    * 查询所有班级
    * */
    @GetMapping("/list")
    public Result getAll(){
        log.info("查询所有班级");
        List<Clazz> clazzList = clazzService.getAll();
        return Result.success(clazzList);
    }
}
