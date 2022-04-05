package com.example.zongshe;

import com.example.zongshe.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
    private LoginService loginService;
    @RequestMapping("/")
    public ModelAndView register() {
        return new ModelAndView("register.html");
    }
    @RequestMapping("/**/hey")
    public ModelAndView kamen() {
        return new ModelAndView("hey.html");
    }
    @GetMapping(
            value = {"/as","/test"}
    )
    public ModelAndView hello(){
        return new ModelAndView("as.html");
    }
    @GetMapping(
            value = {"/test"},
            params = {"wid"}
    )
    public ModelAndView test(@RequestParam("wid") String wid){
//        loginService.loginbyWid(wid);
        return new ModelAndView("as.html");
    }
}
