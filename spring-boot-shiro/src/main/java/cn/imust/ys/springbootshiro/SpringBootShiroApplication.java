package cn.imust.ys.springbootshiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@SpringBootApplication
@EnableJdbcHttpSession
public class SpringBootShiroApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootShiroApplication.class, args);
	}

}
