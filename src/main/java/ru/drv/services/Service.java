package ru.drv.services;

import ru.drv.interfaces.AutoDao;
import ru.drv.interfaces.UserDao;
import ru.drv.models.Auto;
import ru.drv.models.User;

import java.util.List;

public class Service {

    private AutoDao autoDao;
    private UserDao userDao;

    public Service(UserDao userDao, AutoDao autoDao){
        this.userDao = userDao;
        this.autoDao = autoDao;
    }

    public List<User> getAllUsers(){
        return userDao.findAll();
    }

    public User getUser(int id){
        return userDao.find(id);
    }

    public List<Auto> getAutoByUser(int userId){
        return autoDao.findByUser(userId);
    }
}
