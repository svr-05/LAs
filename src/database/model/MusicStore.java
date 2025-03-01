package database.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import database.store.Album;
import database.store.Song;

import java.util.ArrayList;

public class MusicStore {
	
	private HashMap<String, Album> store;
	private ArrayList<SongData> storesongs;
	
	public MusicStore() {
		this.store = new HashMap<String, Album>();
		this.storesongs = new ArrayList<>();
	}
	
	public ArrayList<SongData> getSongData() {
	    ArrayList<SongData> copy = new ArrayList<>();
	    for (SongData song : storesongs) {
	        copy.add(new SongData(song));
	    }
	    return copy;
	}
	
	public HashMap<String, Album> getStore() { return new HashMap<>(this.store); }
	
	public void parseAlbums() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("albums.txt"));
			//File file = new File("albums.txt");
			//System.out.println("Looking for: " + file.getAbsolutePath());
			String albumLine = "";
			try {
				while ((albumLine = br.readLine()) != null) {
					String[] components = albumLine.split(",");
					String albumTitle = components[0].strip();
					String artist = components[1].strip();
					String albumFile = albumTitle + "_" + artist + ".txt"; // Assemble the name of the file containing file info
					
					parseAlbumInfo(albumFile);
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
					SongData songData = new SongData(tracklistSong, info[1], info[0]); // Disorganized, possible bugs
					Song song = new Song(tracklistSong, info[1], info[0]);
					storesongs.add(songData);
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
	
	//Checks if a song/album is in the store
	public boolean checkStoreSong(String sTitle){ // Searches for a Song in the HashMap/Music Store
		for(SongData s: storesongs) {
			if(s.getTitle().equals(sTitle)) return true;
		}
		return false;
	}
	
	public boolean checkStoreAlbum(String aTitle){ // Searches for a Album in the HashMap/Music Store
		for(String t: store.keySet()) {
			if(t.equals(aTitle)) return true;
		}
		return false;
	}
	
	//Search Methods
	//Search for Song by Tile or Artist
	public void searchSongbyString(String a_t){
		ArrayList<Song> songsByString = new ArrayList<>();
		for(Album a: store.values()) {	// Makes sure to retrieve the song that does match the artist and title
			for(Song s1: a.getSongs()) {
				if(a_t.equals(s1.getTitle()) && a_t.equals(s1.getAuthor())) { songsByString.add(s1); }
			}
			for(Song sr: a.getSongs()) {	// Makes sure to retrieve the songs with the same artist or name
				if (a_t.equals(sr.getTitle()) || a_t.equals(sr.getAuthor())){ songsByString.add(sr);}
			}
		}
		if(songsByString.size() == 0) {
			System.out.println("Item is not in your Library...Maybe buy it from the Music Store!");
		}
		for(Song p: songsByString) { // Prints the songs retrieved from the resulted iteration
			System.out.println(p.toString());
		}
	}
	
	//Search for an Album by Title or Artist
	public void searchAlbumbyString(String a_t){
		ArrayList<Album> albumsByString = new ArrayList<>();
		for(Album a1: store.values()) {	// Makes sure to retrieve the album that does match the artist and title
			if(a_t.equals(a1.getName()) && a_t.equals(a1.getArtist())) { albumsByString.add(a1); }
		}
		for(Album ar: store.values()) {	// Makes sure to retrieve the albums with the same artist or name
			if (a_t.equals(ar.getName()) || a_t.equals(ar.getArtist())){ albumsByString.add(ar);}
		}
		if(albumsByString.size() == 0) {
			System.out.println("Item is not in your Library...Maybe buy it from the Music Store!");
		}
		for(Album p: albumsByString) { // Prints the albums retrieved from the resulted iteration
			System.out.println(p.toString());
		}
	}
	
}