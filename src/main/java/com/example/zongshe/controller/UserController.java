package com.example.zongshe.controller;

import com.example.zongshe.entity.Doctor;
import com.example.zongshe.service.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private Doctor doctor;
    @Autowired
    ObjectMapper objectMapper;
    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login.html");
    }
    @PostMapping("/login")
    public String judge(HttpSession session, @RequestParam("id") String id, @RequestParam("psw") String psw,@RequestParam("remember") String re){
        System.out.println("用户 "+id+" 登录系统！");
        if(loginService.loginbyWid(id,psw)||loginService.loginbyName(id,psw)){
            session.setAttribute("user",doctor.getWid());
            session.setAttribute("psw",psw);
            session.setAttribute("rem",re);
            return "redirect:/usertable";
        }else {
            return "redirect:/login";
        }
    }
    @PostMapping("/login/getinfo")
    public void getInfo(HttpSession session,HttpServletResponse response){
        try {
            if(session.getAttribute("rem")==null||((String)session.getAttribute("rem")).isEmpty()){
                response.getWriter().print("");
                return;
            }
            String psw=(String) session.getAttribute("psw");
            if(psw==null){
                response.getWriter().print("");
            }else {
                response.getWriter().print(psw);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/login";
    }
    @GetMapping("/usertable")
    public ModelAndView userTable(){
        return new ModelAndView("frame.html");
    }
    @GetMapping("/usertable/main")
    public ModelAndView userTableMain(){
        return new ModelAndView("main.html");
    }
    @GetMapping("/usertable/bedinfo")
    public void bedInfo(HttpServletResponse response){
        List<Integer> li=loginService.getBedInfo();
        try {
            response.getWriter().print(objectMapper.writeValueAsString(li));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/usertable/personal")
    public ModelAndView userTablePersonal(){
        return new ModelAndView("personal.html");
    }
}
