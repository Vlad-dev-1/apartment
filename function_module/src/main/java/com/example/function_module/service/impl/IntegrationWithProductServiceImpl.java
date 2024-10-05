package com.example.function_module.service.impl;

import com.example.function_module.repository.IntegrationRepository;
import com.example.function_module.service.IntegretionWithProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class IntegrationWithProductServiceImpl implements IntegretionWithProductService {

    private final IntegrationRepository integrationRepository;

    public final static String URL = "http://localhost:9292/test?name=%s";


    @Override
    public String integrationWithProduct(String name) {

        RestTemplate restTemplate = new RestTemplate();

        String responceBody = restTemplate
                .exchange(prepareUrl(name),
                        HttpMethod.GET,
                        new HttpEntity<>(null, null),
                        String.class)
                .getBody();

        return responceBody;
    }

    private String prepareUrl(String value) {

        return String.format(URL, value);
    }
}
