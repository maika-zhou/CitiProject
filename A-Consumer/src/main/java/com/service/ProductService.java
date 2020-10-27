package com.service;

import com.dao.ProductDAO;
import com.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService
{
    @Autowired
    private ProductDAO productDAO;


    public List<Product> findAll()
    {
        return productDAO.findAll();
    }

    public Product findById(long pid)
    {
        return productDAO.findById(pid);
    }

    public int updateProduct(Product product)
    {
        return productDAO.updateProduct(product);
    }



}
