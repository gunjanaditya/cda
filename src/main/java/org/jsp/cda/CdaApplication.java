package org.jsp.cda;

import org.jsp.cda.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CdaApplication {

	public static void main(String[] args) {
		 ApplicationContext ac = SpringApplication.run(CdaApplication.class, args);
		 
		 User u = ac.getBean(User.class);
		 
		 System.out.println(u.getName());
		 System.out.println(u.getEmail());
		 System.out.println(u.getPhone());
	}

}
