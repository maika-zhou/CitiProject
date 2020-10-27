package com.action;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionsEndpoint
{

    @GetMapping("/incomes")
    public String getAllIncomes() {
        return "aaaaaaaaaa-->";
    }


}
