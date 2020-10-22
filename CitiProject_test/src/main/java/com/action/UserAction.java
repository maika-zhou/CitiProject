package com.action;

import com.pojo.Dept;
import com.service.DeptService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAction
{

    @Autowired
    DeptService deptService;




    @ApiOperation(value = "实现人员信息的添加处理", notes = "就是加人的，多么的简单")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "用户名字333", required = true, dataType = "string")})
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String add(@RequestBody String username)
    {
        System.out.println("username = 11" + username);

        Dept dept =  deptService.getDept(1);
		System.out.println( dept.getDeptCode()+"------------>"+dept.getDeptName()  );


        return username;
    }



}
