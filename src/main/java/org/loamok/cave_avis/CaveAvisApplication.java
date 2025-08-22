package org.loamok.cave_avis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class }) 
public class CaveAvisApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaveAvisApplication.class, args);
	}

}
