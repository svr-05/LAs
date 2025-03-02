package database.model;

import java.util.ArrayList;
import java.util.HashMap;

import database.store.MusicStore;

public class LibraryModel{
	public static void main(String[] args) {
		LibraryModel lB = new LibraryModel();
		lB.makePlayList("S");
		lB.addSong("DayDreamer");
		
		lB.addSongToPlayList("S", "DayDreamer");
		System.out.println(lB.getUserList().size());
		lB.removeSongFromPlayList("S", "DayDreamer");
		System.out.println(lB.getUserList().size());
	}

	private ArrayList<PlayList> userList; 
	private ArrayList<Song> songLibrary; // Holds in a Song
	private ArrayList<Album> albumLibrary;
	private HashMap<SongData, Boolean> favorites; // Holds the songs that are favorited
	private MusicStore musicStore;
	
	//Constructors
	public LibraryModel() {
		this.userList = new ArrayList<>();
		this.songLibrary = new ArrayList<>();
		this.albumLibrary = new ArrayList<>();
		this.favorites = new HashMap<>();
		this.musicStore = new MusicStore();
		musicStore.parseAlbums(); // That way we load all of the music store here!
	}

	//Getters
	public ArrayList<PlayList> getUserList() { 
		return new ArrayList<>(this.userList);
	}
	public ArrayList<Song> getSongLibrary() {
		return new ArrayList<>(this.songLibrary);
	}
	public ArrayList<Album> getAlbumLibrary(){
		return new ArrayList<>(this.albumLibrary);
	}
	
	public void addSong(String Stitle) {
	    boolean songFound = false;
	    for (SongData sD : musicStore.getSongData()) { // retrieve all the songs in the library
	        if (Stitle.equalsIgnoreCase(sD.getTitle())) {  // verify there is a SongData object that matches the title of the song
	            Song S = sD.getSongObject(); // retuerns a copy of the SongObject
	            if (musicStore.checkStoreSong(S.getTitle())) {
	                if (!checkSongList(S)) { // add the song if it's not yet in the library
	                    songLibrary.add(S);
	                    System.out.println("Song has been Added!");
	                    songFound = true;
	                }
	            }
	        }
	    }  
	    if (songFound == false) {
	        System.out.println("Song not found in the Music Store.");
	    }
	}

	// basically, mirror the addSong() method but for Album
	public void addAlbum(String aTitle) { // Adds the album and its songs. Adds each song in album if the song is not already in the library
		for(Album A : musicStore.getStore().values()) { // check in the array of Albums from the music store (hash map)
			if(aTitle.equalsIgnoreCase(A.getName())) {
				if(musicStore.checkStoreAlbum(A.getName())) {
					if(!checkAlbumList(A)) {
						albumLibrary.add(A); 
						for(Song s: A.getSongs()) {
							if(!checkSongList(s)) {
								addSong(s.getTitle());
							}
						}
						System.out.println("Albums have been Added :)");
						break;
					}
					else System.out.println("Album already in the library");
				}
			}
		}
	}
	/*
	 * @pre: S != null
	 * 
	 */
	public void addFavorite(String songTitle) {
	    for (SongData d : getDataInLibrary()) {
	        if (d.getTitle().equalsIgnoreCase(songTitle)) {
	            d.changeFavorite();
	            favorites.put(d, d.favoriteStatus());
	            System.out.println("Done!");
	            return;
	        }
	    }
	    System.out.println("Song not found in the library.");
	}


	/*
	 * @pre: name != null
	 */
	public void makePlayList(String name){ 
		userList.add(new PlayList(name));
		System.out.println(String.format("Playlist %s has been made!", name));
	}
	
	//Rate a Song
	/*
	 * @pre: sTitle != null
	 */
	public void rateSong(String sTitle, int r) {
		boolean found = false; // mirror the strategy of addSong
		for(SongData d : getDataInLibrary()) { // check in all of the SongData objects in the array
			if(d.getTitle().equalsIgnoreCase(sTitle)) {
				d.rate(r);
				if(r == 5) {
					addFavorite(d.getTitle());
				}
				found = true;
				break;
			}
		}
		if (found == false) System.out.println("Song not in the library");
	}
	
	//Modify PlayList
	//Add Song
	/*
	 * @pre: pName != null && songName != null
	 */
	public void addSongToPlayList(String pName, String songName) {
		// compare
		for(PlayList p : userList) { // mirror the same logic modeled above
			if(p.getTitle().equalsIgnoreCase(pName)) {
				for(Song s : songLibrary) {
					if(s.getTitle().equalsIgnoreCase((songName))) {
						p.addSong(s);
						System.out.println("Song has been added :D");
						return;
					}
				}
			}
			System.out.println("Song not in library");
			return;
		}
		System.out.println("Create playlist first");
	}
	
	//Remove Song
	/*
	 * @pre: pName != null && songName != null
	 */
	public void removeSongFromPlayList(String pName, String songName) {
		for(PlayList p: userList) {
			if(p.getTitle().equalsIgnoreCase(pName)) {
				for(Song s : songLibrary) {
					if(s.getTitle().equalsIgnoreCase(songName)) {
						p.remove(s);
						System.out.println("Song has been removed D:");
						return;
					}
				}
				System.out.println(String.format("Song isn't in %s", pName));
				return;
			}
		}
		System.out.println("Create playlist first");
	}
	
	//Methods for GetLists/Searching
	//Returns a list of Song names from the library
	public ArrayList<String> getSongTitles(){
		ArrayList<String> result = new ArrayList<>();
		for(Song s : songLibrary) {
			result.add(s.getTitle());
		}
		return result;
	}
	
	//Returns a list of Artists from the library
	public ArrayList<String> getArtists(){
		ArrayList<String> result = new ArrayList<>();
		for(Song s : songLibrary) {
			result.add(s.getAuthor());
		}
		return result;
	}
	
	//Returns a list of Albums from the Library
	public ArrayList<String> getAlbumList(){
		ArrayList<String> result = new ArrayList<>();
		for(Album a : albumLibrary) {
			result.add(a.getName());
		}
		return result;
	}
	
	//Returns a list of strings with the playlist titles from the Library list
	public ArrayList<String> getPlayList(){
		ArrayList<String> result = new ArrayList<>();
		for(PlayList p : userList) {
			result.add(p.getTitle());
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
	//Search for Song by Tile
	/*
	 * @pre: a_t != null
	 */
	public void searchSongbyString(String a_t){
		ArrayList<Song> songsByString = new ArrayList<>();
		for(Song s1: songLibrary) {	// Makes sure to retrieve the song that does match the artist
			if(a_t.equalsIgnoreCase(s1.getTitle())) { 
				songsByString.add(s1); 
			}
		}
		if(songsByString.isEmpty()) {
			System.out.println("Item is not in your Library...Maybe buy it from the Music Store!");
		}
		else {
			for(Song p: songsByString) { // Prints the songs retrieved from the resulted iteration
				System.out.println(p.toString());
			}
		}
	}
	
	//Search for an Album by Title or Artist
	/*
	 * @pre: a_t != null
	 */
	public void searchAlbumbyString(String a_t){
		ArrayList<Album> albumsByString = new ArrayList<>();
		for(Album a1: albumLibrary) {	// Makes sure to retrieve the album that does match the artist
			if(a_t.equalsIgnoreCase(a1.getName())) { 
				albumsByString.add(a1); 
			}
		}
		if(albumsByString.isEmpty()) {
			System.out.println("Item is not in your Library...Maybe buy it from the Music Store!");
		}
		else {
			for(Album p: albumsByString) { // Prints the albums retrieved from the resulted iteration
				System.out.println(p.toString());
			}
		}

	}
	/*
	 * @pre: title != null && artist != null
	 */
	public void searchAlbumbyTitleAuthor(String title, String artist){
		ArrayList<Album> albumsByString = new ArrayList<>();
		for(Album a1: albumLibrary) {	// Makes sure to retrieve the album that does match the artist and title
			if(title.equalsIgnoreCase(a1.getName()) && artist.equalsIgnoreCase(a1.getArtist())) { 
				albumsByString.add(a1);
			}
		}
		if(albumsByString.isEmpty()) {
			System.out.println("Item is not in your Library...Maybe buy it from the Music Store!");
		}
		for(Album p: albumsByString) { // Prints the albums retrieved from the resulted iteration
			System.out.println(p.toString());
		}
	}
	/*
	 * @pre: title != null && author != null
	 */
	public void searchSongByTitleArtist(String title, String author) {
		ArrayList<Song> songsByString = new ArrayList<>();
		for(Song s: songLibrary) {	// Makes sure to retrieve the song that does match the artist and title
			if(title.equalsIgnoreCase(s.getTitle()) && author.equalsIgnoreCase(s.getAuthor())) { 
				songsByString.add(s); 
			}
			
		}
		if(songsByString.isEmpty()) {
			System.out.println("Item is not in your library");
		}
		for(Song p: songsByString) { // Prints the songs retrieved from the resulted iteration
			System.out.println(p);
		}
	}
	
	//Search for a PlayList
	/*
	 * @pre: name != null
	 */
	// mirror the same logic from above but only for playlist name
	public void searchPlayListName(String name) {
		ArrayList<PlayList> pList = new ArrayList<>();
		for(PlayList pn : userList) {
			if(pn.getTitle().equalsIgnoreCase(name)) {
				pList.add(pn);
			}
		}
		if(pList.isEmpty()) {
			System.out.println("Looks like you don't have this as a playList...MAKE ONE!!!");
		}
		else {
			for(PlayList p: pList) { // Prints the albums retrieved from the resulted iteration
				System.out.println(p.toString());
			}
		}
	}
	
	//Search/Look at Favorites
	/*
	 * @pre: a_t != null
	 */
	public void searchFavorites(String a_t) {
		ArrayList<SongData> data = new ArrayList<>();

		for(SongData dr: favorites.keySet()) {	// Makes sure to retrieve the favorited song with the same artist or name
			if(a_t.equalsIgnoreCase(dr.getTitle())) {
				data.add(dr);
			}
		}
		if(data.isEmpty()) {
			System.out.println("You don't have any favorites...LISTEN TO MORE MUSIC!!");
		}
		else {
			for(SongData p: favorites.keySet()) {
				System.out.print(p.getSongObject().toString() + ", Rating: " + p.getRating());
			}
		}

	}
	
	
	// Helper Methods that can retrieve
	// Retrieves the list of Songs from Library
	// Song is immutable! No need for deeper copy
	public ArrayList<Song> getSongs(){
		return new ArrayList<>(songLibrary);
	}
	
	// Retrieves the list of Albums from library
	// Albums are mutable, thus, a deeper cop is needed
	public ArrayList<Album> getAlbums(){
		ArrayList<Album> result = new ArrayList<>();
		for(Album album : albumLibrary) {
			Album albumCopy = new Album(album);
			for (Song s : album.getSongs()) {
				albumCopy.addSong(s);
			}
			result.add(albumCopy);
		}
		return result;
	}
	
	//Retrieve the List of SONG DATA
	private ArrayList<SongData> getDataInLibrary(){
		ArrayList<SongData> result = new ArrayList<>();
		for(SongData d : musicStore.getSongData()) {
			if(songLibrary.contains(d.getSongObject())) {
				result.add(d);
			}
		}
		return result;
	}
	
	//Checks if a song is inside the Library
	/*
	 * @pre: compare != null
	 */
	private boolean checkSongList(Song compare) {
		for (Song s : getSongs()) {
			if(s.equals(compare)) {
				return true;
			}
		}
		return false;
	}
	
	//checks if a album is inside the library
	/*
	 * @pre: compare != null
	 */
	private boolean checkAlbumList(Album compare) {
		for(Album a : getAlbums()) {
			if(a.equals(compare)) {
				return true;
			}
		}
		return false;
	}
}