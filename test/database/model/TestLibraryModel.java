package database.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestLibraryModel {

	@Test
	void testGetUserList() {
		LibraryModel lB = new LibraryModel();
		assertEquals(lB.getUserList().toString(), "[]");
	}
	
	@Test
	void testAddSong() {
		LibraryModel lB = new LibraryModel();
		lB.addSong("DayDreamer");
		assertEquals(1, lB.getSongLibrary().size());
	}
	
	@Test
	void testAddAlbum() {
		LibraryModel lB = new LibraryModel();
		lB.addAlbum("19");
		assertEquals(1, lB.getAlbumLibrary().size());
	}
	
	@Test
	void testAddFavorite() {
		LibraryModel lB = new LibraryModel();
		lB.addFavorite("DayDreamer");
	}
	
	@Test
	void testAddSongToPlayList() {
		LibraryModel lB = new LibraryModel();
		lB.makePlayList("S");
		
		lB.addSongToPlayList("S", "DayDreamer");
		lB.addSongToPlayList("b", "a");
	}
	
	@Test
	void testRemoveSongFromPlayList() {
		LibraryModel lB = new LibraryModel();
		lB.removeSongFromPlayList("s", "uh oh");
		lB.makePlayList("S");
		lB.addSong("DayDreamer");
		lB.addSongToPlayList("S", "DayDreamer");
		lB.removeSongFromPlayList("s", "DayDreamer");
		assertEquals(lB.getUserList().get(0).getBody().size(), 0);
		lB.removeSongFromPlayList("s", "uh oh");
	}
	
	@Test
	void testRateSong() {
		LibraryModel lB = new LibraryModel();
		lB.rateSong("DayDreamer", 5);
		
		lB.addSong("DayDreamer");
		lB.addSong("uh oh");
		lB.rateSong("DayDreamer", 5);
		lB.rateSong("uh oh", 4);
		assertEquals(lB.getFavorites().size(), 1);
		
	}
	
	@Test
	void testGetSongTitles() {
		LibraryModel lB = new LibraryModel();
		lB.addSong("uh oh");
		
		assertEquals("Uh Oh", lB.getSongTitles().get(0));
	}
	
	@Test
	void testGetArtists() {
		LibraryModel lB = new LibraryModel();
		lB.addSong("DayDreamer");
		
		assertEquals("Adele", lB.getArtists().get(0));
	}
	
	@Test
	void testGetAlbumList() {
		LibraryModel lB = new LibraryModel();
		lB.addAlbum("19");
		
		assertEquals("19", lB.getAlbumList().get(0));
	}
	
	@Test
	void testSearchSongByString() {
		LibraryModel lB = new LibraryModel();
		lB.addAlbum("19");
		
		lB.searchAlbumbyString("19");
	}
	
	@Test
	void testSearchAlbumbyTitleAuthor() {
		LibraryModel lB = new LibraryModel();
		lB.addAlbum("19");
		
		lB.searchAlbumbyTitleAuthor("Adele");
	}
	
	@Test
	void testSearchSongByTitleArtist() {
		LibraryModel lB = new LibraryModel();
		lB.addAlbum("19");
		
		lB.searchSongByTitleArtist("Adele");
	}
	
	@Test
	void testSearchPlayListName() {
		LibraryModel lB = new LibraryModel();
		lB.makePlayList("S");
		
		lB.searchPlayListName("s");
		assertEquals(1, lB.getPlayList().size());

	}
	
	@Test
	void testSearchSongbyString() {
		LibraryModel lB = new LibraryModel();
		lB.addSong("uh oh");
		
		lB.searchSongbyString("uh oh");
	}
	
	@Test
	void testSearchFavorites() {
		LibraryModel lB = new LibraryModel();
		lB.addSong("DayDreamer");
		lB.rateSong("DayDreamer", 5);
		
		lB.searchFavorites("DayDreamer");
	}
	
	@Test
	void testGetAlbums() {
		LibraryModel lB = new LibraryModel();
		lB.addAlbum("19");
		
		assertEquals(1, lB.getAlbums().size());
	}
	
}
