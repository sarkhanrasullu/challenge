package com.outfittery;

import com.outfittery.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringbootJwtApplication {

	public static void main(String[] args) {
             BCryptPasswordEncoder b = new BCryptPasswordEncoder();
            System.out.println("pass="+b.encode("111111"));
		SpringApplication.run(SpringbootJwtApplication.class, args);
	}
}
