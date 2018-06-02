package cn.imust.ys.springbootshiro.modules.permission.service;

import cn.imust.ys.springbootshiro.modules.permission.entity.User;
import cn.imust.ys.springbootshiro.modules.permission.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    UserRepository userRepository;

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User UserfindByUsernameAndPassword(String username, String password){
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
