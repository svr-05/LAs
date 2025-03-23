package database.user;

import database.model.LibraryModel;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
/*
 * This class is used to create a User object with fields that are unique to each user like 
 * username, its salted and hashed password, and their music library. It also defines variables for
 * encryption and methods to update and save the user's library via txt files.
 */
public final class User { // No point in having a subclass for user
    private String username;
    private String encryptedPassword;
    private LibraryModel musicLibrary; // the user's library, fortunatley
   
    // encryption variables
    private byte[] salt;
    // constants
    private static final String ALGORITHM = "SHA-256";
    private static final char[] HEXARRAY = "0123456789ABCDEF".toCharArray();

    public User(String username, String password) {
        this.username = username;
        this.salt = createSalt(); // Idea: generate the random salt first, then hash the password
        this.encryptedPassword = generateHash(password, ALGORITHM, salt);
        this.musicLibrary = new LibraryModel();
    }

    // getters
    public String getUsername() { return username; }

    public String getEncryptedPassword() { return encryptedPassword; }

    public byte[] getSalt() { return salt.clone(); } // .clone() returns a copy of the salt array

    public LibraryModel getMusicLibrary() { return musicLibrary; }

    // setters
    public void setEncryptedPassword(String hashedPassword) 
                                                    { this.encryptedPassword = hashedPassword; }

    public void setSalt(byte[] salt) { this.salt = salt; }

    // encryption methods
    /* @pre: password != null && algorithm != null */
    private String generateHash(String password, String algorithm, byte[] salt) {
        try {
            // MessageDigest instances allow for performing a cryptographic algorithm (SHA-256)
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.reset(); // clear any previous data so there is no hash interference
            digest.update(salt); // appeend the salt to the MessageDigest
            
            // hash the current data (password)
            byte[] hash = digest.digest(password.getBytes());
            return bytesToString(hash);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return algorithm + " is not a valid algorithm";
        }
    }

    /* This method converts a byte array to a String of hex didgits */
    public static String bytesToString(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2]; // each byte becomes 2 hex chars
    
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF; // Convert byte to unsigned int
            hexChars[i * 2] = HEXARRAY[v >>> 4]; // get first hex digit
            hexChars[i * 2 + 1] = HEXARRAY[v & 0x0F]; // get the s econd hex digit
        }
        return new String(hexChars);
    }

    /* This method creates a random salt for the user's password */
    private static byte[] createSalt() {
        byte[] bytes = new byte[20];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes); // randomize the bytes in the array
        return bytes;
    }

    /* @pre: s != null */
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) { // process two chars at a time
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) // extract the first hex digit
                                 + Character.digit(s.charAt(i+1), 16)); // extract the second hex digit
        }
        return data; // Return the byte array
    }

    /* 
     *Idea1: create a txt file for each user with their respective username, encrypted password and
     *        library.
     * Idea2: record the library components in the txt file.
     * 
     * Layout:
     * salt
     * encrypted password
     * library components (see order below)
     * 
     * Usage example: when the user logs out, the libraty is recorded
     */
    public void writeToFile() {
        musicLibrary.setPrintToConsole(false);
        String filename = username + "_library.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(bytesToString(salt));
            writer.newLine();

            writer.write(encryptedPassword);
            writer.newLine();
            
            for (String songTitle : musicLibrary.getSongTitles()) {
                writer.write("SONG :" + songTitle);
                writer.newLine();
            }
            
            // write the album library
            for (String albumTitle : musicLibrary.getAlbumList()) {
                writer.write("ALBUM :" + albumTitle);
                writer.newLine();
            }
            
            // write the playlists
            for (String playlistName : musicLibrary.getPlayList()) {
                writer.write("PLAYLIST :" + playlistName);
                writer.newLine();
            }  
            // write favorites
            for (String favorite : musicLibrary.getFavorites()) {
                writer.write("FAVORITE :" + favorite);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /* This method reads the user's library info from a txt file and "restores" their current 
     * library.
     * 
     * Usage example: when the user logs in.
     */
    public void readFromFile() {
        String filename = username + "_library.txt";
        File file = new File(filename);
        
        // If file doesn't exist, this is a new user - just return as they start with empty library
        if (!file.exists()) {
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // read salt
            String saltHex = reader.readLine();
            if (saltHex != null) {
                this.salt = hexStringToByteArray(saltHex);
                this.encryptedPassword = reader.readLine();
            }
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String type = parts[0].trim();
                String content = parts[1].trim();
                    
                switch (type) {
                    case "SONG":
                        musicLibrary.addSong(content);
                        break;
                    case "ALBUM":
                        musicLibrary.addAlbum(content);
                        break;
                    case "PLAYLIST":
                        musicLibrary.makePlayList(content);
                        break;
                    case "FAVORITE":
                        musicLibrary.addFavorite(content);
                        break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /* @pre: password != null */
    public boolean verifyPassword(String password) {
        String inputHash = generateHash(password, ALGORITHM, salt);
        return inputHash.equals(this.encryptedPassword);
    }
    
} 