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
		assertEquals(albumStr, "Album name: Rayo, Author: J Balvin, Genre: Reggeaton, Release year: 2024\nTracklist:\nEn Alta\n3 Noches");
	}

}
