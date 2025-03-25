package database.model;

import database.store.MusicStore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Comparator;

public final class LibraryModel {
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
		for (SongData sD : musicStore.getSongData()) {
			if (Stitle.equalsIgnoreCase(sD.getTitle())) {
				Song S = sD.getSongObject();
				if (!checkSongList(S)) {
					songLibrary.add(S);
					
					// find the album in the store
					Album storeAlbum = null;
					for (Album a : musicStore.getStore().values()) {
						if (a.getName().equalsIgnoreCase(S.getAlbum())) {
							storeAlbum = a;
							break;
						}
					}
					
					if (storeAlbum != null) {
						// check if we already have this album by name
						Album libraryAlbum = null;
						for (Album a : albumLibrary) {
							if (a.getName().equalsIgnoreCase(storeAlbum.getName())) {
								libraryAlbum = a;
								break;
							}
						}
						
						if (libraryAlbum == null) {
							// create new album if it doesn't exist
							libraryAlbum = new Album(storeAlbum);
							libraryAlbum.getSongs().clear();
							albumLibrary.add(libraryAlbum);
						}
						libraryAlbum.addSong(S);
					}
					
					System.out.println(Stitle + " has been Added!");
					updateAutomaticPlaylists();
					return;
				}
			}
		}
		System.out.println("Song not found in the Music Store.");
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
						updateAutomaticPlaylists();
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
		// Check if playlist with this name already exists
		for (PlayList p : userList) {
			if (p.getTitle().equalsIgnoreCase(name)) {
				if (printToConsole) {
					System.out.println("Playlist " + name + " already exists!");
				}
				return;
			}
		}
		userList.add(new PlayList(name));
		if (printToConsole) {
			System.out.println(String.format("Playlist %s has been made!", name));
		}
	}
	
	//Rate a Song
	/*
	 * @pre: sTitle != null
	 */
	public void rateSong(String sTitle, int r) {
		boolean found = false;
		for(SongData d : getDataInLibrary()) {
			if(d.getTitle().equalsIgnoreCase(sTitle)) {
				d.rate(r);
				// Add to favorites if rating is 5
				if(r == 5) {
					if (!favorites.contains(d)) {
						favorites.add(d);
					}
				} else {
					// Remove from favorites if rating drops below 5 (unless manually favorited)
					if (d.getRating().ordinal() < 5) {
						favorites.remove(d);
					}
				}
				found = true;
				updateAutomaticPlaylists();
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
	
	//returns a set of Artists from the library
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
	/*
	 * search for song by title
	 * @pre: a_t != null
	 * @return true if song was found, false otherwise
	 */
	public boolean searchSongbyString(String songName){
		ArrayList<Song> songsByString = new ArrayList<>();
		for(Song s1: songLibrary) {	// make sure to retrieve the song that does match the artist
			if(songName.equalsIgnoreCase(s1.getTitle())) { 
				songsByString.add(s1); 
			}
		}
		if(songsByString.isEmpty()) {
			System.out.println("Item is not in your Library...Maybe buy it from the Music Store!");
			return false;
		}
		else {
			for(Song p: songsByString) { // Prints the songs retrieved from the resulted iteration
				System.out.println(p.toString());
			}
			return true;
		}
	}

	/*
	 * method to output album info
	 * @pre: songName != null
	 */
	public void outputAlbumInfo(String songName) {
		String albumName;
		boolean inLibrary = false;

		for (Song s : songLibrary) {
			if (s.getTitle().equalsIgnoreCase(songName)) {
				albumName = s.getAlbum();

				for (Album a : albumLibrary) {
					if (a.getName().equalsIgnoreCase(albumName)) {
						inLibrary = true;
					}
				}

				if (inLibrary) {
					// call the other search method
					searchAlbumbyString(albumName);
				}
				else {
					System.out.println("\nAlbum: '" + albumName + "' not in your library but here's the info from the store!\n");
					musicStore.searchAlbumbyString(albumName);
				}

			}
		}
	}
	/* @pre: genre != null */
	public void searchSongByGenre(String genre) {
		for(Album a : albumLibrary) {
			if(a.getGenre().equalsIgnoreCase(genre)) {
				for(Song s : a.getSongs()) { 
					System.out.println(s + " by " + a.getArtist() +", " + s.getAlbum());
				}
			}
		}
		System.out.println("\nNo " + genre + " songs in the library");
	} 

	/*
	 * Search for an Album by Title
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
	 * @return true if any songs were found, false otherwise
	 */
	public boolean searchSongByTitleArtist(String author) {
		ArrayList<Song> songsByString = new ArrayList<>();
		for(Song s: songLibrary) {	// Makes sure to retrieve the song that does match the artist and title
			if(author.equalsIgnoreCase(s.getAuthor())) { 
				songsByString.add(s); 
			}
			
		}
		if(songsByString.isEmpty()) {
			System.out.println("Item is not in your library");
			return false;
		}
		for(Song p: songsByString) { // Prints the songs retrieved from the resulted iteration
			System.out.println(p);
		}
		return true;
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
		boolean hasFavoritePlaylist = false;
		for (PlayList p : userList) {
			String title = p.getTitle();
			if (title.equals("Recently Played")) {
				hasRecentPlaylist = true;
			} else if (title.equals("Most Played")) {
				hasMostPlayedPlaylist = true;
			} else if (title.equals("Favorite Songs")) {
				hasFavoritePlaylist = true;
			}
		}
		if (!hasRecentPlaylist) {
			userList.add(new PlayList("Recently Played"));
		}
		if (!hasMostPlayedPlaylist) {
			userList.add(new PlayList("Most Played"));
		}
		if (!hasFavoritePlaylist) {
			userList.add(new PlayList("Favorite Songs"));
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
	/* This method gets the count of songs by genre */
	private HashMap<String, Integer> countSongsByGenre() {
		HashMap<String, Integer> genreCounts = new HashMap<>();
		for (Album album : albumLibrary) {
			String genre = album.getGenre().toUpperCase();
			int currentCount = genreCounts.getOrDefault(genre, 0);
			currentCount += album.getSongs().size();
			genreCounts.put(genre, currentCount);
		}
		return genreCounts;
	}

	private void updateAutomaticPlaylists() {
		// update Recently Played playlist
		PlayList recentPlaylist = null;
		PlayList mostPlayedPlaylist = null;
		PlayList favoritePlaylist = null;
		int recentIndex = -1; // the -1 is used as "the song's index" hasn't been found yet
		int mostPlayedIndex = -1;
		int favoriteIndex = -1;

		// find the automatic playlists in userList
		for (int i = 0; i < userList.size(); i++) {
			PlayList p = userList.get(i);
			String title = p.getTitle();
			if (title.equals("Recently Played")) {
				recentPlaylist = p;
				recentIndex = i;
			} else if (title.equals("Most Played")) {
				mostPlayedPlaylist = p;
				mostPlayedIndex = i;
			} else if (title.equals("Favorite Songs")) {
				favoritePlaylist = p;
				favoriteIndex = i;
			}
		}

		// update Recently Played playlist
		if (recentPlaylist != null) {
			recentPlaylist = new PlayList("Recently Played");
			for (Song song : recentlyPlayed) {
				recentPlaylist.addSong(song);
			}
			userList.set(recentIndex, recentPlaylist);
		}

		// update Most Played playlist
		if (mostPlayedPlaylist != null) {
			mostPlayedPlaylist = new PlayList("Most Played");
			ArrayList<Song> mostPlayed = getMostPlayedSongs();
			for (Song song : mostPlayed) {
				mostPlayedPlaylist.addSong(song);
			}
			userList.set(mostPlayedIndex, mostPlayedPlaylist);
		}

		// update favorites playlist with songs that are marked as favorite or rated 5
		PlayList newFavoritePlaylist = new PlayList("Favorite Songs");
		PlayList newTopRatedPlaylist = new PlayList("Top Rated");
		for (SongData data : getDataInLibrary()) {
			// Add to favorites if marked as favorite or rated 5
			if (favorites.contains(data) || data.getRating().ordinal() == 5) {
				newFavoritePlaylist.addSong(data.getSongObject());
			}
			// Add to top rated if rated 4 or 5
			if (data.getRating().ordinal() >= 4) {
				newTopRatedPlaylist.addSong(data.getSongObject());
			}
		}
		if (favoriteIndex != -1) {
			userList.set(favoriteIndex, newFavoritePlaylist);
		} else {
			userList.add(newFavoritePlaylist);
		}

		// Find or create Top Rated playlist
		int topRatedIndex = -1;
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getTitle().equals("Top Rated")) {
				topRatedIndex = i;
				break;
			}
		}
		if (topRatedIndex != -1) {
			userList.set(topRatedIndex, newTopRatedPlaylist);
		} else {
			userList.add(newTopRatedPlaylist);
		}

		// Update genre-based playlists
		HashMap<String, Integer> genreCounts = countSongsByGenre();
		
		// Remove existing genre playlists
		userList.removeIf(p -> p.getTitle().startsWith("Genre: "));
		
		// Create new genre playlists for genres with 10+ songs
		for (String genre : genreCounts.keySet()) {
			if (genreCounts.get(genre) >= 10) {
				PlayList genrePlaylist = new PlayList("Genre: " + genre);
				for (Album album : albumLibrary) {
					if (album.getGenre().toUpperCase().equals(genre)) {
						for (Song song : album.getSongs()) {
							genrePlaylist.addSong(song);
						}
					}
				}
				userList.add(genrePlaylist);
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

	/*
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

		// get the sorted list of most played songs
		ArrayList<Song> mostPlayed = getMostPlayedSongs();
		
		// clear the playlist by removing all songs
		while (!mostPlayedPlaylist.getBody().isEmpty()) {
			mostPlayedPlaylist.remove(mostPlayedPlaylist.getBody().get(0));
		}
		
		// add songs in the correct order using addSong
		for (Song song : mostPlayed) {
			mostPlayedPlaylist.addSong(song);
		}
		
		return mostPlayedPlaylist;
	}

	// comparator methods for sorting songs
	public Comparator<Song> titleComparator() {
		return new Comparator<Song>() {
			public int compare(Song song1, Song song2) {
				return song1.getTitle().compareToIgnoreCase(song2.getTitle());
			}
		};
	}
	
	public Comparator<Song> artistComparator() {
		return new Comparator<Song>() {
			public int compare(Song song1, Song song2) {
				return song1.getAuthor().compareToIgnoreCase(song2.getAuthor());
			}
		};
	}
	
	public Comparator<Song> ratingComparator() {
		return new Comparator<Song>() {
			public int compare(Song song1, Song song2) {
				// get SongData objects to access ratings
				SongData data1 = null;
				SongData data2 = null;
				for (SongData d : musicStore.getSongData()) {
					if (d.getSongObject().equals(song1)) data1 = d;
					if (d.getSongObject().equals(song2)) data2 = d;
				}
				
				// compare ratings (higher rating first)
				if (data1 != null && data2 != null) {
					return data2.getRating().compareTo(data1.getRating());
				}
				return 0;
			}
		};
	}

	// methods to get sorted lists
	public ArrayList<Song> getSongsSortedByTitle() {
		ArrayList<Song> sortedSongs = new ArrayList<>(songLibrary);
		sortedSongs.sort(titleComparator());
		return sortedSongs;
	}
	
	public ArrayList<Song> getSongsSortedByArtist() {
		ArrayList<Song> sortedSongs = new ArrayList<>(songLibrary);
		sortedSongs.sort(artistComparator());
		return sortedSongs;
	}
	
	public ArrayList<Song> getSongsSortedByRating() {
		ArrayList<Song> sortedSongs = new ArrayList<>(songLibrary);
		sortedSongs.sort(ratingComparator());
		return sortedSongs;
	}

	// remove a song from the library
	public void removeSongFromLib(String songTitle) {
		// first find the song in the library
		Song songToRemove = null;
		for (Song s : songLibrary) {
			if (s.getTitle().equalsIgnoreCase(songTitle)) {
				songToRemove = s;
				break;
			}
		}
		
		if (songToRemove == null) {
			System.out.println(songTitle + " is not in the library");
			return;
		}
		
		// remove from main song library
		songLibrary.remove(songToRemove);
		
		// Remove from all playlists
		for (PlayList playlist : userList) {
			playlist.remove(songToRemove);
		}
		
		// remove from favorites
		for (int i = favorites.size() - 1; i >= 0; i--) {
			if (favorites.get(i).getSongObject().equals(songToRemove)) {
				favorites.remove(i);
			}
		}
		
		// remove from recently played
		recentlyPlayed.remove(songToRemove);
		
		// Remove from play count
		playCount.remove(songToRemove);
		
		// remove from albums
		for (int i = albumLibrary.size() - 1; i >= 0; i--) {
			Album album = albumLibrary.get(i);
			album.getSongs().remove(songToRemove);
			if (album.getSongs().isEmpty()) { // if the last song is removed, remove the album
				albumLibrary.remove(i);
			}
		}
		
		System.out.println(songTitle + " has been removed from the library");
	}

	public void removeAlbumFromLib(String albumTitle) {
		// find the album to remove
		Album albumToRemove = null;
		for (Album a : albumLibrary) {
			if (a.getName().equalsIgnoreCase(albumTitle)) {
				albumToRemove = a;
				break;
			}
		}
		
		if (albumToRemove == null) {
			System.out.println(albumTitle + " is not in the library");
			return;
		}
		
		// get all songs from the album
		ArrayList<Song> albumSongs = new ArrayList<>(albumToRemove.getSongs());
		
		// remove album from albumLibrary
		albumLibrary.remove(albumToRemove);
		
		for (Song song : albumSongs) {
			// check if song exists in other albums
			boolean songInOtherAlbums = false;
			for (Album album : albumLibrary) {
				if (album.getSongs().contains(song)) {
					songInOtherAlbums = true;
					break;
				}
			}
			
			// if song is not in other albums, remove it from all collections
			if (!songInOtherAlbums) {
				// remove from main song library
				songLibrary.remove(song);
				
				// remove from all playlists
				for (PlayList playlist : userList) {
					playlist.remove(song);
				}
				
				// remove from favorites
				for (int i = favorites.size() - 1; i >= 0; i--) {
					if (favorites.get(i).getSongObject().equals(song)) {
						favorites.remove(i);
					}
				}
				
				// remove from recently played
				recentlyPlayed.remove(song);
				
				// remove from play count
				playCount.remove(song);
			}
		}
		
		System.out.println(albumTitle + " has been removed from the library");
	}

	public ArrayList<PlayList> getUserList() {
		return new ArrayList<>(userList);
	}

	public void shuffleSongs() {
		Collections.shuffle(songLibrary);
	}

	public void shufflePlayList(String pName) {
		// doesn't make sense to shuffle these playlists
		if (pName.equalsIgnoreCase("Recently Played") ||
			pName.equalsIgnoreCase("Most Played")) {
			return;
		}
		for (int i = 0; i < userList.size(); i++) {
			PlayList p = userList.get(i);
			// recall the getBody() method is a defensive copy, thus we need this approach
			if (p.getTitle().equalsIgnoreCase(pName)) {
				ArrayList<Song> songs = new ArrayList<>(p.getBody());
				Collections.shuffle(songs);
				PlayList shuffledPlaylist = new PlayList(pName);
				for (Song song : songs) {
					shuffledPlaylist.addSong(song);
				}
				userList.set(i, shuffledPlaylist);
				break;
			}
		}
	}

	// methods to get the new automatic playlists
	public PlayList getTopRated() {
		for (PlayList p : userList) {
			if (p.getTitle().equals("Top Rated")) {
				return p;
			}
		}
		return new PlayList("Top Rated");
	}

	public PlayList getGenrePlaylist(String genre) {
		for (PlayList p : userList) {
			if (p.getTitle().equals("Genre: " + genre)) {
				return p;
			}
		}
		return null;
	}

}