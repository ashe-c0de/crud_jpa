package com.ashe.database.controller;

import com.ashe.database.service.CustomerService;
import com.ashe.database.view.CreateCustomerReq;
import com.ashe.database.view.RestResult;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public HttpEntity<RestResult> create(@RequestBody CreateCustomerReq request) {
        return ResponseEntity.ok(customerService.create(request));
    }

}
