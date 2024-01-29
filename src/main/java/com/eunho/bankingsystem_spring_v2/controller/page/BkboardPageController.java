package com.eunho.bankingsystem_spring_v2.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


//bkboard의 페이지 url
// /bkboard
@RequestMapping("/bkboard")
@Controller
public class BkboardPageController {
    
    ///bkboard/{page}
    // page를 url value 값으로 받네.
    @GetMapping("/{page}")
    public String page(@PathVariable("page") String page){
        return "/bkboard/" + page;
    }
    
    // /bkboard/detail/{id}
    // bkboard {id}를 bkboard url로 받네
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id){
        return "/bkboard/detail";
    }

}
