package view;

import model.LibraryModel;
import model.store.MusicStore;
import model.user.User;
import model.user.UserDatabase;
import java.util.Scanner;
/*
 * The view has been AI generated, we used a drafted code as "sample" so we could give AI an idea
 * on how to shape the view. The lines with // at the end were manually added
 */
public class View {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MusicStore musicStore = new MusicStore();
        UserDatabase userDatabase = new UserDatabase();
        musicStore.parseAlbums();

        while (true) {
            User currentUser = null;
            
            System.out.println("------------------------------------------------------------");
            System.out.println("|              Welcome to Ty-Santi's Music Store!          |");
            System.out.println("------------------------------------------------------------");
            
            // Login/Register loop
            while (currentUser == null) {
                System.out.println("1 - Login");
                System.out.println("2 - Register");
                System.out.println("Q - Quit");
                System.out.print("Select an option: ");
                String choice = scanner.nextLine().toLowerCase();
                
                switch (choice) {
                    case "1" -> {
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();
                        currentUser = userDatabase.loginUser(username, password);
                        if (currentUser == null) {
                            System.out.println("Invalid username or password.");
                        } else {
                            System.out.println("\n---------------------------");
                            System.out.println("Welcome back, " + username + "!");
                        }
                    }
                    case "2" -> {
                        System.out.print("Enter new username: ");
                        String username = scanner.nextLine();
                        if (userDatabase.usernameExists(username)) {
                            System.out.println("Username " + username + " already exists.");
                            continue;
                        }
                        System.out.print("Enter new password: ");
                        String password = scanner.nextLine();
                        if (userDatabase.registerUser(username, password)) {
                            System.out.println("Registration successful!");
                            System.out.println("... logging you in");
                            System.out.println("Welcome! " + username);
                            currentUser = userDatabase.loginUser(username, password);
                        }
                    }
                    case "q" -> {
                        System.out.println("Thank you for visiting Ty-Santi's Music Store!");
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("Invalid input. Please try again.");
                }
            }
            
            // Main menu loop
            String userChoice;
            do {
                System.out.println("\n1 - Music Store");
                System.out.println("2 - Music Library");
                System.out.println("3 - Logout");
                System.out.println("Q - Quit");
                System.out.print("Select an option: ");
                userChoice = scanner.nextLine().toLowerCase();
                
                switch (userChoice) {
                    case "1" -> handleMusicStore(scanner, musicStore);
                    case "2" -> handleLibrary(scanner, currentUser.getMusicLibrary());
                    case "3" -> {
                        currentUser.writeToFile(); // Save user's library before logout
                        System.out.println("Logged out successfully.");
                        userChoice = "3"; // Force the loop to exit
                    }
                    case "q" -> {
                        if (currentUser != null) {
                            currentUser.writeToFile(); // Save user's library before quitting
                        }
                        System.out.println("Thank you for visiting Ty-Santi's Music Store!");
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("Invalid input. Please try again.");
                }
            } while (!userChoice.equals("3")); // Exit loop on logout
        }
    }

    private static void handleMusicStore(Scanner scanner, MusicStore musicStore) {
        String choice;
        do {
            System.out.println("\n-- Music Store --"); 
            System.out.println("1 - Search for a Song by Title"); //
            System.out.println("2 - Search for a Song by Artist"); //
            System.out.println("3 - Search for an Album by Title"); //
            System.out.println("4 - Search for an Album by Artist"); //
            System.out.println("B - Back");
            System.out.print("Select an option: "); //
            choice = scanner.nextLine().toLowerCase();
            
            switch (choice) {
                case "1" -> {
                    System.out.print("Enter song title: ");
                    String title = scanner.nextLine();
                    musicStore.searchSongbyString(title);
                }
                case "2" -> {
                    System.out.print("Enter artist name: ");
                    String artist = scanner.nextLine();
                    musicStore.searchSongByTitleArtist(artist);
                }
                case "3" -> {
                    System.out.print("Enter album title: ");
                    String title = scanner.nextLine();
                    musicStore.searchAlbumbyString(title);
                }
                case "4" -> {
                    System.out.print("Enter artist name: ");
                    String artist = scanner.nextLine();
                    musicStore.searchAlbumbyTitleAuthor(artist);
                }
                case "b" -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid input. Please try again.");
            }
        } while (!choice.equals("b"));
    }

    private static void handleLibrary(Scanner scanner, LibraryModel libraryModel) {
        String choice;
        do {
            System.out.println("\n-- Music Library --");
            System.out.println("1 - Add a Song to Library");
            System.out.println("2 - Add an Album to Library");
            System.out.println("3 - Search for a Song by Title");
            System.out.println("4 - Search for a Song by Genre");
            System.out.println("5 - Search for a Song by Artist");
            System.out.println("6 - Search for an Album by Title");
            System.out.println("7 - Search for an Album by Artist");
            System.out.println("8 - Create a Playlist");
            System.out.println("9 - Add Song to Playlist");
            System.out.println("10 - Remove Song from Playlist");
            System.out.println("11 - Mark Song as Favorite");
            System.out.println("12 - Rate a Song");
            System.out.println("13 - View Lists (Songs, Artists, Albums, Playlists, Favorites, Frequent, Recent)");
            System.out.println("14 - Search for a Playlist by Name");
            System.out.println("15 - Play a Song by Title");
            System.out.println("B - Back");
            System.out.print("Select an option: ");
            choice = scanner.nextLine().toLowerCase();
            
            switch (choice) {
                case "1" -> {
                    System.out.print("Enter song title: ");
                    String title = scanner.nextLine();
                    libraryModel.addSong(title);
                }
                case "2" -> {
                    System.out.print("Enter album title: ");
                    String title = scanner.nextLine();
                    libraryModel.addAlbum(title);
                }
                case "3" -> {
                    System.out.print("Enter song title: ");
                    String title = scanner.nextLine();
                    libraryModel.searchSongbyString(title);
                }
                case "4" -> {
                    System.out.print("Enter song genre: ");
                    String genre = scanner.nextLine();
                    libraryModel.searchByGenre(genre);
                }
                case "5" -> {
                    System.out.print("Enter artist name: ");
                    String artist = scanner.nextLine();
                    libraryModel.searchSongByTitleArtist(artist);
                }
                case "6" -> {
                    System.out.print("Enter album title: ");
                    String title = scanner.nextLine();
                    libraryModel.searchAlbumbyString(title);
                }
                case "7" -> {
                    System.out.print("Enter artist name: ");
                    String artist = scanner.nextLine();
                    libraryModel.searchAlbumbyTitleAuthor(artist);
                }
                case "8" -> {
                    System.out.print("Enter playlist name: ");
                    String playlistName = scanner.nextLine();
                    libraryModel.makePlayList(playlistName);
                }
                case "9" -> {
                    System.out.print("Enter playlist name: ");
                    String playlistName = scanner.nextLine();
                    System.out.print("Enter song title: ");
                    String songTitle = scanner.nextLine();
                    libraryModel.addSongToPlayList(playlistName, songTitle);
                }
                case "10" -> {
                    System.out.print("Enter playlist name: ");
                    String playlistName = scanner.nextLine();
                    System.out.print("Enter song title: ");
                    String songTitle = scanner.nextLine();
                    libraryModel.removeSongFromPlayList(playlistName, songTitle);
                }
                case "11" -> {
                    System.out.print("Enter song title: ");
                    String songTitle = scanner.nextLine();
                    libraryModel.addFavorite(songTitle);
                }
                case "12" -> {
                    System.out.print("Enter song title: ");
                    String songTitle = scanner.nextLine();
                    System.out.print("Enter rating (1-5): ");
                    int rating = scanner.nextInt();
                    scanner.nextLine();
                    libraryModel.rateSong(songTitle, rating);
                }
                case "13" -> {
                    System.out.print("Enter category (Songs, Artists, Albums, Playlists, Favorites, Frequent, Recent): ");
                    String category = scanner.nextLine().toLowerCase();
                    if (category.contains("song") || category.contains("somg")) {
                        libraryModel.getSongTitles().forEach(System.out::println);
                    }
                    else if (category.contains("arti")) {
                        libraryModel.getArtists().forEach(System.out::println);
                    }
                    else if (category.contains("albu")) {
                        libraryModel.getAlbumList().forEach(System.out::println);
                    }
                    else if (category.contains("playlis")) {
                        libraryModel.getPlayList().forEach(System.out::println);
                    }
                    else if (category.contains("fav")) {
                        libraryModel.getFavorites().forEach(System.out::println);
                    }
                    else if (category.contains("freq")) {
                        libraryModel.getFrequentlyPlayed();
                    }
                    else if (category.contains("rec")) {
                        libraryModel.getRecentlyPlayed();
                    }
                    else {
                        System.out.println("Invalid category. Please enter one of: Songs, Artists, Albums, Playlists, Favorites.");
                    }
                }
                case "14" -> {
                    System.out.print("Enter playlist name: ");
                    String playlistName = scanner.nextLine();
                    libraryModel.searchPlayListName(playlistName);
                }
                case "15" -> {
                    libraryModel.playSong();
                }
                case "b" -> System.out.println("Returning to main menu...\n");
                default -> System.out.println("Invalid input. Please try again.\n");
            }
        } while (!choice.equals("b"));
    }
}
