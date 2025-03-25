package database.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;


class testUserDatabase {

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
