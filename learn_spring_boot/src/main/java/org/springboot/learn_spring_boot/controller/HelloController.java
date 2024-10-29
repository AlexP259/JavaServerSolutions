package org.springboot.learn_spring_boot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @Value("${hello}")
    private String hello;

//    @RequestMapping("/hello")
    @GetMapping("/hello")
    public String hello(Model model){
//        System.out.println(this.hello);
        model.addAttribute("message", "Hello Spring Boot!");
        return "hello";
    }

}
















