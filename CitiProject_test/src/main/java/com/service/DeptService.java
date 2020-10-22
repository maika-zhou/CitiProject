package com.service;

import com.dao.DeptMapper;
import com.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeptService
{
    @Autowired
    private DeptMapper deptMapper;


    public Dept getDept(int id)
    {
        Dept dept = this.deptMapper.selectByPrimaryKey(id);

        return dept;
    }



}

