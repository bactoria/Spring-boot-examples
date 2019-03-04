package com.example._22_Cache.user;

import com.example._22_Cache.exceptions.NullUserException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable(cacheNames="users")
    public List<User> getUsers() throws InterruptedException {
        Thread.sleep(3000);
        return userRepository.findAll();
    }

    @Cacheable(cacheNames="user", key="#id")
    public User getUser(String id) throws NullUserException, InterruptedException {
        Thread.sleep(3000);
        return userRepository.findById(id).orElseThrow(NullUserException::new);
    }
}
