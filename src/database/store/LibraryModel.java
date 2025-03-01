package database.store;

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
	
	//Add Song/Album/PlayList/Favorite to Library
	public void addSong(Song S) { // Adds the song to the library. Unless the song is already in the library
		if(checkStoreSong(S.getTitle())) {
			if(!checkSongList(S)) {
				library.add(new Song(S));
			}
		}
	}
	public void addAlbum(Album A) { // Adds the album and its songs. Adds each song in album if the song is not already in the library
		if(checkStoreAlbum(A.getName())) {
			if(checkAlbumList(A)) {
				library.add(A); 
				for(Song s: A.getSongs()) {
					if(!checkSongList(s)) {
						addSong(s);
					}
				}
			}
		}
	}
	public void addFavorite(SongData S) {
		if(S.favoriteStatus()) {
			favorites.put(S, S.favoriteStatus());
		} else {
			S.changeFavorite();
			favorites.put(S, S.favoriteStatus());
		}
	}
	public void makePlayList(String name){ userList.add(new PlayList(name));}
	
	//Rate a Song
	public void rateSong(String stitle, int r) {
		for(SongData d: getDataInLibrary()) {
			if(d.getTitle().equals(stitle)) {
				if(r == 5) {
					d.rate(r);
					addFavorite(d);
				} else { d.rate(r); }
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
	
	//Methods for GetLists/Searching
	//Returns a list of Song names from the library
	public ArrayList<String> getSongTitles(){
		ArrayList<Song> songs = getSongs();
		ArrayList<String> result = new ArrayList<>();
		for(Song s : songs) {
			result.add(s.getTitle());
		}
		return result;
	}
	
	//Returns a list of Artists from the library
	public ArrayList<String> getArtists(){
		ArrayList<Song> songs = getSongs();
		ArrayList<String> result = new ArrayList<>();
		for(Song s : songs) {
			result.add(s.getAuthor());
		}
		return result;
	}
	
	//Returns a list of Albums from the Library
	public ArrayList<String> getAlbumList(){
		ArrayList<Album> album = getAlbums();
		ArrayList<String> result = new ArrayList<>();
		for(Album a : album) {
			result.add(a.toString());
		}
		return result;
	}
	
	//Returns a list of PlayLists from the Library list
	public ArrayList<String> getPlayList(){
		ArrayList<String> result = new ArrayList<>();
		for(PlayList p : userList) {
			result.add(p.toString());
		}
		return result;
	}
	
	//Returns a List of all songs on Favorites
	public ArrayList<String> getFavorites(){
		ArrayList<String> result = new ArrayList<>();
		for(SongData d: favorites.keySet()) {
			result.add(d.toString());
		}
		return result;
	}
	
	//Search Methods
	//Search for Song by Tile or Artist
	public void searchSongbyString(String a_t){
		ArrayList<Song> songsByString = new ArrayList<>();
		for(Song s1: getSongs()) {	// Makes sure to retrieve the song that does match the artist and title
			if(a_t.equals(s1.getTitle()) && a_t.equals(s1.getAuthor())) { songsByString.add(s1); }
		}
		for(Song sr: getSongs()) {	// Makes sure to retrieve the songs with the same artist or name
			if (a_t.equals(sr.getTitle()) || a_t.equals(sr.getAuthor())){ songsByString.add(sr);}
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
		for(Album a1: getAlbums()) {	// Makes sure to retrieve the album that does match the artist and title
			if(a_t.equals(a1.getName()) && a_t.equals(a1.getArtist())) { albumsByString.add(a1); }
		}
		for(Album ar: getAlbums()) {	// Makes sure to retrieve the albums with the same artist or name
			if (a_t.equals(ar.getName()) || a_t.equals(ar.getArtist())){ albumsByString.add(ar);}
		}
		if(albumsByString.size() == 0) {
			System.out.println("Item is not in your Library...Maybe buy it from the Music Store!");
		}
		for(Album p: albumsByString) { // Prints the albums retrieved from the resulted iteration
			System.out.println(p.toString());
		}
	}
	
	//Search for a PlayList
	public void searchPlayListName(String name) {
		ArrayList<PlayList> pList = new ArrayList<>();
		for(PlayList pn : userList) {
			if(pn.getTitle().equals(name)) {
				pList.add(pn);
			}
		}
		if(pList.size() == 0) {
			System.out.println("Looks like you don't have a playList...MAKE ONE!!!");
		}
		for(PlayList p: pList) { // Prints the albums retrieved from the resulted iteration
			System.out.println(p.toString());
		}
	}
	
	//Search/Look at Favorites
	public void searchFavorites(String a_t) {
		ArrayList<SongData> data = new ArrayList<>();
		for(SongData d1: favorites.keySet()) {	// Makes sure to retrieve the favorited song that matches the search
			if(a_t.equals(d1.getTitle()) && a_t.equals(d1.getAuthor())) {data.add(d1);}
		}
		for(SongData dr: favorites.keySet()) {	// Makes sure to retrieve the favorited song with the same artist or name
			if(a_t.equals(dr.getTitle()) || a_t.equals(dr.getAuthor())) {data.add(dr);}
		}
		if(data.size() == 0) {
			System.out.println("You don't have any favorites...LISTEN TO MORE MUSIC!!");
		}
		for(SongData p: favorites.keySet()) {
			System.out.print(p.getSongObject().toString() + ", Rating: " + p.getRating());
		}
	}
	
	//Look at Ratings
	public void lookAtRatings() { /////////////////////////// Debugging purposes/Style
		for(SongData dp : getDataInLibrary()) {
			System.out.println(dp.getSongObject().toString() + ", Rating: " + dp.getRating());
		}
	}
	
	// Helper Methods that can retrieve
	// Retrieves the list of Songs from Library
	private ArrayList<Song> getSongs(){
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
	
	//Retrieve the List of SONG DATA
	private ArrayList<SongData> getDataInLibrary(){
		ArrayList<SongData> result = new ArrayList<>();
		for(SongData d : getSongData()) {
			for(Song s: getSongs()) {
				if(d.getTitle().equals(s.getTitle())) {
					result.add(d);
				}
			}
		}
		return result;
	}
	
	//Checks if a song is inside the Library
	private boolean checkSongList(Song compare) {
		for ( Song s : getSongs()) {
			if(s.equals(compare)) {
				return true;
			}
		}
		return false;
	}
	
	//checks if a album is inside the library
	private boolean checkAlbumList(Album compare) {
		for(Album a : getAlbums()) {
			if(a.equals(compare)) {
				return true;
			}
		}
		return false;
	}
}
