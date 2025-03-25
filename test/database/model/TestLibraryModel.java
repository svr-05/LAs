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
	}
	
	@Test
	void testAddAlbum() {
		LibraryModel lB = new LibraryModel();
		lB.addAlbum("19");
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
		lB.getSongTitles();
		lB.addSong("uh oh");
		
		assertEquals("Uh Oh", lB.getSongTitles().get(0));
	}
	
	@Test
	void testGetArtists() {
		LibraryModel lB = new LibraryModel();
		lB.getArtists();
		lB.addSong("DayDreamer");
		lB.addSong("Best for Last");
		
		assertEquals(1, lB.getArtists().size());
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
		lB.outputAlbumInfo("Daydreamer");
		lB.addAlbum("19");
		
		assertEquals(1, lB.getAlbums().size());
	}
	
	@Test
	void testSetPrintToConsole() {
		LibraryModel lB = new LibraryModel();
		lB.setPrintToConsole(false);
	}
	
	@Test
	void testAddFavorite() {
		LibraryModel lB = new LibraryModel();
		lB.addSong("Daydreamer");
		lB.addFavorite("Daydreamer");
	}
	
	@Test
	void testMakePlayList() {
		LibraryModel lB = new LibraryModel();
		lB.makePlayList("s");
		lB.makePlayList("s");
	}
	
	@Test
	void testOutputAlbumInfo() {
		LibraryModel lB = new LibraryModel();
		lB.outputAlbumInfo("Daydreamer");
		
		lB.addSong("Daydreamer");
		lB.outputAlbumInfo("Daydreamer");
	}
	
	@Test
	void testSearchSongByGenre() {
		LibraryModel lB = new LibraryModel();

		lB.addAlbum("19");
		lB.searchSongByGenre("pop");
	}
	
	@Test
	void testPlaySong() {
		LibraryModel lB = new LibraryModel();
		lB.addSong("Daydreamer");
		lB.playSong("Daydreamer");
		
		lB.playSong("Tired");
	}
	
	@Test
	void testGetRecentlyPlayed() {
		LibraryModel lB = new LibraryModel();
		lB.makePlayList("Recently Played");
		lB.addSong("DayDreamer");
		lB.addSong("Best for Last");
		lB.addSong("Chasing Pavements");

		lB.playSong("DayDreamer");
		lB.playSong("Best for Last");
		lB.playSong("Chasing Pavements");
		PlayList p = lB.getRecentlyPlayed();
		
	}
	
	@Test
	void testGetMostPlayed() {
		LibraryModel lB = new LibraryModel();
		lB.addSong("DayDreamer");
		lB.addSong("Best for Last");
		lB.addSong("Chasing Pavements");

		lB.playSong("DayDreamer");
		lB.playSong("DayDreamer");
		lB.playSong("Best for Last");
		lB.playSong("Chasing Pavements");
		PlayList p = lB.getMostPlayed();
	}
	
	@Test
	void testGetSongsSortedByTitle() {
		LibraryModel lB = new LibraryModel();
		lB.addSong("DayDreamer");
		lB.addSong("Best for Last");
		lB.addSong("Chasing Pavements");
		
		lB.getSongsSortedByTitle();
	}
	
	
	@Test
	void testGetSongsSortedByArtist() {
		LibraryModel lB = new LibraryModel();
		lB.addSong("DayDreamer");
		lB.addSong("Best for Last");
		lB.addSong("Chasing Pavements");
		
		lB.getSongsSortedByArtist();
	}
	
	@Test
	void testGetSongsSortedByRating() {
		LibraryModel lB = new LibraryModel();
		lB.addSong("DayDreamer");
		lB.addSong("Best for Last");
		lB.addSong("Chasing Pavements");
		
		lB.getSongsSortedByRating();
	}
	
	@Test
	void testRemoveSongFromLib() {
		LibraryModel lB = new LibraryModel();
		lB.addSong("DayDreamer");
		lB.addSong("Best for Last");
		lB.addSong("Chasing Pavements");
		
		lB.removeSongFromLib("Daydreamer");
	}
	
	@Test
	void testRemoveAlbumFromLib() {
		LibraryModel lB = new LibraryModel();
		lB.addAlbum("19");
		lB.addSong("DayDreamer");
		lB.addSong("Best for Last");
		lB.addSong("Chasing Pavements");
		
		lB.removeAlbumFromLib("19");
	}
	
	@Test
	void testShufflePlayList() {
		LibraryModel lB = new LibraryModel();
		lB.makePlayList("s");
		lB.addSongToPlayList("s", "Daydreamer");
		
		lB.shufflePlayList("s");
	}
	
	@Test
	void testGetTopRated() {
		LibraryModel lB = new LibraryModel();
		lB.addAlbum("19");
		lB.addSong("DayDreamer");
		lB.addSong("Best for Last");
		lB.addSong("Chasing Pavements");
		lB.rateSong("Daydreamer", 5);
		
		PlayList p = lB.getTopRated();
	}
	
	@Test
	void testgetGenrePlaylist() {
		LibraryModel lB = new LibraryModel();
		lB.addAlbum("19");
		
		PlayList p = lB.getGenrePlaylist("Genre: pop");
	}
	
	
	
}
