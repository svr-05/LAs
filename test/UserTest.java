package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import model.LibraryModel;
import model.Song;
import model.user.User;

class UserTest {
	
	private static final String name = "Dylan";
	private static final String password = "5678";
	private static final String algorithm = "SHA-256";

	@Test
	void testUserCreation() {
		User tuser = new User(name, password);
		assertEquals(tuser.getUsername(), "Dylan"); // Checks if the Username initialized is the same as the constant
		assertNotNull(tuser.getUsername());
		assertNotNull(tuser.getSalt());
		assertNotNull(tuser.getEncryptedPassword());
		assertNotNull(tuser.getMusicLibrary());
	}
	
	@Test
	void testPasswordHash() {
		User tuser = new User(name, password);
		User tuser1 = new User(name, password);
		assertNotEquals(tuser.getEncryptedPassword(), tuser1.getEncryptedPassword());
	}
	
    @Test
    void testSaltGeneration() {
    	User tuser = new User(name, password);
		User tuser1 = new User(name, password);
        assertNotEquals(Arrays.toString(tuser.getSalt()), Arrays.toString(tuser1.getSalt()));
        
        assertEquals(20, tuser.getSalt().length);
    }
    
    @Test
    void testHexStringToByteArray_ValidHex() {
        String hexString = "4A6F686E"; // "John" in ASCII hex
        byte[] expectedBytes = { 0x4A, 0x6F, 0x68, 0x6E };
        assertArrayEquals(expectedBytes, User.hexStringToByteArray(hexString));
    }
    
    @Test
    void testSetEncrypted() {
    	User tuser = new User(name, password);
    	tuser.setEncryptedPassword("Byebye");
        assertEquals(tuser.getEncryptedPassword(), "Byebye");
    }
    
    @Test
    void testSalt() {
    	User tuser = new User(name, password);
        byte[] salt = {1, 2, 3, 4};
        tuser.setSalt(salt);
        assertArrayEquals(salt, tuser.getSalt());
    }
    
    @TempDir
    Path tempDir; // JUnit will create a temporary directory for testing

    @Test
    void testWriteToFile() throws IOException {
        // Arrange: Create a User instance with sample data
        User user = new User("testUser", "securePassword");
        user.getMusicLibrary().addSong("Tired");
        user.getMusicLibrary().addAlbum("19");
        user.getMusicLibrary().makePlayList("Pl");
        user.getMusicLibrary().addFavorite("Tired");

        // Redirect output to a temp file
        String filename = tempDir.resolve("testUser_library.txt").toString();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(User.bytesToString(user.getSalt()));
            writer.newLine();
            writer.write(user.getEncryptedPassword());
            writer.newLine();
            writer.write("SONG :Tired");
            writer.newLine();
            writer.write("ALBUM :19");
            writer.newLine();
            writer.write("PLAYLIST :Pl");
            writer.newLine();
            writer.write("FAVORITE :Tired");
            writer.newLine();
        }

        // Act: Call writeToFile()
        user.writeToFile();

        // Assert: Check if the file is created
        File file = new File(filename);
        assertTrue(file.exists());

        // Assert: Check if the file contains expected content
        List<String> lines = Files.readAllLines(file.toPath());
        assertEquals(User.bytesToString(user.getSalt()), lines.get(0)); // Checks if username matches
        assertEquals(user.getEncryptedPassword(), lines.get(1)); // Checks if encrypted password matches
        assertTrue(lines.contains("SONG :Tired"));
        assertTrue(lines.contains("ALBUM :19"));
        assertTrue(lines.contains("PLAYLIST :Pl"));
        assertTrue(lines.contains("FAVORITE :Tired"));

        // Cleanup: Delete test file
        file.delete();
    }

    @Test
    void testReadFromFile(@TempDir Path tempDir) throws IOException {
        User user = new User("testUser", "testPassword");
        user.getMusicLibrary().addSong("Tired");
        user.getMusicLibrary().addAlbum("19");
        user.getMusicLibrary().makePlayList("PL");
        user.getMusicLibrary().addFavorite("Tired");
        
        byte[] salt = user.getSalt();
        String encryptedPassword = user.getEncryptedPassword();
        
        File testFile = tempDir.resolve(user.getUsername() + "_library.txt").toFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFile))) {
            writer.write("0102030405060708090A0B0C0D0E0F10111213\n");
            writer.write(encryptedPassword + "\n");
            writer.write("SONG :Tired\n");
            writer.write("ALBUM :19\n");
            writer.write("PLAYLIST :PL\n");
            writer.write("FAVORITE :Song title: Tired, Author: Adele, Album: 19, Rating: ★ ★ ★ ★ ★, Favorite\n");
        }
        user.readFromFile();

        assertNotNull(user.getSalt());
        assertEquals(20, user.getSalt().length);

        assertTrue(user.getMusicLibrary().getSongTitles().contains("Tired"));
        assertTrue(user.getMusicLibrary().getAlbumList().contains("19"));
        assertTrue(user.getMusicLibrary().getPlayList().contains("PL"));

        User newUser = new User("nonExistentUser", "somePassword");
        assertDoesNotThrow(newUser::readFromFile);
    }
  
}
