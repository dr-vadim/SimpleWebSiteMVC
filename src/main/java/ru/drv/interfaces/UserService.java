package ru.drv.interfaces;

import ru.drv.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUser(int id);
    int save(User user);
}
