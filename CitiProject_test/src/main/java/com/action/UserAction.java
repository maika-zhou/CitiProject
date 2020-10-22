package com.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "int")})
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public List<Dept> add(@RequestBody Integer id)
    {
        System.out.println("ID = " + id);

        //用Mapper取数据
        Dept dept =  deptService.getDept(id);
		System.out.println( dept.getDeptCode()+"------------>"+dept.getDeptName()  );

        //用DAO取数据
        List<Dept> list =  deptService.findAll();
        System.out.println( "Dept.size ------------>"+list.size()  );

        /**
         *  JSON -> Obj 转换， 参考 https://www.cnblogs.com/ibigboy/p/11124524.html
         *
         */
        //Obj -> JSON String
        String json =  JSON.toJSONString(list);
        //JSON String -> Obj
        List<Dept> list2 = JSON.parseObject(json,List.class);
        //Obj -> JSONObject  只有是单个VO类才可以转
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(dept);
        //Obj -> JSONObject  如果是List，必须要先转必须要先转JSONString, 随后再JSONArray
        String arrayStr =  JSON.toJSONString(list);
        JSONArray array = JSON.parseArray(arrayStr);
        for (Object d : array)
        {
            JSONObject obj = JSON.parseObject(d.toString());
            System.out.println("obj = " + obj.get("deptCode"));
        }

        System.out.println("json = " + json);
        return list2;
    }



}
