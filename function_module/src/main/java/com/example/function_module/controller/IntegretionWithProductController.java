package com.example.function_module.controller;

import com.example.function_module.controller.controller_consts.ControllerConsts;
import com.example.function_module.service.IntegretionWithProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class IntegretionWithProductController {

    private final IntegretionWithProductService integretionWithProductService;

    @GetMapping(ControllerConsts.INTEGRATION_WITH_MICROSERVICE_PRODUCT_MODULE)
    public String getIntegration(@RequestParam String name){

        return integretionWithProductService.integrationWithProduct(name);
    }
}
