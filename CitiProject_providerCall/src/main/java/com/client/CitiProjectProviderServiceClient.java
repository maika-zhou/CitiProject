package com.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="CitiProjectProvider")
public interface CitiProjectProviderServiceClient
{
    @GetMapping("/dept/getDept")
    public String getDept();
}
