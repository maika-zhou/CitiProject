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

import java.util.List;

@RestController
public class UserAction
{

    @Autowired
    DeptService deptService;




    @ApiOperation(value = "实现人员信息的添加处理", notes = "就是加人的，多么的简单")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "用户名字333", required = true, dataType = "int")})
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String add(@RequestBody Integer id)
    {
        System.out.println("ID = " + id);

        //用Mapper取数据
        Dept dept =  deptService.getDept(id);
		System.out.println( dept.getDeptCode()+"------------>"+dept.getDeptName()  );

        //用DAO取数据
        List<Dept> list =  deptService.findAll();
        System.out.println( "Dept.size ------------>"+list.size()  );

        return dept.toString();
    }



}
