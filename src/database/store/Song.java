package LA1;

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
	public String toString() {
		return String.format("Song title: %s, Author: %s, Album: %s",
				             this.title, this.author, this.album);
	}
	
	public boolean equals(Song S) {
		if( (S.getTitle().equals(this.title)) && (S.getAuthor().equals(this.author)) && (S.getAlbum().equals(this.album))) {
			return true;
		}
		return false;
	}

}
