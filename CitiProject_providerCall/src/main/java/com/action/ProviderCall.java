package com.action;

import com.client.CitiProjectProviderServiceClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderCall
{
    @Autowired
    private CitiProjectProviderServiceClient citiProjectProviderServiceClient;

    @HystrixCommand(fallbackMethod="getFallback")
    @RequestMapping(value = "/call", method = RequestMethod.GET)
    public String call()
    {
        String str = citiProjectProviderServiceClient.getDept();
        System.out.println( "--------> "+ str);

        //抛出一下exception，会执行fallback
        //System.out.println( 1/0 );
//        if (vo == null) {	// 数据不存在，假设让它抛出个错误
//            throw new RuntimeException("部门信息不存在！") ;
//        }


        return str;
    }


    public String getFallback() {	// 此时方法的参数 与get()一致
        return "----------> getFallback" ;
    }



}
