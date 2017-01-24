package ru.drv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ru.drv.exceptions.UserNotFoundException;
import ru.drv.interfaces.UserService;
import ru.drv.models.User;
import org.springframework.stereotype.Controller;
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
        modelAndView.addObject("userForm", new User());
        return modelAndView;
    }

    @GetMapping("/user/{user-id}")
    @ResponseBody ModelAndView userPage(@PathVariable("user-id") int id) throws Exception {
        System.out.println("userPage run");
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(id);

        if(id <= 0) throw new UserNotFoundException("user Not Found");

        User user = userService.getUser(id);
        modelAndView.setViewName("user");
        modelAndView.addObject("userForm", user);
        return modelAndView;
    }

    @PostMapping("/user")
    public String update(@ModelAttribute("userForm") User user){
        System.out.println(user);
        userService.update(user.getId(),user);
        return "redirect:/user/"+user.getId();
    }

    @PutMapping("/user")
    String add(@ModelAttribute("userForm") User user){
        System.out.println("addUser");
        User userSaved = userService.save(user);
        System.out.println("id: "+userSaved.getId());
        return "redirect:/users";
    }

    @DeleteMapping("/user/{user-id}")
    String delete(@PathVariable("user-id") int id){
        userService.delete(id);
        return "redirect:/users";
    }
}
