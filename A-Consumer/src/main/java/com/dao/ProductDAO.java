package com.dao;


import com.pojo.Product;

import java.util.List;

public interface ProductDAO
{
    public List<Product> findAll();


    public Product findById(long pid);

    public int updateProduct(Product product);

}