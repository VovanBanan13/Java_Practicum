package ru.home.services;

import java.util.List;
import ru.home.models.User;

public interface UserService {
    List<User> findAllUsers();
    User getById(int id);
    void save(User user);
    void delete(int id);
    User update(int id, User user);
}
