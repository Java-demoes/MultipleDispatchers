package com.test.demo.secure;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
public class ProtectedController {

    @RequestMapping("/hello")
    public ResponseEntity<String> helloWorld(){

        return ResponseEntity.ok("Hello world from ProtectedController");
    }
}
