package interview.assignment.selling.tshirts.gateway;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import interview.assignment.selling.tshirts.model.User;
import interview.assignment.selling.tshirts.model.Tshirt;

@Repository
public class UserRepository {

	private static ArrayList<User> users;

	static {
		users = new ArrayList<User>();

		// Create User with an Admin Role a
		users.add(new User("admin", true));

		// Create User with a No Admin Role
		users.add(new User("user", false));

	}

	public void saveUser(User user) {
		users.add(user);
	}

	public User findUserByUserId(String userId) {

		for (User user : users) {
			if (user.getId().equals(userId))
				return user;
		} // For Loop

		return null;
	}

	public User findUserByUserName(String userName) {

		for (User user : users) {
			if (user.getUserName().equals(userName))
				return user;
		} // For Loop

		return null;
	}

}
