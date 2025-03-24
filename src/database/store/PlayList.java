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
	public PlayList(PlayList P) {
		this.title = P.getTitle();
		this.body = P.getBody();
	}

	//Getters
	public String getTitle() {
		return this.title;
	}

	public ArrayList<Song> getBody() {
		return new ArrayList<>(this.body);
	}
	
	//Methods
	public void addSong(Song s) {
		body.add(s); // no need for a copy
	}
	
	public void remove(Song s) {
		body.remove(s);
	}
	
	public boolean contains(Song s) {
		for(Song sg : body) {
			if(sg.equals(s)) {
				return true;
			}
		}
		return false;
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
