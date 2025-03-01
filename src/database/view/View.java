package database.view;

import java.util.Scanner;

import database.model.LibraryModel;
import database.model.MusicStore;

public class View {
	
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
				
				if(user_num == 1) {
					System.out.println("-------------------------------------------------------------------------------------------------");
					System.out.println("|					 Ty-Santi Music Store!  				|");
					System.out.println("-------------------------------------------------------------------------------------------------");
					System.out.println();
					
					
				} else if (user_num == 2){
					System.out.println("-------------------------------------------------------------------------------------------------");
					System.out.println("|					   Your Music Library!  				|");
					System.out.println("-------------------------------------------------------------------------------------------------");
					System.out.println();
				} else {
					do {
						System.out.print("Wong input -_-...Try again?");
						user_num = scanner.nextInt();
					} while(user_num != 1 && user_num != 2);
				}
				String while_input, garbage;
				do {
					System.out.print("Would you like to quit(Input Q)? ");
					garbage = scanner.nextLine();
					while_input = scanner.nextLine();
				} while(while_input.isEmpty());
				user_char = while_input.toLowerCase().charAt(0);
				System.out.println("\n");
			}
			System.out.print("OK HAVE A NICE DAY!!!");
		}
		scanner.close();
	}

}
