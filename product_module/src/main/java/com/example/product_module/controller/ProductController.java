package com.example.product_module.controller;

import com.example.product_module.controller.controller_consts.ControllerConsts;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {


    @GetMapping(ControllerConsts.GET_INFO_FROM_PM)
    public String getTest(@RequestParam String name){

        return "Это строка из микросервиса product " + name;
    }

}
