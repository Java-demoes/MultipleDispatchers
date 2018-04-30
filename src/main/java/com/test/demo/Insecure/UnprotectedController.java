package com.test.demo.Insecure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insecure")
public class UnprotectedController {
    @Autowired
    private Environment env;

    @RequestMapping("/hello")
    public ResponseEntity<String> helloWorld(){
        // to show spring env works in this context
        System.out.println(env.getProperty("environment"));
        return ResponseEntity.ok("Hello world from unprotected Controller A");
    }
}
