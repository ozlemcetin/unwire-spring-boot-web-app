package interview.assignment.selling.tshirts.web;

import org.springframework.boot.SpringApplication;

/*
 * * @author Ozlem Cetin  @date 21.09.2017
 * 
 */

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/*
 * Use @@ComponentScan("interview.assignment.selling.tshirts") to tell the spring to 
 * scan everything in "interview.assignment.selling.tshirts" packages and its subpackages.
 */

@SpringBootApplication
@ComponentScan("interview.assignment.selling.tshirts")
public class UnwireSpringBootWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnwireSpringBootWebAppApplication.class, args);
	}
}