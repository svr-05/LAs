package testmain;

import model.user.User;

public class TestMain {

	private static final String name = "Dylan";
	private static final String password = "5678";
	private static final String algorithm = "SHA-256";
	
	public static void main(String[] args) {
		User tuser = new User(name, password);
		System.out.println(tuser.getUsername().equals("Dylan")); // Checks if the Username initialized is the same as the constant
		System.out.println(tuser.getUsername() != null);
		System.out.println(tuser.getSalt() != null);
		System.out.println(tuser.getEncryptedPassword() != null);
		System.out.println(tuser.getMusicLibrary() != null);
		
	}
}
