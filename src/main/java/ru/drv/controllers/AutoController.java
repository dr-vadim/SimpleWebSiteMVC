package ru.drv.controllers;

import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
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

        if(userId <= 0) return modelAndView;

        User user = userService.getUser(userId);
        List<Auto> listAuto = autoService.getAutoByUser(user.getId());
        //req.setAttribute("userList", userList);
        modelAndView.setViewName("auto");
        modelAndView.addObject("user", user);
        modelAndView.addObject("listAuto", listAuto);
        return modelAndView;
    }
}
