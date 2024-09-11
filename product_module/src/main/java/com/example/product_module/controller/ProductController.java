package com.example.product_module.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {


    @GetMapping("/test")
    public String getTest(@RequestParam String name){

        return "Это строка из микросервиса product " + name;
    }

}
