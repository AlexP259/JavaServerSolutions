package org.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/site")
public class MyController {


//    @ResponseBody
    @RequestMapping("/home")
    public String home(Model m){
        m.addAttribute("name", "Виктор Кимаковский");
        m.addAttribute("age", 34);

        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("HTML");
        list.add("JavaScript");
        m.addAttribute("nameList", list);

        return "home";
    }

//    @ResponseBody
    @RequestMapping("/login")
    public ModelAndView login(){
        ModelAndView model = new ModelAndView();

        model.addObject("address", "ул. Ленина");
        model.addObject("house", 56);

        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("HTML");
        list.add("JavaScript");
        model.addObject("nameList", list);

        model.setViewName("login");
        return model;
    }



}















