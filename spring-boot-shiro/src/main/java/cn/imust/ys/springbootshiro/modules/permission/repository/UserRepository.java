package cn.imust.ys.springbootshiro.modules.permission.repository;

import cn.imust.ys.springbootshiro.modules.permission.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface UserRepository/* extends JpaRepository<User,Integer> */{

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

}
