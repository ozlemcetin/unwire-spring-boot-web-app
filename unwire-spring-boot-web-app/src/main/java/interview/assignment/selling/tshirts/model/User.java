package interview.assignment.selling.tshirts.model;

public class User extends Entity {

	private String userName;
	private boolean isAdmin;


	public User(String userName, boolean isAdmin) {

		super();
		this.userName = userName;
		this.isAdmin = isAdmin;
	}

	public String getUserName() {
		return userName;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", isAdmin=" + isAdmin + "]";
	}

}
