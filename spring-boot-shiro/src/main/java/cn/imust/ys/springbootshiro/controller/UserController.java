package cn.imust.ys.springbootshiro.controller;

import cn.imust.ys.springbootshiro.entity.User;
import cn.imust.ys.springbootshiro.service.UserService;
import cn.imust.ys.springbootshiro.utils.ControllerUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Map login(String username,
                     String password,
                     HttpSession session) {
        userService.UserfindByUsernameAndPassword(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            User user = (User) subject.getPrincipal();
            session.setAttribute("user", user);
            Map map = new HashMap();
            map.put("code",200);
            map.put("msg","登陆成功");
            map.put("data",new User(user.getUid(),user.getUsername()));
            map.put("sessionID",session.getId());
            return map;
        } catch (Exception e) {
            Map map = new HashMap();
            map.put("code",500);
            map.put("msg","登陆失败");
            map.put("data",null);
            return map;
        }
    }

    @RequestMapping("/logout")
    public Map logout(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        Map map = new HashMap();
        map.put("code",200);
        map.put("msg","退出成功");
        return map;
    }

    @RequestMapping("unauthorized")
    public Map unauthorized() {
        return ControllerUtils.getUnauthorizedMap();
    }

    @RequestMapping("redirtLogin")
    public Map redirtLogin(){
        return ControllerUtils.getLoginMap("会话失效，请先登陆");
    }
}
