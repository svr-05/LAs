package database.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import database.store.Album;

class TestMusicStore {
	
	MusicStore musicStore = new MusicStore();

	@Test
    void testCheckStoreSong() {
        SongData songData = new SongData("La Bachata", "Manuel Turizo", "2000");
        musicStore.getSongData().add(songData);
     
        assertFalse(musicStore.checkStoreSong("Sola"));
	}


}
