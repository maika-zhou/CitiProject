package com.service;

import com.dao.DeptDAO;
import com.dao.DeptMapper;
import com.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptService
{
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private DeptDAO deptDAO;


    public Dept getDept(int id)
    {
        Dept dept = this.deptMapper.selectByPrimaryKey(id);

        return dept;
    }

    public List<Dept> findAll()
    {
        return deptDAO.findAll();
    }

}

