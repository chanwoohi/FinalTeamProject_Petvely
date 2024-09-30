package kr.kh.petvely.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "kr.kh.petvely")
public class PetvelyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetvelyApplication.class, args);
	}

}
