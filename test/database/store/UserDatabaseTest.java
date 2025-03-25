package model.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.*;
import java.io.*;

class UserDatabaseTest {

    private UserDatabase userDatabase;
    private File originalDbFile;
    private File testDbFile;
	
    @BeforeEach
    void setUp() throws IOException {
        originalDbFile = new File("user_database.txt");
        testDbFile = new File("test_user_database.txt");

        if (originalDbFile.exists()) {
            originalDbFile.renameTo(new File("backup_user_database.txt"));
        }

        testDbFile.createNewFile();
        userDatabase = new UserDatabase();
    }

    @AfterEach
    void tearDown() {
        if (testDbFile.exists()) {
            testDbFile.delete();
        }

        File backupFile = new File("backup_user_database.txt");
        if (backupFile.exists()) {
            backupFile.renameTo(originalDbFile);
        }
    }

    @Test
    void testRegisterUserAndSave() throws IOException {
        assertTrue(userDatabase.registerUser("user1", "password1"));
        assertTrue(userDatabase.registerUser("user2", "password2"));
        assertTrue(testDbFile.exists());

        try (BufferedReader reader = new BufferedReader(new FileReader(testDbFile))) {
            int lineCount = 0;
            while (reader.readLine() != null) {
                lineCount++;
            }
        }
    }

    @Test
    void testLoginUser() throws IOException {
        assertTrue(userDatabase.registerUser("user1", "password1"));

        User loggedInUser = userDatabase.loginUser("user1", "password1");
        assertNotNull(loggedInUser);
        assertEquals("user1", loggedInUser.getUsername());

        User failedLogin = userDatabase.loginUser("user1", "wrongPassword");
        assertNull(failedLogin, "Login with incorrect password should fail");
    }
    
    @Test
    void testLoadUsers() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("user_database.txt"))) {
            writer.write("user1");
            writer.newLine();
            writer.write("abcd1234");
            writer.newLine();
            writer.write("encryptedPassword1");
            writer.newLine();

            writer.write("user2");
            writer.newLine();
            writer.write("efgh5678");
            writer.newLine();
            writer.write("encryptedPassword2");
            writer.newLine();
        }
        userDatabase = new UserDatabase();

        assertTrue(userDatabase.usernameExists("user1"));
        assertTrue(userDatabase.usernameExists("user2"));
    }
}