package database.store;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SongData {
	// instance variables
	public final ArrayList<String> RATINGS;
	
	private String title,
	               author,
	               album,
	               rating;
	private boolean isFavorite;
	
	// constructor
	public SongData(String title, String author, String album) {
		this.title = title;
		this.author = author;
		this.album = album;
		RATINGS = new ArrayList<String>(Arrays.asList
				(null, "★", "★ ★", "★ ★ ★", "★ ★ ★ ★", "★ ★ ★ ★ ★"));
		Collections.unmodifiableList(RATINGS); // makes RATING immutable, throws UnsupportedOperationException
		this.rating = RATINGS.get(0);
		this.isFavorite = false;
	}
	
	// copy constructor
	public SongData(SongData song) {
		this(song.title, song.author, song.album);
	}
	
	// getters
	public String getTitle() { return this.title; }
	
	public String getAuthor() { return this.author; }
	
	public String getAlbum() { return this.album; }
	
	public String getRating() {
		return this.rating;
	}

	
  public Song getSongObject() {
		return new Song(this.title, this.author, this.album);
	}
  
	public boolean favoriteStatus() { return this.isFavorite; }
	
	// setters
	public void setFavorite() { this.isFavorite = true; }
	
	public void rate(int r) {
    if(r == 5) setFavorite();
		this.rating = RATINGS.get(r);
	}
	
	@Override
	public String toString() {
		return String.format("Song title: %s, Author: %s, Album: %s",
				             this.title, this.author, this.album);
	}
	

}
