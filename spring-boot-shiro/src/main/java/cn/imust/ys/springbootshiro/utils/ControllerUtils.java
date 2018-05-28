package cn.imust.ys.springbootshiro.utils;

import java.util.HashMap;
import java.util.Map;

public class ControllerUtils {
    /**
     * 执行成功返回的 Map
     * @param msg
     * @param obj
     * @return
     */
    public static Map getSuccessMap(String msg, Object obj){
        Map map = new HashMap();
        map.put("code", 200);
        map.put("msg",msg);
        map.put("data", obj);
        return map;
    }

    /**
     * 权限不足返回的 Map
     * @return
     */
    public static Map getUnauthorizedMap(){
        Map map = new HashMap();
        map.put("code", 403);
        map.put("msg","抱歉您的权限不足");
        return map;
    }

    /**
     * 会话失效跳转到登陆页
     * @param msg
     * @return
     */
    public static Map getLoginMap(String msg){
        Map map = new HashMap();
        map.put("code", 4327);
        map.put("msg", msg);
        return map;
    }
}
