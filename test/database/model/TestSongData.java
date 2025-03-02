package database.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import database.store.Album;
import database.store.Song;

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
		assertEquals("Sauce Boyz 2", HCV.getAlbum());
		assertEquals("Sauce Boyz 2", SB2.getName());
	}
	
	@Test
	void testSetRating() {
		SongData song = new SongData("Space Cadet", "Metro Boomin", "NOT ALL HEROES WEAR CAPES (Deluxe)");
		
		assertEquals(SongData.ratingScale.NONE, song.getRating());
		
		song.rate(1);
		assertEquals(SongData.ratingScale.ONE, song.getRating());
		
		song.rate(2);
		assertEquals(SongData.ratingScale.TWO, song.getRating());

		song.rate(3);
		assertEquals(SongData.ratingScale.THREE, song.getRating());
		
		song.rate(4);
		assertEquals(SongData.ratingScale.FOUR, song.getRating());
		
	}
	
	@Test
	void testFavoriteStatus() {
		SongData song      = new SongData("Ignorantes", "Bad Bunny", "YHLQMDLG");
		SongData otherS = new SongData("Sólido", "J Balvin", "Rayo");
		SongData otherSong = new SongData(otherS);
		
		assertFalse(song.favoriteStatus());
		song.changeFavorite();
		assertTrue(song.favoriteStatus());
		song.changeFavorite();
		assertFalse(song.favoriteStatus());
		
		otherSong.rate(5);
		assertTrue(otherSong.favoriteStatus());
	}
	
	@Test
	void testGetSongObject() {
		SongData songD = new SongData("Chasing Pavements", "Adele", "19");
		Song song = new Song("Chasing Pavements", "Adele", "19");
		
		assertEquals(song, songD.getSongObject());
	}
	
	@Test
	void testToString() {
		String songStr = HCV.toString();
		assertEquals(songStr, "Song title: Hola Como Vas, Author: Eladio Carrion, Album: Sauce Boyz 2, Rating: Not Rated Yet, Not Favorite");
		
		SongData HC = new SongData("Hola Como Vas", "Eladio Carrion", "Sauce Boyz 2");
		HC.rate(5);
		String HCStr = HC.toString();
		assertEquals(HCStr, "Song title: Hola Como Vas, Author: Eladio Carrion, Album: Sauce Boyz 2, Rating: ★ ★ ★ ★ ★, Favorite");
		
	}
	
	
	

}
