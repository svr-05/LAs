package database.store;

public class Song {
	// instance variables
	private String title,
	               author;
	private String album;
	private int rating;
	private boolean isFavorite;
	
	// constructor
	public Song(String title, String author, String album) {
		this.title = title;
		this.author = author;
		this.album = album;
		this.rating = -1; // We need to talk about what the type of rating should be either string or int
		this.isFavorite = false;
	}
	
	// copy constructor
	public Song(Song song) {
		this(song.title, song.author, song.album);
	}
	
	// getters
	public String getTitle() { return this.title; }
	
	public String getAuthor() { return this.author; }
	
	public String getAlbum() { return this.album; }
	
	public int getRating() {
		// -1 represents no rating with r
		if (this.rating != -1) return this.rating; 
		else return -1;
	}
	
	public boolean favoriteStatus() { return this.isFavorite; }
	
	// setters
	public void setFavorite() { this.isFavorite = true; }
	
	public void rate(int r) {
		this.rating = r;
		if (r == 5) this.isFavorite = true;
	}
	
	@Override
	public String toString() {
		return String.format("Song title: %s, Author: %s, Album: %s",
				             this.title, this.author, this.album);
	}
	

}
