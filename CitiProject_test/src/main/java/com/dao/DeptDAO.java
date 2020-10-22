package com.dao;

import java.util.List;

import com.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface DeptDAO
{
	public List<Dept> findAll();
}
