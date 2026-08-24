package com.zcf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClazzQueryParam {
    private Integer page = 1; //页码
    private Integer pageSize = 10; //每页记录数
    private String name; //班级名称
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin; //开班日期-开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end; //开班日期-结束时间
}