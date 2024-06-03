package com.ashe.database.controller;

import com.ashe.database.view.RestResult;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class HelloController {

    @GetMapping("/hello")
    public HttpEntity<RestResult> hello() {
        return ResponseEntity.ok(RestResult.ok("hello"));
    }
}
