package com.ashe.database.controller;

import com.ashe.database.service.OrderService;
import com.ashe.database.view.OrderReq;
import com.ashe.database.view.RestResult;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public HttpEntity<RestResult> create(@RequestBody OrderReq dto) {
        return ResponseEntity.ok(orderService.create(dto));
    }

    @PostMapping("/pages")
    public HttpEntity<RestResult> pages(@RequestBody OrderReq dto) {
        return ResponseEntity.ok(orderService.pages(dto));
    }
}
