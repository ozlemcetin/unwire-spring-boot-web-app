package interview.assignment.selling.tshirts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import interview.assignment.selling.tshirts.model.User;
import interview.assignment.selling.tshirts.gateway.UserRepository;

/*
 * For dependency injection framework to work, step  number 1 tell that this is a spring bean.
 */

/*
 * To tell that this is a spring bean, use @Component on top of the class declaration. 
 * @Component tells that this something to be managed by the spring framework.
 */

/*
 *
 * @Controller indicates that the class handles requests from browsers.
 * @Service typically means anything contains the business logic. 
 * @Repository is used in terms of data stores.
 * @Component is the generic of the all these three scenarios.
 * 
 * Use them to say hey spring framework manage me!
 */

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;


	/*
	 * Only valid user names are 1) Admin or 2) User.
	 */
	public User findUserByUserName(String userName) {

		if (userName != null && userName.length() > 0) {

			return userRepository.findUserByUserName(userName);
		}

		return null;
	}

	public User addUser(String userName, boolean isAdmin) {

		if (userName != null && userName.length() > 0) {

			User user = new User(userName, isAdmin);
			userRepository.saveUser(user);

			return user;

		}

		return null;
	}

	

}
