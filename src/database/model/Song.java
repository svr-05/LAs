package database.model;

public class Song {
	private final String title,
	                     author,
	                     album;
	
	// constructor
	/* 
	 * @pre title != null && author != null && album != null 
	 */
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
		
		if (o == this) return true;
		
		else if (o.getClass() != getClass()) return false;
		
		else {
			return this.title.equals(((Song) o).title) &&  
		           this.author.equals(((Song) o).author) && 
		           this.album.equals(((Song) o).album); 
		}
	}
	
	@Override
	public String toString() {
		return String.format("Song title: %s, Author: %s, Album: %s",
				             this.title, this.author, this.album);
	}

}
