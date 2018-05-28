package cn.imust.ys.springbootshiro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.jdbc.JdbcOperationsSessionRepository;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.session.jdbc.config.annotation.web.http.JdbcHttpSessionConfiguration;

import javax.sql.DataSource;

/*
 * 将session 进行拦截持久化到数据库中
 * */
//@Configuration
public class SpringSessionConfiguration {
//    @Bean
    public JdbcHttpSessionConfiguration jdbcHttpSessionConfiguration(){
        JdbcHttpSessionConfiguration jdbcHttpSessionConfiguration = new JdbcHttpSessionConfiguration();
        jdbcHttpSessionConfiguration.setMaxInactiveIntervalInSeconds(18000);
        return jdbcHttpSessionConfiguration;
    }

}
