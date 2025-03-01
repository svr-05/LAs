package database.store;

public class Song {
	private String title,
	               author,
	               album;
	
	// constructor
	public Song(String title, String author, String album) {
		this.title = title;
		this.author = author;
		this.album = album;
	}
	
	// copy constructor
	public Song(Song song) { // Need to change this to take in SongData
		this(song.title, song.author, song.album);
	}
	
	// getters
	public String getTitle() { return this.title; }
	
	public String getAuthor() { return this.author; }
	
	public String getAlbum() { return this.album; }
	
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		
		if (o == this) return true;
		
		else if (o.getClass() != getClass()) return false;
		
		else {
			return this.title == ((Song) o).title &&  
		           this.author == ((Song) o).author && 
		           this.album == ((Song) o).album; 
		}
	}
	
	@Override
	public String toString() {
		return String.format("Song title: %s, Author: %s, Album: %s",
				             this.title, this.author, this.album);
	}

}
