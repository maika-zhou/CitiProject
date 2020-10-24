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

        System.out.println( 1/0 );

        return str;
    }


    public String getFallback() {	// 此时方法的参数 与get()一致
        return "----------> getFallback" ;
    }



}
