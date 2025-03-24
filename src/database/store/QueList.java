package model;

import java.util.ArrayList;
import java.util.HashMap;

public class QueList {
	private ArrayList<Song> list;
	private HashMap<Song, Integer> countlist;
	
	//Constructors
	public QueList() {
		this.list = new ArrayList<>();
		this.countlist = new HashMap<>();
	}
	
	//Getters
	public ArrayList<Song> getQueue(){
		return new ArrayList<Song>(this.list);
	}
	public HashMap<Song, Integer> getCounts(){
		return new HashMap<Song, Integer>(this.countlist);
	}
	
	//Get 10 Most Frequently Played and 10 Recently Played
	public String getRecentlyPlayed(){
		String result = "";
		for( int i = 0; i < list.size(); i++) { result += "-" + list.get(i) + "\n"; }
		for( int j = list.size(); j < 10; j++) { result += "-\n"; }
		return result;
	}
	
	public String getFrequentlyPlayed(){
        ArrayList<Song> keys = new ArrayList<>(countlist.keySet());
        keys.sort((s1, s2) -> countlist.get(s2) - countlist.get(s1)); // Sort in descending order by count

        // Create result string with song and count
        String result = "";
        for (int i = 0; i < keys.size(); i++) {
            result += "-" + keys.get(i) + " (" + countlist.get(keys.get(i)) + ")\n";
        }
        for ( int j = keys.size(); j < 10; j++) { result += "-\n"; }
        return result.toString();
	}
	public ArrayList<Song> retrieveListOfFreq(){
        ArrayList<Song> keys = new ArrayList<>(countlist.keySet());
        keys.sort((s1, s2) -> countlist.get(s2) - countlist.get(s1)); // Sort in descending order by count
        return keys;
	}
	
	
	
	//Add to the Queue and Adds to the Song Count
	public void addQue(Song s) {
	    if (!countlist.containsKey(s)) { 
	        countlist.put(s, 1);
	        if (list.size() < 10) {
	            list.add(0, s); // Add to the front (most recent)
	        } else {
	            list.add(0, s);
	            removeQue(); // Remove the last song if over capacity
	        }
	    } else {
	        countlist.put(s, countlist.get(s) + 1);
	    }
	}
	
	@Override
	public String toString() {
		String result = "QUEUED: ";
		for(int i = 0; i < list.size() - 1; i++) {
			result += list.get(i) + ",";
		}
		result += "\nSONG COUNT: ";
		for(Song s : countlist.keySet()) {
			result += s.getTitle() + " , " + countlist.get(s);
		}
		return result;
	}
	
	// Helper Remove from the Queue
	private void removeQue() {
		list.remove(list.size() - 1);
	}
}
