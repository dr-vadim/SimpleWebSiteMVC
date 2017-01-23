package ru.drv.interfaces;

import ru.drv.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUser(int id);
    User save(User user);
    boolean delete(int id);
    boolean update(int id, User user);
}
