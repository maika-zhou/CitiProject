package com.action;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptAction
{

    @ApiOperation(value = "实现人员信息的添加处理", notes = "就是加人的，多么的简单")
    @RequestMapping(value = "/dept/getDept", method = RequestMethod.GET)
    public String getDept()
    {
        System.out.println("ddddddd ");


        return "getDept";
    }



}
