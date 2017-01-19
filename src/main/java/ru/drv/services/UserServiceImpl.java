package ru.drv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.drv.interfaces.UserDao;
import ru.drv.interfaces.UserService;
import ru.drv.models.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User getUser(int id) {
        return userDao.find(id);
    }

    @Override
    public int save(User user) {
        return 0;
    }
}
