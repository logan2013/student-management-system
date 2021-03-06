package cn.imust.ys.springbootshiro.modules.permission.controller;

import cn.imust.ys.springbootshiro.modules.permission.entity.User;
import cn.imust.ys.springbootshiro.modules.permission.service.UserService;
import cn.imust.ys.springbootshiro.modules.teacher.entity.Teacher;
import cn.imust.ys.springbootshiro.modules.teacher.repository.TeacherRepository;
import cn.imust.ys.springbootshiro.utils.ControllerUtils;
import cn.imust.ys.springbootshiro.utils.SessionUtils;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired private TeacherRepository teacherRepository;

    @RequestMapping("/login")
    public Map login(@RequestBody String params) {
        Map<String, Object> paramsMap = JSON.parseObject(params, Map.class);
        String username = (String) paramsMap.get("username");
        String password = (String) paramsMap.get("password");
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            Teacher teacher = (Teacher) subject.getPrincipal();
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("user", teacher);
            Map map = new HashMap();
            map.put("code", 200);
            map.put("msg", "登陆成功");
            map.put("data", teacher);
            map.put("sessionID", session.getId());
            return map;
        } catch (Exception e) {
            Map map = new HashMap();
            map.put("code", 201);
            map.put("msg", "登陆失败");
            map.put("data", null);
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
        map.put("code", 200);
        map.put("msg", "退出成功");
        return map;
    }

    @GetMapping("getUser")
    public Map getUser(HttpSession session){
        Teacher teacher = (Teacher) session.getAttribute("user");
        Teacher one = teacherRepository.findOne(teacher.getTid());
        return ControllerUtils.getSuccessMap(one);
    }

    @RequestMapping("unauthorized")
    public Map unauthorized(HttpServletResponse response) {
        response.setStatus(403);
        return ControllerUtils.getUnauthorizedMap();
    }

    @RequestMapping("redirtLogin")
    public Map redirtLogin() {
        return ControllerUtils.getLoginMap("会话失效，请重新登陆");
    }
}
