package database.view;

import java.util.ArrayList;
import java.util.Scanner;

import database.model.LibraryModel;
import database.model.MusicStore;

public class View {
	
	//AI GENERATED
    public static ArrayList<String> createList() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the PLAYLIST Name and SONG NAME you want to add separated by a comma(,): ");
        String inputLine = scanner.nextLine();
        scanner.close();
        
        String[] inputs = inputLine.split(",", 2); // Split into at most 2 parts
        ArrayList<String> list = new ArrayList<>();
        
        for (String input : inputs) {
            list.add(input.trim());
        }

        return list;
    }

	
	public static void main(String[] args) {
		MusicStore MS = new MusicStore();
		LibraryModel ML = new LibraryModel();
		MS.parseAlbums();
		char user_char;

		Scanner scanner = new Scanner(System.in);
		System.out.println("-------------------------------------------------------------------------------------------------");
		System.out.println("|				Welcome to Ty-Santi's Music Store!				|");
		System.out.println("-------------------------------------------------------------------------------------------------");
		System.out.print("Would you like to browse for Music?(Please answer with Y or N): ");
		String input = scanner.nextLine();
		user_char = input.toLowerCase().charAt(0);
		System.out.print("\n");
		
		if(user_char == 'n') {
			System.out.print("OK HAVE A NICE DAY!!!");
		} else {
			while (user_char != 'q') {
				System.out.println("-------------------------------------------------------------------------------------------------");
				System.out.println("|					  Pick N Choose!  					|");
				System.out.println("-------------------------------------------------------------------------------------------------");
				System.out.println("1 - Music Store");
				System.out.println("2 - Music Library\n");
				System.out.print("What would you like to check out? Input a number and go to that location: ");
				int user_num = scanner.nextInt();;
				System.out.println();
				
				String garbage;
				if(user_num == 1) {
					String store_input;
					do {
						System.out.println("-------------------------------------------------------------------------------------------------");
						System.out.println("|					 Ty-Santi Music Store!  				|");
						System.out.println("-------------------------------------------------------------------------------------------------");
						System.out.println("1 - Search for a Song by Title");
						System.out.println("2 - Search for a Song by Artist");
						System.out.println("3 - Search for a Album by Title");
						System.out.println("4 - Search for a Album by Artist\n");
						System.out.print("How are you searching? Input a number to continue: ");
						int user_ms = scanner.nextInt();
						
						if(user_ms == 1) {
							System.out.print("Please input the Title you want to find: ");
							String garbage_ms = scanner.nextLine();
							String user_title_s = scanner.nextLine();
							System.out.println("Searching Song by title...");
							MS.searchSongbyString(user_title_s);
							System.out.println();
						} else if ( user_ms == 2){
							System.out.print("Please input the Artist you want to find: ");
							String garbage_ms = scanner.nextLine();
							String user_title_a = scanner.nextLine();
							System.out.println("Searching Song by artist...");
							MS.searchSongbyString(user_title_a);
							System.out.println();
						} else if ( user_ms == 3) {
							System.out.print("Please input the Title of the Album you want to find: ");
							String garbage_ms = scanner.nextLine();
							String user_title_sl = scanner.nextLine();
							System.out.println("Searching Album by title...");
							MS.searchAlbumbyString(user_title_sl);
							System.out.println();
						} else if ( user_ms == 4) {
							System.out.print("Please input the Title of the Album you want to find: ");
							String garbage_ms = scanner.nextLine();
							String user_title_al = scanner.nextLine();
							System.out.println("Searching Album by artist...\n");
							MS.searchAlbumbyString(user_title_al);
							System.out.println();
						} else {
							do {
								System.out.print("Wong input -_-...Try again?");
								user_ms = scanner.nextInt();
							} while (user_ms != 1 && user_ms != 2 && user_ms != 3 && user_ms != 4);
							continue;
						} 
						
						do {
							System.out.print("Want to go Back (Input B)? Or quit (Input Q)? ");
							store_input = scanner.nextLine();
							user_char = store_input.toLowerCase().charAt(0);
						} while(user_char != 'b' && user_char != 'q');
						System.out.println();
					}while (user_char != 'q');
					
					do {
						System.out.print("Want to go Back to the Menu(Input B) or Quit(Input Q)? ");
						store_input = scanner.nextLine();
					} while(store_input.isEmpty());
					user_char = store_input.toLowerCase().charAt(0);
					System.out.println();
					if(user_char == 'b') {
						continue;
					} else {
						break;
					}
					
					
				} else if (user_num == 2){
					String library_input;
					
					do {
						System.out.println("-------------------------------------------------------------------------------------------------");
						System.out.println("|					   Your Music Library!  				|");
						System.out.println("-------------------------------------------------------------------------------------------------");
						System.out.println("1 - Add a Song to Library");
						System.out.println("2 - Add a Album to Library");
						System.out.println("3 - Get a List");
						System.out.println("4 - Make PlayList");
						System.out.println("5 - Add to a PlayList");
						System.out.println("6 - Remove from a PlayList");
						System.out.println("7 - Search for a Song by Title");
						System.out.println("8 - Search for a Song by Artist");
						System.out.println("9 - Search for a Album by Title");
						System.out.println("10 - Search for a Album by Artist");
						System.out.println("11 - Search for a PlayList");
						System.out.println("12 - Mark a Song as Favorite");
						System.out.println("13 - Rate a song");
						System.out.print("How are you searching? Input a number to continue: ");
						int user_ml = scanner.nextInt();
						
						if(user_ml == 1) {
							System.out.print("Please input the Song Title you want to Add: ");
							String garbage_ml = scanner.nextLine();
							String user_title_S = scanner.nextLine();
							System.out.println("Adding Song by title...");
							ML.addSong(user_title_S);
							System.out.println();
						} else if (user_ml == 2){
							System.out.print("Please input the Album Title you want to Add: ");
							String garbage_ml = scanner.nextLine();
							String user_title_A = scanner.nextLine();
							System.out.println("Adding Album by title...");
							ML.addAlbum(user_title_A);
							System.out.println();
						} else if (user_ml == 3){
							
						} else if (user_ml == 4){
							System.out.print("Please Make a Title for the Playlist you want to Add: ");
							String garbage_ml = scanner.nextLine();
							String user_title_P = scanner.nextLine();
							ML.makePlayList(user_title_P);
							System.out.println();
						} else if (user_ml == 5){
							ArrayList<String> addPLAYLIST = createList();
							ML.addSongToPlayList(addPLAYLIST.get(0), addPLAYLIST.get(1));
							System.out.println();
						} else if (user_ml == 6){
							
						} else if (user_ml == 7){
							
						} else if (user_ml == 8){
							
						} else if (user_ml == 9){
							
						} else if (user_ml == 10){
							
						} else if (user_ml == 11){
							
						} else if (user_ml == 12){
							
						} else if (user_ml == 13){
							
						} else {
							do {
								System.out.print("Wong input -_-...Try again?");
								user_ml = scanner.nextInt();
							} while (user_ml != 1 && user_ml != 2 && user_ml != 3 && user_ml != 4);
							continue;
						} 
						
						do {
							System.out.print("Want to go Back (Input B)? Or quit (Input Q)? ");
							garbage = scanner.nextLine();
							library_input = scanner.nextLine();
							user_char = library_input.toLowerCase().charAt(0);
						} while(user_char != 'b' && user_char != 'q');
						System.out.println();
					} while(user_char != 'q');
					
					do {
						System.out.print("Want to go Back to the Menu(Input B) or Quit(Input Q)? ");
						library_input = scanner.nextLine();
					} while(library_input.isEmpty());
					user_char = library_input.toLowerCase().charAt(0);
					System.out.println();
					if(user_char == 'b') {
						continue;
					} else {
						break;
					}
					
				} else {
					do {
						System.out.print("Wong input -_-...Try again( Input 1 or 2 )?");
						user_num = scanner.nextInt();
					} while(user_num != 1 && user_num != 2);
					continue;
				}
			}
			System.out.print("OK HAVE A NICE DAY!!!");
		}
		scanner.close();
	}
}
