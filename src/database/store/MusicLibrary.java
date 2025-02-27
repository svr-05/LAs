package LA1;

import java.util.ArrayList;
import java.util.HashMap;

public class MusicLibrary extends MusicStore{

	private ArrayList<PlayList> userList;
	private ArrayList<Object> library; // Holds in a Song, Album, or Playlist
	private HashMap<Song, Boolean> favorites; // Holds the songs that are favorited
	
	//Constructors
	public MusicLibrary() {
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
	
	//Gen Methods
	//Add Song/Album
	public void addSong(Song S) { 
		if(checkStoreSong(S.getTitle())) { library.add(new Song(S));}
	}
	public void addAlbum(Album A) { 
		if(checkStoreAlbum(A.getName())) { library.add(A); }
	}
	public void addFavorite(Song S) {	// Assumes Song is not already favorite
		if(S.favoriteStatus()) {
			favorites.put(S, S.favoriteStatus());
		} else {
			S.setFavorite();
			favorites.put(S, S.favoriteStatus());
		}
	}

	//Create PlayList
	public void makePlayList(String name){ library.add(new PlayList(name));}
	
	//Rate a Song
	public void rateSong(String stitle, int r) {
		for(Song s: getSongs()) {
			if(s.getTitle().equals(stitle)) {
				if(r == 5) {
					s.rate(r);
					addFavorite(s);
				} else { s.rate(r); }
			}
		}
	}
	
	
	//Methods that can retrieve
	// Retrieves the list of Songs from Library
	public ArrayList<Song> getSongs(){
		ArrayList<Song> result = new ArrayList<>();
		for(Object elem: library) {
			if(elem instanceof Song) {
				Song s = (Song) elem;
				result.add(s);
			}
		}
		return result;
	}
	
	// Retrieves the list of Albums from library
	public ArrayList<Album> getAlbums(){
		ArrayList<Album> result = new ArrayList<>();
		for(Object elem: library) {
			if(elem instanceof Album) {
				Album a = (Album) elem;
				result.add(a);
			}
		}
		return result;
	}
	
	// Retrieves the List of PlayList created
	public ArrayList<PlayList> getPlayLists(){
		ArrayList<PlayList> result = new ArrayList<>();
		for(Object elem: library) {
			if(elem instanceof PlayList) {
				PlayList p = (PlayList) elem;
				result.add(p);
			}
		}
		return result;
	}
	
	
	
	
}
