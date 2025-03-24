package database.user;

import java.io.*;
import java.util.HashMap;
/* This class stores all of the users in the database by keeping track of their username */
public final class UserDatabase {
    private HashMap<String, User> users; // store users with username as key
    private static final String DB = "user_database.txt";

    public UserDatabase() {
        this.users = new HashMap<>();                             
        loadUsers(); // Load existing users from file
    }
    
    /* 
     * This method adds a new user to the database 
     * @pre: username != null && password != null
    */
    public boolean registerUser(String username, String password) {
        if (users.containsKey(username)) { // check if username is taken
            return false;                   
        }
        User newUser = new User(username, password);
        users.put(username, newUser); // add to users
        saveUsers();
        return true; // user registered
    }
    /* This method authenticates a user login 
     * 
     * @pre: username != null && password != null
    */
    public User loginUser(String username, String password) { 
        User user = users.get(username);
        if (user != null && user.verifyPassword(password)) { // First check if user exists
            user.readFromFile(); // Try to load existing library data
            return user;                                     
        }
        return null; // if login fails
    }
    /* This method checks if a username already exists in the database */
    public boolean usernameExists(String username) {
        return users.containsKey(username);
    }
    /* This method saves the user info to the txt file
     * 
     * The format for each user is:
     * username
     * salt
     * encrypted password
     */
    private void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DB))) {
            for (User user : users.values()) {
                writer.write(user.getUsername());
                writer.newLine();

                writer.write(User.bytesToString(user.getSalt()));
                writer.newLine();
                
                writer.write(user.getEncryptedPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /* This method loads existing users from the database file.
     * If it's the first time running the program, it starts with an empty file,
     */
    private void loadUsers() {
        File file = new File(DB);
        if (!file.exists()) { // if it's the first time running the program, the method stops at
            return;          // creating the file
        }
        // otherwise...
        try (BufferedReader reader = new BufferedReader(new FileReader(DB))) {
            String username;                                 // current username
            while ((username = reader.readLine()) != null) {
                String saltHex = reader.readLine();          // read salt value
                String hashedPassword = reader.readLine();   // read encrypted password               
            
                // create user with existing salt and password from file
                User user = new User(username, User.hexStringToByteArray(saltHex), hashedPassword);
                users.put(username, user); // add to users map
            }
        } catch (IOException e) {                 
            e.printStackTrace();                         
        }
    }

} 