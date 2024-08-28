package org.pragadeesh.ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/Home")
    public String home() {
        return "Welcome to authenticated URL!!!";
    }
}
