package LA1;

import java.util.ArrayList;
import java.util.HashMap;

public class LibraryModel extends MusicStore{

	private ArrayList<PlayList> userList;
	private ArrayList<Object> library; // Holds in a Song or Album
	private HashMap<SongData, Boolean> favorites; // Holds the songs that are favorited
	
	//Constructors
	public LibraryModel() {
		this.userList = new ArrayList<>();
		this.library = new ArrayList<>();
		this.favorites = new HashMap<>();
	}

	//Getters
	public ArrayList<PlayList> getUserList() { ////////////////// DEBUG PURPOSES
		return new ArrayList<>(userList);
	}
	public ArrayList<Object> getLibrary() { /////////////////////  DEBUG PURPOSES
		return new ArrayList<>(library);
	}
	
	//Add Song/Album/PlayList to Library
	public void addSong(SongData S) {
		if(checkStoreSong(S.getTitle())) { library.add(new SongData(S));}
	}
	public void addAlbum(Album A) { 
		if(checkStoreAlbum(A.getName())) { library.add(A); }
	}
	public void addFavorite(SongData S) {	// Assumes Song is not already favorite
		if(S.favoriteStatus()) {
			favorites.put(S, S.favoriteStatus());
		} else {
			S.setFavorite();
			favorites.put(S, S.favoriteStatus());
		}
	}
	public void makePlayList(String name){ userList.add(new PlayList(name));}
	
	//Rate a Song
	public void rateSong(String stitle, int r) {
		for(SongData s: getSongs()) {
			if(s.getTitle().equals(stitle)) {
				if(r == 5) {
					s.rate(r);
					addFavorite(s);
				} else { s.rate(r); }
			}
		}
	}
	
	//Modify PlayList
	//Add Song
	public void addSongToPlayList(String PName, Song S) {
		for(PlayList p: userList) {
			if(p.getTitle().equals(PName)) {
				p.addSong(S);
			}
		}
	}
	
	//Remove Song
	public void removeSongFromPlayList(String PName, Song S) {
		for(PlayList p: userList) {
			if(p.getTitle().equals(PName)) {
				p.remove(S);
			}
		}
	}
	
	// Helper Methods that can retrieve
	// Retrieves the list of Songs from Library
	private ArrayList<SongData> getSongs(){
		ArrayList<SongData> result = new ArrayList<>();
		for(Object elem: library) {
			if(elem instanceof SongData) {
				SongData s = (SongData) elem;
				result.add(s);
			}
		}
		return result;
	}
	
	// Retrieves the list of Albums from library
	private ArrayList<Album> getAlbums(){
		ArrayList<Album> result = new ArrayList<>();
		for(Object elem: library) {
			if(elem instanceof Album) {
				Album a = (Album) elem;
				result.add(a);
			}
		}
		return result;
	}
	
	
	
}
