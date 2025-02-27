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
	}

	//Getters
	public ArrayList<PlayList> getUserList() {
		return new ArrayList<>(userList);
	}

	public ArrayList<Object> getLibrary() { // Might need to make this into a deep copy
		return new ArrayList<>(library);
	}
	
	//Add Song/Album
	public void addSong(Song S) {library.add(new Song(S));}
	
	public void addAlbum(Album A) {library.add(A);}

	//Create PlayList
	public void makePlayList(String name){ library.add(new PlayList(name));}
	
	
	
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
