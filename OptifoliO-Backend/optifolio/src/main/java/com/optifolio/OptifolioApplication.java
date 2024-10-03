package com.optifolio;

import com.optifolio.models.Enum;
import com.optifolio.models.User;
import com.optifolio.repositories.UserRepository;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.function.Function;


@SpringBootApplication(scanBasePackages = "com.optifolio")
@SecurityScheme(
		name = "bearerAuth",
		type = SecuritySchemeType.HTTP,
		scheme = "bearer",
		bearerFormat = "JWT"
)
@AllArgsConstructor
public class OptifolioApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(OptifolioApplication.class, args);
	}


	private UserRepository userRepository;
	@Override
	public void run(String... args) throws Exception {

        //CREATE USERS
		createUserIfNotExist("vishal.k3102@gmail.com","Vishal Kumar","1234","HS1456", Enum.RoleType.ADMIN);
		createUserIfNotExist("karande.vishal3102@gmail.com","Vishal ","vk1234","HS1656",Enum.RoleType.USER);
	}


    //FUNCTION TO CREATE USERS
	// FUNCTION TO CREATE USERS
	private void createUserIfNotExist(String emailId, String name, String password, String brokerId, Enum.RoleType role) {
		if (!userRepository.existsByEmailId(emailId)) {
			User user = new User();
			user.setEmailId(emailId);
			user.setName(name);
			user.setPassword(password);
			user.setBrokerId(brokerId);
			user.setRole(role);

			LocalDateTime now = LocalDateTime.now();
			user.setCreatedAt(now);
			user.setUpdatedAt(now);

			userRepository.save(user);
		}
	}
}
