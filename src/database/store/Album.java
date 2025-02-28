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
	public Album(String name, String artist, String genre, int year) {
		this.name = name;
		this.artist = artist;
		this.genre = genre;
		this.year = year;
		this.songs = new ArrayList<Song>();
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
	public void addSong(Song song) {
		this.songs.add(new Song(song));
	}
	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Album name: " + this.name);
		sb.append(", Author: " + this.artist);
		sb.append(", Genre: " + this.genre);
		sb.append(", Release year: " + this.year);
		sb.append("\nTracklist:\n");
		String songs = "";
		for (Song s : this.songs) {
			songs += s.getTitle();
			songs += "\n";
		}
		sb.append(songs.trim());
		return sb.toString();
	}
	

}
