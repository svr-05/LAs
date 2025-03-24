package database.model;

import java.util.ArrayList;

public final class PlayList {
	
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
	
	// setters
	public void addSong(Song s) {
		if (!body.contains(s)) { // only add if song isn't already in playlist
			body.add(s);
		}
	}
	
	public void remove(Song s) {
		body.remove(s);
	}
	
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