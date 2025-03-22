package model;

import java.util.ArrayList;

public class PlayList {
	
	private String title;
	private ArrayList<Song> body;
	
	//Constructors
	/*
	 * @pre: title != null
	 */
	public PlayList(String title) {
		this.title = title;
		this.body = new ArrayList<Song>();
	}

	//Getters
	public String getTitle() {
		return this.title;
	}

	public ArrayList<Song> getBody() {
		return new ArrayList<>(body);
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
