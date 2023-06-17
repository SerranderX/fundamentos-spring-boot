package com.fundamentos.springboot.fundamentos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("/")
    @ResponseBody
    public ResponseEntity<String> functionEndPoint() {
        return new ResponseEntity<String>("hello world :D", HttpStatus.OK);
    }
}
