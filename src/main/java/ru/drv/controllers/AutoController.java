package ru.drv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.drv.exceptions.AutoNotFoundException;
import ru.drv.exceptions.UserNotFoundException;
import ru.drv.interfaces.AutoService;
import ru.drv.interfaces.UserService;
import ru.drv.models.Auto;
import ru.drv.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import org.springframework.stereotype.Controller;

@Controller
public class AutoController{

    @Autowired
    private AutoService autoService;
    @Autowired
    private UserService userService;

    @GetMapping("/user/{user-id}/autos")
    public ModelAndView index(@PathVariable("user-id") int userId) throws Exception {
        System.out.println("Auto controller run");
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(userId);

        if(userId <= 0) throw new UserNotFoundException("User not found");

        User user = userService.getUser(userId);
        List<Auto> listAuto = autoService.getAutoByUser(user.getId());
        //req.setAttribute("userList", userList);
        modelAndView.setViewName("listAuto");
        modelAndView.addObject("user", user);
        modelAndView.addObject("listAuto", listAuto);
        return modelAndView;
    }

    @GetMapping("/auto/{auto-id}")
    @ResponseBody ModelAndView auto(@PathVariable("auto-id") int id) throws AutoNotFoundException,
            UserNotFoundException {
        ModelAndView modelAndView = new ModelAndView();

        if(id <= 0) throw new AutoNotFoundException("Auto not found");

        Auto auto = autoService.get(id);
        if(auto == null) throw new AutoNotFoundException("Auto not found");
        User user = userService.getUser(auto.getUser().getId());
        if(user == null) throw new UserNotFoundException("User not found");

        modelAndView.setViewName("auto");
        modelAndView.addObject("auto", auto);
        modelAndView.addObject("user", user);

        return modelAndView;
    }


}
