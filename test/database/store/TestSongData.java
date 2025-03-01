package database.store;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestSongData {
	
	SongData HCV = new SongData("Hola Como Vas", "Eladio Carrion", "Sauce Boyz 2");
	Album SB2 = new Album("Sauce Boyz 2", "Eladio Carrion", "Regueaton", 2021);

	@Test
	void testGetTitle() {
		assertEquals("Hola Como Vas", HCV.getTitle());
	}
	
	@Test
	void testGetAuthor() {
		assertEquals("Eladio Carrion", HCV.getAuthor());
	}
	
	@Test
	void testGetAlbum() {
		assertEquals("Sauce Boyz 2", HCV.getAlbum(), SB2.getName());
	}
	
	@Test
	void testSetRating() {
		SongData song = new SongData("Space Cadet", "Metro Boomin", "NOT ALL HEROES WEAR CAPES (Deluxe)");
		assertNull(song.getRating()); // No default rating
		
		song.rate(1);
		assertEquals("★", song.getRating());
		
		song.rate(2);
		assertEquals("★ ★", song.getRating());
		
		song.rate(6);	
		assertNull(song.getRating());
		
	}
	
	@Test
	void testFavoriteStatus() {
		SongData song      = new SongData("Ignorantes", "Bad Bunny", "YHLQMDLG");
		SongData otherS = new SongData("Sólido", "J Balvin", "Rayo");
		SongData otherSong = new SongData(otherS);
		
		assertFalse(song.favoriteStatus());
		song.setFavorite();
		assertTrue(song.favoriteStatus());
		
		otherSong.rate(5);
		assertTrue(song.favoriteStatus());
	}
	
	
	

}
