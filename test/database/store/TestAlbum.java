package database.store;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestAlbum {
	
	Album album = new Album("Rayo", "J Balvin", "Reggeaton", 2024);
	Song song = new Song("En Alta", "J Balvin", "Rayo");
	Song song1 = new Song("3 Noches", "J Balvin", "Rayo");

	@Test
	void testGetName() {
		assertEquals(album.getName(), "Rayo");
	}
	
	@Test
	void testGetArtist() {
		assertEquals(album.getArtist(), "J Balvin");	
	}
	
	@Test
	void getGenre() {
		assertEquals(album.getGenre(), "Reggeaton");
	}
	
	@Test
	void testGetYear() {
		assertEquals(album.getYear(), 2024);
	}
	
	@Test
	void testAddSong() {
		album.addSong(song);
		assertEquals(album.getSongs().size(), 1);
		
	}
	
	@Test
	void testToString() {
		album.addSong(song);
		album.addSong(song1);
		String albumStr = album.toString();
		assertEquals(albumStr, "Album name: Rayo, Author: J Balvin, Genre: Reggeaton, Release year: 2024\nTracklist:\n- En Alta\n- 3 Noches");
	}
	
	@Test
	void testEquals() {
		Song gris = new Song("Gris", "J Balvin", "Colores");
		Song rosa = new Song("Rosa", "J Balvin", "Colores");
		Album colores = new Album("Colores", "J Balvin", "Reggeaton", 2020);
		colores.addSong(gris);
		colores.addSong(rosa);
		Album c       = new Album("Colore", "J Balvin", "Reggeaton", 2020);
		Album c1      = new Album("Colores", "J Balvi", "Reggeaton", 2020);
		Album c2      = new Album("Colores", "J Balvin", "Reggeato", 2020);
		Album c3      = new Album("Colores", "J Balvin", "Reggeato", 2020);
		Album c4      = new Album("Colores", "J Balvin", "Reggeato", 2021);
		Album c5      = new Album("Colores", "J Balvin", "Reggeaton", 2020);
		Album c6      = new Album("Colores", "J Balvin", "Reggeaton", 2020);
		Song rojo = new Song("Rojo", "J Balvin", "Colores");
		c6.addSong(rojo);
		c6.addSong(rosa);
		Album c7      = new Album("Colores", "J Balvin", "Reggeaton", 2020);
		c7.addSong(gris);
		c7.addSong(rosa);
		
		
		assertFalse(colores.equals(null));
		assertTrue(colores.equals(colores));
		assertFalse(colores.equals(gris));
		assertFalse(colores.equals(c));
		assertFalse(colores.equals(c1));
		assertFalse(colores.equals(c2));
		assertFalse(colores.equals(c3));
		assertFalse(colores.equals(c4));
		assertFalse(colores.equals(c5));
		assertFalse(colores.equals(c6));
		assertTrue(colores.equals(c7));
	}

}
