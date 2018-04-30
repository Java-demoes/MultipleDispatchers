package com.test.demo.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenericController {

    @RequestMapping("/hello")
    public ResponseEntity<String> helloWorld(){
        // to show spring env works in this context
        return ResponseEntity.ok("Hello world from Genric Controller with / context");
    }
}
