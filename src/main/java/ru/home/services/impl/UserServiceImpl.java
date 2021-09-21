package ru.home.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.home.exceptions.ObjectNotFoundAdvice;
import ru.home.models.User;
import ru.home.repositories.UserRepository;
import ru.home.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = userRepository.findAll();
        if(users.isEmpty()) {
            throw new ObjectNotFoundAdvice();
        } else
            return users;
    }

    @Override
    public User getById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundAdvice());
    }

    @Override
    public void save(User user) {
        if(user.getName()==null || user.getRole()==null)
            throw new ObjectNotFoundAdvice();
        this.userRepository.save(user);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
