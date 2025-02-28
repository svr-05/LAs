package LA1;

import java.util.ArrayList;

public class PlayList {
	
	private String title;
	private ArrayList<Song> body;
	
	//Constructors
	public PlayList(String title) {
		this.title = title;
		this.body = new ArrayList<Song>();
	}
	
	public PlayList(PlayList p) {
		this.title = p.getTitle();
		this.body = p.getBody();
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
		body.add(new Song(s));
	}
	
	public void remove(Song s) {
		body.remove(s);
	}
	
	//StringMethod
	public String toString(){
		String result = title + ": ";
		for(Song s : body) {
			result = "\n-" + s.toString();
		}
		result += "\n";
		return result;
	}

}
