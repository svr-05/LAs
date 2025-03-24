package database.model;

import database.store.MusicStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class LibraryModel {
	private ArrayList<PlayList> userList; 
	private ArrayList<Song> songLibrary; // holds in a Song
	private ArrayList<Album> albumLibrary;
	private ArrayList<SongData> favorites; // holds the songs that are favorited
	
	private ArrayList<Song> recentlyPlayed; // stores last 10 played songs
	private HashMap<Song, Integer> playCount; // tracks play count for each song
	private MusicStore musicStore;
	
	private boolean printToConsole; // a flag to determine if we want to print to console
	
	//Constructors
	public LibraryModel() {
		this.userList = new ArrayList<>();
		this.songLibrary = new ArrayList<>();
		this.albumLibrary = new ArrayList<>();
		this.favorites = new ArrayList<>();
		this.recentlyPlayed = new ArrayList<>();
		this.playCount = new HashMap<>();
		this.musicStore = new MusicStore();
		this.printToConsole = true;
		musicStore.parseAlbums(); // That way we load all of the music store here!
	}
	
	// setter
	public void setPrintToConsole(boolean b) { this.printToConsole = b; }

	public void addSong(String Stitle) {
	    boolean songFound = false;
	    for (SongData sD : musicStore.getSongData()) { // check all the songs in the library
	        if (Stitle.equalsIgnoreCase(sD.getTitle())) {  // verify there is a SongData object that matches the title of the song
	            Song S = sD.getSongObject(); // returns a copy of the SongObject
	            if (musicStore.checkStoreSong(S.getTitle())) {
	                if (!checkSongList(S)) { // add the song if it's not yet in the library
	                    songLibrary.add(S);
	                    System.out.println(Stitle + " has been Added!");
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
						System.out.println("\n" + aTitle + " has been Added :)");
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
	            d.setFavorite();
	            favorites.add(d);
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
				if(d.favoriteStatus()) {
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
		if (userList.isEmpty() && printToConsole) {
			System.out.println("Create playlist first");
			return;
		}
		
		boolean playlistFound = false;
		for(PlayList p : userList) {
			if(p.getTitle().equalsIgnoreCase(pName)) {
				playlistFound = true;
				for(Song s : songLibrary) {
					if(s.getTitle().equalsIgnoreCase((songName))) {
						p.addSong(s);
						System.out.println(songName + " has been added :D");
						return;
					}
				}
				System.out.println("Song not in library");
				return;
			}
		}
		if (!playlistFound && printToConsole) {
			System.out.println("Playlist not found");
		}
	}
	
	//Remove Song
	/*
	 * @pre: pName != null && songName != null
	 */
	public void removeSongFromPlayList(String pName, String songName) {
		if (printToConsole && userList.isEmpty()) {
			System.out.println("Create playlist first");
			return;
		}
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
	}
	
	//Methods for GetLists/Searching
	//Returns a list of Song names from the library
	public ArrayList<String> getSongTitles(){
		if (songLibrary.isEmpty() && printToConsole) {
			System.out.println("You haven't added any songs yet -_-"); 
			return new ArrayList<>();
		}
		if (printToConsole) System.out.println("🎵");
		ArrayList<String> result = new ArrayList<>();
		for(Song s : songLibrary) {
			result.add(s.getTitle());
		}
		return result;
	}
	
	//Returns a set of Artists from the library
	public HashSet<String> getArtists(){ // as opposed to the other "serchers" for the
		if (songLibrary.isEmpty() && printToConsole) {     // library, this one uses a set to avoid repetition!
			System.out.println("Add a song to add an artist!");
			return new HashSet<>();
		}
		
		if (printToConsole)System.out.println("🎤");

		HashSet<String> result = new HashSet<>(); 
		for(Song s : songLibrary) {                  
			result.add(s.getAuthor());
		}
		return result;
	}
	
	//Returns a list of Albums from the Library
	public ArrayList<String> getAlbumList() {
		if (albumLibrary.isEmpty() && printToConsole) {
			System.out.println("You haven't added any albums");
			return new ArrayList<>();
		}

		if (printToConsole) System.out.println("💿");

		ArrayList<String> result = new ArrayList<>();
		for(Album a : albumLibrary) {
			result.add(a.getName());
		}
		return result;
	}
	
	//Returns a list of strings with the playlist titles from the Library list
	public ArrayList<String> getPlayList(){
		if (userList.isEmpty() && printToConsole) {
			System.out.println("Create a playlist first");
			return new ArrayList<>();
		}
		if (printToConsole)System.out.println("🎧");

		ArrayList<String> result = new ArrayList<>();
		for(PlayList p : userList) {
			result.add(p.getTitle());
		}
		return result;
	}
	
	//Returns a List of all songs on Favorites
	public HashSet<String> getFavorites(){
		if (favorites.isEmpty() && printToConsole) {
			System.out.println("You don't have any favorite songs yet 🙃");
			return new HashSet<>();
		}
		if (printToConsole)System.out.println("🖤");

		HashSet<String> result = new HashSet<>();
		for(SongData d: favorites) {
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
	public void searchAlbumbyTitleAuthor(String artist){
		ArrayList<Album> albumsByString = new ArrayList<>();
		for(Album a1: albumLibrary) {	// Makes sure to retrieve the album that does match the artist and title
			if(artist.equalsIgnoreCase(a1.getArtist())) { 
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
	public void searchSongByTitleArtist(String author) {
		ArrayList<Song> songsByString = new ArrayList<>();
		for(Song s: songLibrary) {	// Makes sure to retrieve the song that does match the artist and title
			if(author.equalsIgnoreCase(s.getAuthor())) { 
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

		for(SongData dr: favorites) {	// Makes sure to retrieve the favorited song with the same artist or name
			if(a_t.equalsIgnoreCase(dr.getTitle())) {
				data.add(dr);
			}
		}
		if(data.isEmpty()) {
			System.out.println("You don't have any favorites...LISTEN TO MORE MUSIC!!");
		}
		else {
			for(SongData p: favorites) {
				System.out.print(p.getSongObject().toString() + ", Rating: " + p.getRating());
			}
		}

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
	
	// retrieve the List of SONG DATA
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
		for (Song s : songLibrary) {
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

	/*
	 * simulate playing a song by updating recently played and play count lists
	 */
	public boolean playSong(String songTitle) {
		boolean songExists = false;
		Song song = null; // song holds the song object (if it exists in the library)
		for (Song s : songLibrary) {
			if (s.getTitle().equalsIgnoreCase(songTitle)) {
				songExists = true;
				song = s;
				break;
			}
		}

		if (!songExists) {
			if (printToConsole) {
				System.out.println("Song '" + songTitle + "' not found in library.");
			}
			return false;
		}

		// create automatic playlists if they don't exist
		boolean hasRecentPlaylist = false;
		boolean hasMostPlayedPlaylist = false;
		for (PlayList p : userList) {
			if (p.getTitle().equals("Recently Played")) {
				hasRecentPlaylist = true;
			} else if (p.getTitle().equals("Most Played")) {
				hasMostPlayedPlaylist = true;
			}
		}
		if (!hasRecentPlaylist) {
			userList.add(new PlayList("Recently Played"));
		}
		if (!hasMostPlayedPlaylist) {
			userList.add(new PlayList("Most Played"));
		}

		// update play count
		int currentCount = 0;
		if (playCount.containsKey(song)) {
			currentCount = playCount.get(song);
		}
		playCount.put(song, currentCount + 1);

		// update recently played list
		if (recentlyPlayed.contains(song)) {
			recentlyPlayed.remove(song); // remove if already in list
		}
		recentlyPlayed.add(0, song); // add to front
		
		// keep only last 10 songs
		while (recentlyPlayed.size() > 10) {
			recentlyPlayed.remove(recentlyPlayed.size() - 1);
		}

		updateAutomaticPlaylists();

		if (printToConsole) {
			System.out.println("Playing: " + songTitle);
		}
		return true;
	}

	/*
	 * updates both the Recently Played and Most Played automatic playlists
	 */
	private void updateAutomaticPlaylists() {
		// update Recently Played playlist
		PlayList recentPlaylist = null;
		PlayList mostPlayedPlaylist = null;

		// find the automatic playlists in userList
		for (PlayList p : userList) {
			if (p.getTitle().equals("Recently Played")) {
				recentPlaylist = p;
			} else if (p.getTitle().equals("Most Played")) {
				mostPlayedPlaylist = p;
			}
		}

		// clear and rebuild "recently played" playlist to account for any immediate changes
		if (recentPlaylist != null) {
			recentPlaylist = new PlayList("Recently Played");
			// add songs in order (most recent first)
			for (Song song : recentlyPlayed) {
				recentPlaylist.addSong(song);
			}
		}

		// same for "most played" playlist
		if (mostPlayedPlaylist != null) {
			mostPlayedPlaylist = new PlayList("Most Played");
			ArrayList<Song> mostPlayed = getMostPlayedSongs();
			// add songs in order (highest play count first)
			for (Song song : mostPlayed) {
				mostPlayedPlaylist.addSong(song);
			}
		}
	}

	/**
	 * get playList containing the 10 most recently played songs
	 */
	public PlayList getRecentlyPlayed() {
		for (PlayList p : userList) {
			if (p.getTitle().equals("Recently Played")) {
				// Clear and rebuild the playlist in reverse order
				p = new PlayList("Recently Played");
				for (int i = recentlyPlayed.size() - 1; i >= 0; i--) {
					p.addSong(recentlyPlayed.get(i));
				}
				return p;
			}
		}
		// This shouldn't happen as the playlist is created in constructor
		return new PlayList("Recently Played");
	}

	/**
	 * list of 10 most frequently played songs, highest play count first
	 */
	private ArrayList<Song> getMostPlayedSongs() {
		ArrayList<Song> result = new ArrayList<>();    
		// if no songs have been played yet, return empty list
		if (playCount.isEmpty()) {
			return result;
		}
		
		// get all songs that have been played
		ArrayList<Song> songs = new ArrayList<>(playCount.keySet());
		
		// sort songs by play count (descending order)
		for (int i = 0; i < songs.size() - 1; i++) {
			for (int j = 0; j < songs.size() - 1 - i; j++) {
				// compare play counts in descending order (highest first)
				int count1 = playCount.get(songs.get(j));
				int count2 = playCount.get(songs.get(j + 1));
				if (count1 < count2) {
					// swap songs if current song has lower play count than next song
					Song temp = songs.get(j);
					songs.set(j, songs.get(j + 1));
					songs.set(j + 1, temp);
				}
			}
		}
		
		// take only the top 10 songs at most
		int numSongs = Math.min(10, songs.size());
		for (int i = 0; i < numSongs; i++) {
			result.add(songs.get(i));
		}
		return result;
	}

	/*
	 * playList containing the 10 most played songs
	 */
	public PlayList getMostPlayed() {
		// Find or create the Most Played playlist
		PlayList mostPlayedPlaylist = null;
		for (PlayList p : userList) {
			if (p.getTitle().equals("Most Played")) {
				mostPlayedPlaylist = p;
				break;
			}
		}
		if (mostPlayedPlaylist == null) {
			mostPlayedPlaylist = new PlayList("Most Played");
			userList.add(mostPlayedPlaylist);
		}

		// Get the sorted list of most played songs
		ArrayList<Song> mostPlayed = getMostPlayedSongs();
		
		// Clear the playlist by removing all songs
		while (!mostPlayedPlaylist.getBody().isEmpty()) {
			mostPlayedPlaylist.remove(mostPlayedPlaylist.getBody().get(0));
		}
		
		// Add songs in the correct order using addSong
		for (Song song : mostPlayed) {
			mostPlayedPlaylist.addSong(song);
		}
		
		return mostPlayedPlaylist;
	}
}