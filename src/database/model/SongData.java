package database.model;

public class SongData {
	// instance variables
	public enum ratingScale { 
		NONE("Not Rated Yet"),
		ONE("★"),
	    TWO("★ ★"),
	    THREE("★ ★ ★"),
	    FOUR("★ ★ ★ ★"),
	    FIVE("★ ★ ★ ★ ★");
		
		private final String r;

		ratingScale(String r) {
			this.r = r;
		} 
		
		@Override
		public String toString() { // the toString is the "bridge" between the enums and the stars
			return this.r;
		}
	};
	
	private String title,
	               author,
	               album;
	private boolean isFavorite;
	private ratingScale rating;
	
	// constructor
	public SongData(String title, String author, String album) {
		this.title = title;
		this.author = author;
		this.album = album;
		this.rating = ratingScale.NONE;
		this.isFavorite = false;
	}
	
	// copy constructor
	public SongData(SongData song) {
		this(song.title, song.author, song.album);
		this.rating = song.rating;
		this.isFavorite = song.isFavorite;
	}
	
	// getters
	public String getTitle() { return this.title; }
	
	public String getAuthor() { return this.author; }
	
	public String getAlbum() { return this.album; }
	
	public ratingScale getRating() { return this.rating; }

	
  public Song getSongObject() {
		return new Song(this.title, this.author, this.album);
	}
  
	public boolean favoriteStatus() { return this.isFavorite; }
	
	// setters
	public void changeFavorite() { this.isFavorite = true; }
	
	public void rate(int r) {
		if (r < 1 || r > 5) {
			System.out.println("Please Enter an integer between 1 and 5!");
			return;
		}
		switch (r) {
			case 1:
				this.rating = ratingScale.ONE;
				break;
			case 2:
				this.rating = ratingScale.TWO;
				break;
			case 3:
				this.rating = ratingScale.THREE;
				break;
			case 4:
				this.rating = ratingScale.FOUR;
				break;
			case 5:
				this.rating = ratingScale.FIVE;
				this.isFavorite = true;
				break;
		}
		System.out.println("Rating saved!");
	}
	
	@Override
	public String toString() {
		String fav = (this.isFavorite) ? "Favorite" : "Not Favorite";
		return String.format("Song title: %s, Author: %s, Album: %s, Rating: %s, %s",
				             this.title, this.author, this.album, this.rating, fav);
	}

}
