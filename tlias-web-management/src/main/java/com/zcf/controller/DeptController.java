package com.zcf.controller;

import com.zcf.anno.Log;
import com.zcf.pojo.Dept;
import com.zcf.pojo.Result;
import com.zcf.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;
//    @RequestMapping(value="/depts",method= RequestMethod.GET)
    /*
    * 查询全部部门数据
    * */
    @GetMapping
    public Result list(){
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /*
    *删除部门  -  方式一：HttpServletRequest获取请求参数
    * */
    /*@DeleteMapping("/depts")
    public Result delete(HttpServletRequest  request){
        String idStr=request.getParameter("id");
        Integer id = Integer.parseInt(idStr);
        System.out.println("根据ID删除部门:"+id);
        return Result.success();
    }*/

    /*
    * 删除部门  -  方式二：@RequestParam获取请求参数
    * */
    /*@DeleteMapping("/depts")
    public Result delete(@RequestParam(value = "id",required = false) Integer deptId){
        System.out.println("根据ID删除部门:"+deptId);
        return Result.success();
    }*/
    /*
    * 删除部门  -  方式三：省略@RequestParam注解
    * */
    @Log
    @DeleteMapping
    public Result delete(Integer id){
        log.info("根据ID删除部门,id={}",id);
        deptService.deleteById(id);
        return Result.success();
    }


    /*
    * 新增部门
    * */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门,dept={}",dept);
        deptService.add(dept);
        return Result.success();
    }

    /*
    * 根据ID查询部门
    * */
    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id){
        log.info("根据ID查询部门,id={}",id);
        Dept dept=deptService.getById(id);
        return Result.success(dept);
    }

    /*
    * 修改部门数据
    * */
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门,dept={}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
