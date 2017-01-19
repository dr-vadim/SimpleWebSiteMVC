package ru.drv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.drv.interfaces.UserService;
import ru.drv.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UsersController{


    private UserService userService;

    @Autowired
    public UsersController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(value = {"/","/users","/users.jsp"})
    @ResponseBody public ModelAndView index() throws Exception {
        System.out.println("Users controller run");
        ModelAndView modelAndView = new ModelAndView();
        List<User> userList = userService.getAllUsers();
        //req.setAttribute("userList", userList);
        modelAndView.setViewName("index");
        modelAndView.addObject("userList", userList);
        return modelAndView;
    }

    ModelAndView addUser(@ModelAttribute("user") User user){
        ModelAndView modelAndView = new ModelAndView();

        return modelAndView;
    }
}
