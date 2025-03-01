package database.store;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestSong {
	Song laBachata = new Song("La Bachata", "Manuel Turizo", "2000");
	Song copy      = new Song(laBachata);

	@Test
	void testGetTitle() {
		assertEquals("La Bachata", laBachata.getTitle(), 
				                    copy.getTitle());
	}
	
	@Test
	void testGetAuthor() {
		assertEquals("Manuel Turizo", laBachata.getAuthor(), 
				                      copy.getAuthor());
	}
	
	@Test
	void testGetAlbum() {
		assertEquals("2000", laBachata.getAlbum(), 
				             copy.getAlbum());
	}
	
	@Test
	void testToString() {
		String LBStr = laBachata.toString();
		String copyStr = copy.toString();
		String out = "Song title: La Bachata, Author: Manuel Turizo, Album: 2000";
		assertEquals(out, LBStr, copyStr);
	}

}
