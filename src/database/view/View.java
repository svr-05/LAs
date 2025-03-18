package database.view;

import java.util.Scanner;

import database.model.LibraryModel;
import database.store.MusicStore;
/*
 * The view has been AI generated, we used a drafted code as "sample" so we could give AI an idea
 * on how to shape the view. The lines with // at the end were manually added
 */
public class View {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MusicStore musicStore = new MusicStore();
        LibraryModel libraryModel = new LibraryModel();
        musicStore.parseAlbums();

        System.out.println("------------------------------------------------------------"); //
        System.out.println("|              Welcome to Ty-Santi's Music Store!          |"); //
        System.out.println("------------------------------------------------------------"); //
        
        String userChoice;
        do {
            System.out.println("1 - Music Store"); //
            System.out.println("2 - Music Library"); //
            System.out.println("Q - Quit");
            System.out.print("Select an option: "); //
            userChoice = scanner.nextLine().toLowerCase();
            
            switch (userChoice) {
                case "1" -> handleMusicStore(scanner, musicStore);
                case "2" -> handleLibrary(scanner, libraryModel);
                case "q" -> System.out.println("Thank you for visiting Ty-Santi's Music Store!");
                default -> System.out.println("Invalid input. Please try again.");
            }
        } while (!userChoice.equals("q"));
        
        scanner.close();
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
        String choice; //
        do {
            System.out.println("\n-- Music Library --");
            System.out.println("1 - Add a Song to Library"); //
            System.out.println("2 - Add an Album to Library"); //
            System.out.println("3 - Search for a Song by Title"); //
            System.out.println("4 - Search for a Song by Artist"); //
            System.out.println("5 - Search for an Album by Title"); //
            System.out.println("6 - Search for an Album by Artist"); //
            System.out.println("7 - Create a Playlist"); //
            System.out.println("8 - Add Song to Playlist"); //
            System.out.println("9 - Remove Song from Playlist"); //
            System.out.println("10 - Mark Song as Favorite"); //
            System.out.println("11 - Rate a Song"); //
            System.out.println("12 - View Lists (Songs, Artists, Albums, Playlists, Favorites)"); //
            System.out.println("13 - Search for a Playlist by Name"); //
            System.out.println("B - Back");
            System.out.print("Select an option: "); //
            choice = scanner.nextLine().toLowerCase();
            
            switch (choice) {
                case "1" -> {
                    System.out.print("Enter song title: "); //
                    String title = scanner.nextLine();
                    libraryModel.addSong(title);
                }
                case "2" -> {
                    System.out.print("Enter album title: "); //
                    String title = scanner.nextLine();
                    libraryModel.addAlbum(title);
                }
                case "3" -> {
                    System.out.print("Enter song title: ");
                    String title = scanner.nextLine();
                    libraryModel.searchSongbyString(title);
                }
                case "4" -> {
                    System.out.print("Enter artist name: ");
                    String artist = scanner.nextLine();
                    libraryModel.searchSongByTitleArtist(artist);
                }
                case "5" -> {
                    System.out.print("Enter album title: ");
                    String title = scanner.nextLine();
                    libraryModel.searchAlbumbyString(title);
                }
                case "6" -> {
                    System.out.print("Enter artist name: ");
                    String artist = scanner.nextLine();
                    libraryModel.searchAlbumbyTitleAuthor(artist);
                }
                case "7" -> {
                    System.out.print("Enter playlist name: ");
                    String playlistName = scanner.nextLine();
                    libraryModel.makePlayList(playlistName);
                }
                case "8" -> {
                    System.out.print("Enter playlist name: ");
                    String playlistName = scanner.nextLine();
                    System.out.print("Enter song title: ");
                    String songTitle = scanner.nextLine();
                    libraryModel.addSongToPlayList(playlistName, songTitle);
                }
                case "9" -> {
                    System.out.print("Enter playlist name: ");
                    String playlistName = scanner.nextLine();
                    System.out.print("Enter song title: ");
                    String songTitle = scanner.nextLine();
                    libraryModel.removeSongFromPlayList(playlistName, songTitle);
                }
                case "10" -> {
                    System.out.print("Enter song title: ");
                    String songTitle = scanner.nextLine();
                    libraryModel.addFavorite(songTitle);
                }
                case "11" -> {
                    System.out.print("Enter song title: ");
                    String songTitle = scanner.nextLine();
                    System.out.print("Enter rating (1-5): ");
                    int rating = scanner.nextInt();
                    scanner.nextLine();
                    libraryModel.rateSong(songTitle, rating);
                }
                // to make it more user friendly, there's added flexibility to account for some typos the user might have
                case "12" -> {
                    System.out.print("Enter category (Songs, Artists, Albums, Playlists, Favorites): ");
                    String category = scanner.nextLine().toLowerCase();
                    if (category.contains("song") || category.contains("somg")) { //
                    	libraryModel.getSongTitles().forEach(System.out::println);
                    }
                    else if (category.contains("arti")) { //
                    	libraryModel.getArtists().forEach(System.out::println);
                    }
                    else if (category.contains("albu")) { //
                    	 libraryModel.getAlbumList().forEach(System.out::println);
                    }
                    else if (category.contains("playlis")) { //
                    	libraryModel.getPlayList().forEach(System.out::println);
                    }
                    else if (category.contains("fav")) { //
                    	libraryModel.getFavorites().forEach(System.out::println);
                    } else { //
                    	System.out.println("Invalid category. Please enter one of: Songs, Artists, Albums, Playlists, Favorites.");
                    }
                }
                case "13" -> {
                    System.out.print("Enter playlist name: ");
                    String playlistName = scanner.nextLine();
                    libraryModel.searchPlayListName(playlistName);
                }
                case "b" -> System.out.println("Returning to main menu...\n");
                default -> System.out.println("Invalid input. Please try again.\n");
            }
        } while (!choice.equals("b"));
    }
}
