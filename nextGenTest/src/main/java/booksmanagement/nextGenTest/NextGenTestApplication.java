package booksmanagement.nextGenTest;

import booksmanagement.nextGenTest.entities.Writings;
import booksmanagement.nextGenTest.entities.Roles_Manager;
import booksmanagement.nextGenTest.entities.User_Manager;
import booksmanagement.nextGenTest.services.BooksService;
import booksmanagement.nextGenTest.services.ManagementUsers_Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class NextGenTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(NextGenTestApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


	@Bean
	CommandLineRunner start(ManagementUsers_Service managementUsers_service, BooksService booksService){
		return args -> {


			//On enregistre quelques roles
			managementUsers_service.addRoles(new Roles_Manager(null,"recherche"));
			managementUsers_service.addRoles(new Roles_Manager(null,"ajout"));
			managementUsers_service.addRoles(new Roles_Manager(null,"recuperation"));
			managementUsers_service.addRoles(new Roles_Manager(null,"test"));
			//On enregistre quelques utilisateurs
			managementUsers_service.addUser(new User_Manager(null,"Crespo","123",new ArrayList<>()));
			managementUsers_service.addUser(new User_Manager(null,"Stephane","456",new ArrayList<>()));
			managementUsers_service.addUser(new User_Manager(null,"Alice","789",new ArrayList<>()));

			//On enregistre quelques livres
			booksService.addBook(new Writings(null,"DataScience","Einstein",null,"science"));
			booksService.addBook(new Writings(null,"Mathematiques","Ramanoudjan",null,"maths"));
			booksService.addBook(new Writings(null,"pere riche pere pauvre","Robert Kiosaki",null,"culture"));


			//Ici on va attribuer des roles aux utilisateurs
			managementUsers_service.addRolesToUser("Crespo","ajout");
			managementUsers_service.addRolesToUser("Crespo","recherche");
			managementUsers_service.addRolesToUser("Crespo","recuperation");
			managementUsers_service.addRolesToUser("Crespo","test");
			managementUsers_service.addRolesToUser("Stephane","ajout");
			managementUsers_service.addRolesToUser("Stephane","recuperation");
			managementUsers_service.addRolesToUser("Alice","recherche");
			managementUsers_service.addRolesToUser("Alice","recuperation");

		};
	}

}
