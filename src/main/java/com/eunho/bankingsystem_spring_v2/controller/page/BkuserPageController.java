package com.eunho.bankingsystem_spring_v2.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/bkuser")
@Controller
public class BkuserPageController {
    @GetMapping("/{page}")
    public String page(@PathVariable("page") String page){
        return "/bkuser/" + page;
    }
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id){
        return "/bkboard/detail";
    }
}
