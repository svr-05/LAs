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
	
	@Test
	void testEquals() {
		Album colores = new Album("Colores", "J Balvin", "Reggeaton", 2020);
		Song gris = new Song("Gris", "J Balvin", "Colores");
		Song g    = new Song("Gri", "J Balvin", "Colores");
		Song g1   = new Song("Gris", "J Balvi", "Colores");
		Song g2   = new Song("Gris", "J Balvin", "Colore");
		Song g3   = new Song("Gris", "J Balvin", "Colores");
		
		assertFalse(gris.equals(null));
		assertTrue(gris.equals(gris));
		assertFalse(gris.equals(colores));
		assertFalse(gris.equals(g));
		assertFalse(gris.equals(g1));
		assertFalse(gris.equals(g2));
		assertTrue(gris.equals(g3));
	}

}
