# spring-boot-shiro
学习管理系统-后台服务



### 常见问题

##### JSON 格式化出错

出现无限循环， 在转 JSON 时，忽略引用字段就可以了 ，类上加注解

@JsonIgnoreProperties(value={"roles"})