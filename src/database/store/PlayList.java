package model;

import java.util.HashSet;

public class PlayList {
	
	private String title;
	private HashSet<Song> body;
	
	//Constructors
	/*
	 * @pre: title != null
	 */
	public PlayList(String title) {
		this.title = title;
		this.body = new HashSet<Song>();
	}

	//Getters
	public String getTitle() {
		return this.title;
	}

	public HashSet<Song> getBody() {
		return new HashSet<>(body);
	}
	
	//Methods
	public void addSong(Song s) {
		body.add(s); // no need for a copy
	}
	
	public void remove(Song s) {
		body.remove(s);
	}
	
	//StringMethod
	@Override
	public String toString(){
		String result = "";
		result += this.title + ": ";
		for(Song s : body) {
			result += "\n-" + s.toString();
		}
		result += "\n";
		return result;
	}
	
}
