package database.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import database.model.PlayList;
import database.model.Song;

class TestPlayList {
	
	PlayList pL = new PlayList("My music");
	
	@Test
	void testGetTitle() {
		assertEquals("My music", pL.getTitle());
	}
	
	@Test
	void testAddSong() {
		assertEquals(0, pL.getBody().size());
		Song song = new Song("La Bachata", "Manuel Turizo", "2000");
		
		pL.addSong(song);
		assertEquals(1, pL.getBody().size());
	}
	
	@Test
	void testRemove() {
		PlayList p = new PlayList("Music");
		Song song = new Song("La Bachata", "Manuel Turizo", "2000");
		p.addSong(song);
		assertEquals(1, p.getBody().size());
		
		p.remove(song);
		assertEquals(0, p.getBody().size());
	}
	
	@Test
	void testToString() {
		Song song = new Song("La Bachata", "Manuel Turizo", "2000");
		pL.addSong(song);
		String pLStr = pL.toString();
		assertEquals(pLStr, "My music: \n-Song title: La Bachata, Author: Manuel Turizo, Album: 2000\n");
	}

}
