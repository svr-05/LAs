package database.store;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class MusicStore {
	
	private HashMap<String, Album> store;
	
	public MusicStore() {
		this.store = new HashMap<String, Album>();
	}
	
	public void parseAlbums() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("albums.txt"));
			String albumLine = "";
			try {
				while ((albumLine = br.readLine()) != null) {
					String[] components = albumLine.split(",");
					String albumTitle = components[0].strip();
					String artist = components[1].strip();
					String albumFile = albumTitle + "_" + artist + ".txt"; // Assemble the name of the file containing file info
					
					parseAlbumInfo(albumTitle);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void parseAlbumInfo(String albumTitle) {
		// TODO Auto-generated method stub
		try {
			BufferedReader br = new BufferedReader(new FileReader(albumTitle));
			try {
				String lineOne = br.readLine();
				String[] info = lineOne.split(",");
				// recall "The first line of the file is the heading, which is in the following format":
				//                 Album Title    Artist    Genre                     Year
				Album album = new Album(info[0], info[1], info[2], Integer.parseInt(info[3]));
				
				String tracklistSong = "";
				while ((tracklistSong = br.readLine()) != null) {
					Song song = new Song(tracklistSong, info[1], info[0]); // Disorganized, possible bugs
					album.addSong(song); // add all the songs in the tracklist to the array of songs
				}
				store.put(info[0], album); // { "Album title": Album }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
