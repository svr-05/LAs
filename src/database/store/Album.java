package database.store;

import java.util.ArrayList;

public class Album {
	// instance variables
	private String name, 
		           artist,
		           genre;
	private int year;
	private ArrayList<Song> songs;

	// constructor
	/*
	 * @pre name != null && artist != null && genre != null && year > 1971
	 */
	public Album(String name, String artist, String genre, int year) {
		this.name = name;
		this.artist = artist;
		this.genre = genre;
		this.year = year;
		this.songs = new ArrayList<Song>();
	}
	
	public Album(Album album) {
		this(album.name, album.artist, album.genre, album.year);
	}
	
	// getters
	public String getName() { return name; }
	
	public String getArtist() { return artist; }
	
	public String getGenre() { return genre; }
	
	public int getYear() { return year; }
	
	public ArrayList<Song> getSongs() {
		return new ArrayList<Song>(this.songs);
	}
	
	// setters
	public void addSong(Song s) {
		this.songs.add(new Song(s));
	}
	
	@Override
	public boolean equals(Object o){
		if (o == null) return false;
		
		if (o == this) return true;
		
		if (o.getClass()!= getClass()) return false;
		
		if (!(this.name.equals(((Album) o).name) && 
				   this.artist.equals(((Album) o).artist) &&
			       this.genre.equals(((Album) o).genre) && 
			       this.year == ((Album) o).year && 
			       this.songs.size() == ((Album) o).songs.size())) return false;
		
		for (int i = 0; i < this.songs.size(); i++) {
			if (!this.songs.get(i).equals(((Album) o).songs.get(i))) return false;
		}

		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Album name: " + this.name);
		sb.append(", Author: " + this.artist);
		sb.append(", Genre: " + this.genre);
		sb.append(", Release year: " + this.year);
		String songs = "Tracklist:";
		for (Song s : this.songs) {
			songs += "\n- " + s.getTitle();
		}
		sb.append("\n" + songs.trim());
		return sb.toString();
	}

}
