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
	public Song(Song song) {
		this(song.title, song.author, song.album);
	}
	
	// getters
	public String getTitle() { return this.title; }
	
	public String getAuthor() { return this.author; }
	
	public String getAlbum() { return this.album; }
	
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		
		if (this == o) return true;
		
		if (getClass().getSimpleName() != o.getClass().getSimpleName()) return false;
		
		if (!(this.title == ((Song) o).title) || 
		    !(this.author == ((Song) o).author) || 
		    !(this.album == ((Song) o).album)) return false;
	
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("Song title: %s, Author: %s, Album: %s",
				             this.title, this.author, this.album);
	}

}
