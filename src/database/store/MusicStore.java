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

}
