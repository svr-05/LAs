package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.SongData;
import model.store.MusicStore;

class TestMusicStore {

	@Test
    void testCheckStoreSongEnc() {
		MusicStore musicStore = new MusicStore();
        SongData songData = new SongData("La Bachata", "Manuel Turizo", "2000");
        musicStore.getSongData().add(songData);
     
        assertFalse(musicStore.checkStoreSong("Sola"));
        assertTrue(musicStore.checkStoreSong("La Bachata"));
	}
	
	@Test
	void testGetSongData() {
		MusicStore mS = new MusicStore();
		mS.parseAlbums();
		String s = "[Song title: Daydreamer, Author: Adele, Album: 19, Rating: Not Rated Yet, Not Favorite, Song title: Best for Last, Author: Adele, Album: 19, Rating: Not Rated Yet, Not Favorite, Song title: Chasing Pavements, Author: Adele, Album: 19, Rating: Not Rated Yet, Not Favorite, Song title: Cold Shoulder, Author: Adele, Album: 19, Rating: Not Rated Yet, Not Favorite, Song title: Crazy for You, Author: Adele, Album: 19, Rating: Not Rated Yet, Not Favorite, Song title: Melt My Heart to Stone, Author: Adele, Album: 19, Rating: Not Rated Yet, Not Favorite, Song title: First Love, Author: Adele, Album: 19, Rating: Not Rated Yet, Not Favorite, Song title: Right as Rain, Author: Adele, Album: 19, Rating: Not Rated Yet, Not Favorite, Song title: Make You Feel My Love, Author: Adele, Album: 19, Rating: Not Rated Yet, Not Favorite, Song title: My Same, Author: Adele, Album: 19, Rating: Not Rated Yet, Not Favorite, Song title: Tired, Author: Adele, Album: 19, Rating: Not Rated Yet, Not Favorite, Song title: Hometown Glory, Author: Adele, Album: 19, Rating: Not Rated Yet, Not Favorite, Song title: Rolling in the Deep, Author: Adele, Album: 21, Rating: Not Rated Yet, Not Favorite, Song title: Rumour Has It, Author: Adele, Album: 21, Rating: Not Rated Yet, Not Favorite, Song title: Turning Tables, Author: Adele, Album: 21, Rating: Not Rated Yet, Not Favorite, Song title: Don't You Remember, Author: Adele, Album: 21, Rating: Not Rated Yet, Not Favorite, Song title: Set Fire to the Rain, Author: Adele, Album: 21, Rating: Not Rated Yet, Not Favorite, Song title: He Won't Go, Author: Adele, Album: 21, Rating: Not Rated Yet, Not Favorite, Song title: Take It All, Author: Adele, Album: 21, Rating: Not Rated Yet, Not Favorite, Song title: I'll Be Waiting, Author: Adele, Album: 21, Rating: Not Rated Yet, Not Favorite, Song title: One and Only, Author: Adele, Album: 21, Rating: Not Rated Yet, Not Favorite, Song title: Lovesong, Author: Adele, Album: 21, Rating: Not Rated Yet, Not Favorite, Song title: Someone Like You, Author: Adele, Album: 21, Rating: Not Rated Yet, Not Favorite, Song title: I Found a Boy, Author: Adele, Album: 21, Rating: Not Rated Yet, Not Favorite, Song title: My Heart Is Full, Author: Norah Jones, Album: Begin Again, Rating: Not Rated Yet, Not Favorite, Song title: Begin Again, Author: Norah Jones, Album: Begin Again, Rating: Not Rated Yet, Not Favorite, Song title: It Was You, Author: Norah Jones, Album: Begin Again, Rating: Not Rated Yet, Not Favorite, Song title: A Song with No Name, Author: Norah Jones, Album: Begin Again, Rating: Not Rated Yet, Not Favorite, Song title: Uh Oh, Author: Norah Jones, Album: Begin Again, Rating: Not Rated Yet, Not Favorite, Song title: Wintertime, Author: Norah Jones, Album: Begin Again, Rating: Not Rated Yet, Not Favorite, Song title: Just a Little Bit, Author: Norah Jones, Album: Begin Again, Rating: Not Rated Yet, Not Favorite, Song title: Hold On, Author: Alabama Shakes, Album: Boys & Girls, Rating: Not Rated Yet, Not Favorite, Song title: I Found You, Author: Alabama Shakes, Album: Boys & Girls, Rating: Not Rated Yet, Not Favorite, Song title: Hang Loose, Author: Alabama Shakes, Album: Boys & Girls, Rating: Not Rated Yet, Not Favorite, Song title: Rise to the Sun, Author: Alabama Shakes, Album: Boys & Girls, Rating: Not Rated Yet, Not Favorite, Song title: You Ain't Alone, Author: Alabama Shakes, Album: Boys & Girls, Rating: Not Rated Yet, Not Favorite, Song title: Goin' to the Party, Author: Alabama Shakes, Album: Boys & Girls, Rating: Not Rated Yet, Not Favorite, Song title: Heartbreaker, Author: Alabama Shakes, Album: Boys & Girls, Rating: Not Rated Yet, Not Favorite, Song title: Boys & Girls, Author: Alabama Shakes, Album: Boys & Girls, Rating: Not Rated Yet, Not Favorite, Song title: Be Mine, Author: Alabama Shakes, Album: Boys & Girls, Rating: Not Rated Yet, Not Favorite, Song title: I Ain't the Same, Author: Alabama Shakes, Album: Boys & Girls, Rating: Not Rated Yet, Not Favorite, Song title: On Your Way, Author: Alabama Shakes, Album: Boys & Girls, Rating: Not Rated Yet, Not Favorite, Song title: Heavy Chevy (Bonus Track), Author: Alabama Shakes, Album: Boys & Girls, Rating: Not Rated Yet, Not Favorite, Song title: Como Un Perro Enloquecido, Author: Mana, Album: Cuando Los Angeles Lloran, Rating: Not Rated Yet, Not Favorite, Song title: Selva Negra, Author: Mana, Album: Cuando Los Angeles Lloran, Rating: Not Rated Yet, Not Favorite, Song title: Hundido En Un Rincon, Author: Mana, Album: Cuando Los Angeles Lloran, Rating: Not Rated Yet, Not Favorite, Song title: El Reloj Cucu, Author: Mana, Album: Cuando Los Angeles Lloran, Rating: Not Rated Yet, Not Favorite, Song title: Mis Ojos, Author: Mana, Album: Cuando Los Angeles Lloran, Rating: Not Rated Yet, Not Favorite, Song title: Ana, Author: Mana, Album: Cuando Los Angeles Lloran, Rating: Not Rated Yet, Not Favorite, Song title: Siempre El Amor, Author: Mana, Album: Cuando Los Angeles Lloran, Rating: Not Rated Yet, Not Favorite, Song title: Cuando Los Angeles Lloran, Author: Mana, Album: Cuando Los Angeles Lloran, Rating: Not Rated Yet, Not Favorite, Song title: Dejame Entrar, Author: Mana, Album: Cuando Los Angeles Lloran, Rating: Not Rated Yet, Not Favorite, Song title: No Ha Parado de Llover, Author: Mana, Album: Cuando Los Angeles Lloran, Rating: Not Rated Yet, Not Favorite, Song title: Antifaz, Author: Mana, Album: Cuando Los Angeles Lloran, Rating: Not Rated Yet, Not Favorite, Song title: El Borracho, Author: Mana, Album: Cuando Los Angeles Lloran, Rating: Not Rated Yet, Not Favorite, Song title: City Of Angels, Author: Ozomatli, Album: Don't Mess With the Dragon, Rating: Not Rated Yet, Not Favorite, Song title: After Party, Author: Ozomatli, Album: Don't Mess With the Dragon, Rating: Not Rated Yet, Not Favorite, Song title: Don't Mess With The Dragon, Author: Ozomatli, Album: Don't Mess With the Dragon, Rating: Not Rated Yet, Not Favorite, Song title: La Gallina, Author: Ozomatli, Album: Don't Mess With the Dragon, Rating: Not Rated Yet, Not Favorite, Song title: Magnolia Soul, Author: Ozomatli, Album: Don't Mess With the Dragon, Rating: Not Rated Yet, Not Favorite, Song title: Here We Go, Author: Ozomatli, Album: Don't Mess With the Dragon, Rating: Not Rated Yet, Not Favorite, Song title: La Temperatura, Author: Ozomatli, Album: Don't Mess With the Dragon, Rating: Not Rated Yet, Not Favorite, Song title: Violeta, Author: Ozomatli, Album: Don't Mess With the Dragon, Rating: Not Rated Yet, Not Favorite, Song title: Creo, Author: Ozomatli, Album: Don't Mess With the Dragon, Rating: Not Rated Yet, Not Favorite, Song title: When I Close My Eyes, Author: Ozomatli, Album: Don't Mess With the Dragon, Rating: Not Rated Yet, Not Favorite, Song title: Oppression, Author: Ben Harper, Album: Fight for Your Mind, Rating: Not Rated Yet, Not Favorite, Song title: Ground on Down, Author: Ben Harper, Album: Fight for Your Mind, Rating: Not Rated Yet, Not Favorite, Song title: Another Lonely Day, Author: Ben Harper, Album: Fight for Your Mind, Rating: Not Rated Yet, Not Favorite, Song title: Gold to Me, Author: Ben Harper, Album: Fight for Your Mind, Rating: Not Rated Yet, Not Favorite, Song title: Burn One Down, Author: Ben Harper, Album: Fight for Your Mind, Rating: Not Rated Yet, Not Favorite, Song title: Excuse Me Mr., Author: Ben Harper, Album: Fight for Your Mind, Rating: Not Rated Yet, Not Favorite, Song title: People Lead, Author: Ben Harper, Album: Fight for Your Mind, Rating: Not Rated Yet, Not Favorite, Song title: Fight for Your Mind, Author: Ben Harper, Album: Fight for Your Mind, Rating: Not Rated Yet, Not Favorite, Song title: Give a Man a Home, Author: Ben Harper, Album: Fight for Your Mind, Rating: Not Rated Yet, Not Favorite, Song title: By My Side, Author: Ben Harper, Album: Fight for Your Mind, Rating: Not Rated Yet, Not Favorite, Song title: Power of the Gospel, Author: Ben Harper, Album: Fight for Your Mind, Rating: Not Rated Yet, Not Favorite, Song title: God Fearing Man, Author: Ben Harper, Album: Fight for Your Mind, Rating: Not Rated Yet, Not Favorite, Song title: One Road to Freedom, Author: Ben Harper, Album: Fight for Your Mind, Rating: Not Rated Yet, Not Favorite, Song title: El Camino, Author: Amos Lee, Album: Mission Bell, Rating: Not Rated Yet, Not Favorite, Song title: Windows Are Rolled Down, Author: Amos Lee, Album: Mission Bell, Rating: Not Rated Yet, Not Favorite, Song title: Flower, Author: Amos Lee, Album: Mission Bell, Rating: Not Rated Yet, Not Favorite, Song title: Stay With Me, Author: Amos Lee, Album: Mission Bell, Rating: Not Rated Yet, Not Favorite, Song title: Out of the Cold, Author: Amos Lee, Album: Mission Bell, Rating: Not Rated Yet, Not Favorite, Song title: Jesus, Author: Amos Lee, Album: Mission Bell, Rating: Not Rated Yet, Not Favorite, Song title: Hello Again, Author: Amos Lee, Album: Mission Bell, Rating: Not Rated Yet, Not Favorite, Song title: Cup of Sorrow, Author: Amos Lee, Album: Mission Bell, Rating: Not Rated Yet, Not Favorite, Song title: Clear Blue Eyes (feat. Lucinda Williams), Author: Amos Lee, Album: Mission Bell, Rating: Not Rated Yet, Not Favorite, Song title: Behind Me Now, Author: Amos Lee, Album: Mission Bell, Rating: Not Rated Yet, Not Favorite, Song title: Going Home, Author: Leonard Cohen, Album: Old Ideas, Rating: Not Rated Yet, Not Favorite, Song title: Amen, Author: Leonard Cohen, Album: Old Ideas, Rating: Not Rated Yet, Not Favorite, Song title: Show Me the Place, Author: Leonard Cohen, Album: Old Ideas, Rating: Not Rated Yet, Not Favorite, Song title: Darkness, Author: Leonard Cohen, Album: Old Ideas, Rating: Not Rated Yet, Not Favorite, Song title: Anyhow, Author: Leonard Cohen, Album: Old Ideas, Rating: Not Rated Yet, Not Favorite, Song title: Crazy to Love You, Author: Leonard Cohen, Album: Old Ideas, Rating: Not Rated Yet, Not Favorite, Song title: Come Healing, Author: Leonard Cohen, Album: Old Ideas, Rating: Not Rated Yet, Not Favorite, Song title: Banjo, Author: Leonard Cohen, Album: Old Ideas, Rating: Not Rated Yet, Not Favorite, Song title: Lullaby, Author: Leonard Cohen, Album: Old Ideas, Rating: Not Rated Yet, Not Favorite, Song title: Different Sides, Author: Leonard Cohen, Album: Old Ideas, Rating: Not Rated Yet, Not Favorite, Song title: Sigh No More, Author: Mumford & Sons, Album: Sigh No More, Rating: Not Rated Yet, Not Favorite, Song title: The Cave, Author: Mumford & Sons, Album: Sigh No More, Rating: Not Rated Yet, Not Favorite, Song title: Winter Winds, Author: Mumford & Sons, Album: Sigh No More, Rating: Not Rated Yet, Not Favorite, Song title: Roll Away Your Stone, Author: Mumford & Sons, Album: Sigh No More, Rating: Not Rated Yet, Not Favorite, Song title: White Blank Page, Author: Mumford & Sons, Album: Sigh No More, Rating: Not Rated Yet, Not Favorite, Song title: I Gave You All, Author: Mumford & Sons, Album: Sigh No More, Rating: Not Rated Yet, Not Favorite, Song title: Little Lion Man, Author: Mumford & Sons, Album: Sigh No More, Rating: Not Rated Yet, Not Favorite, Song title: Timshel, Author: Mumford & Sons, Album: Sigh No More, Rating: Not Rated Yet, Not Favorite, Song title: Thistle & Weeds, Author: Mumford & Sons, Album: Sigh No More, Rating: Not Rated Yet, Not Favorite, Song title: Awake My Soul, Author: Mumford & Sons, Album: Sigh No More, Rating: Not Rated Yet, Not Favorite, Song title: Dust Bowl Dance, Author: Mumford & Sons, Album: Sigh No More, Rating: Not Rated Yet, Not Favorite, Song title: After the Storm, Author: Mumford & Sons, Album: Sigh No More, Rating: Not Rated Yet, Not Favorite, Song title: Made for You, Author: OneRepublic, Album: Waking Up, Rating: Not Rated Yet, Not Favorite, Song title: All the Right Moves, Author: OneRepublic, Album: Waking Up, Rating: Not Rated Yet, Not Favorite, Song title: Secrets, Author: OneRepublic, Album: Waking Up, Rating: Not Rated Yet, Not Favorite, Song title: Everybody Loves Me, Author: OneRepublic, Album: Waking Up, Rating: Not Rated Yet, Not Favorite, Song title: Missing Persons 1 & 2, Author: OneRepublic, Album: Waking Up, Rating: Not Rated Yet, Not Favorite, Song title: Good Life, Author: OneRepublic, Album: Waking Up, Rating: Not Rated Yet, Not Favorite, Song title: All This Time, Author: OneRepublic, Album: Waking Up, Rating: Not Rated Yet, Not Favorite, Song title: Fear, Author: OneRepublic, Album: Waking Up, Rating: Not Rated Yet, Not Favorite, Song title: Waking Up, Author: OneRepublic, Album: Waking Up, Rating: Not Rated Yet, Not Favorite, Song title: Marchin On, Author: OneRepublic, Album: Waking Up, Rating: Not Rated Yet, Not Favorite, Song title: Lullaby, Author: OneRepublic, Album: Waking Up, Rating: Not Rated Yet, Not Favorite, Song title: Politik, Author: Coldplay, Album: A Rush of Blood to the Head, Rating: Not Rated Yet, Not Favorite, Song title: In My Place, Author: Coldplay, Album: A Rush of Blood to the Head, Rating: Not Rated Yet, Not Favorite, Song title: God Put a Smile Upon Your Face, Author: Coldplay, Album: A Rush of Blood to the Head, Rating: Not Rated Yet, Not Favorite, Song title: The Scientist, Author: Coldplay, Album: A Rush of Blood to the Head, Rating: Not Rated Yet, Not Favorite, Song title: Clocks, Author: Coldplay, Album: A Rush of Blood to the Head, Rating: Not Rated Yet, Not Favorite, Song title: Daylight, Author: Coldplay, Album: A Rush of Blood to the Head, Rating: Not Rated Yet, Not Favorite, Song title: Green Eyes, Author: Coldplay, Album: A Rush of Blood to the Head, Rating: Not Rated Yet, Not Favorite, Song title: Warning Sign, Author: Coldplay, Album: A Rush of Blood to the Head, Rating: Not Rated Yet, Not Favorite, Song title: A Whisper, Author: Coldplay, Album: A Rush of Blood to the Head, Rating: Not Rated Yet, Not Favorite, Song title: A Rush of Blood to the Head, Author: Coldplay, Album: A Rush of Blood to the Head, Rating: Not Rated Yet, Not Favorite, Song title: Amsterdam, Author: Coldplay, Album: A Rush of Blood to the Head, Rating: Not Rated Yet, Not Favorite, Song title: Coat of Many Colors, Author: Dolly Parton, Album: Coat of Many Colors, Rating: Not Rated Yet, Not Favorite, Song title: Traveling Man, Author: Dolly Parton, Album: Coat of Many Colors, Rating: Not Rated Yet, Not Favorite, Song title: My Blue Tears, Author: Dolly Parton, Album: Coat of Many Colors, Rating: Not Rated Yet, Not Favorite, Song title: If I Lose My Mind, Author: Dolly Parton, Album: Coat of Many Colors, Rating: Not Rated Yet, Not Favorite, Song title: The Mystery of the Mystery, Author: Dolly Parton, Album: Coat of Many Colors, Rating: Not Rated Yet, Not Favorite, Song title: She Never Met a Man (She Didn't Like), Author: Dolly Parton, Album: Coat of Many Colors, Rating: Not Rated Yet, Not Favorite, Song title: Early Morning Breeze, Author: Dolly Parton, Album: Coat of Many Colors, Rating: Not Rated Yet, Not Favorite, Song title: The Way I See You, Author: Dolly Parton, Album: Coat of Many Colors, Rating: Not Rated Yet, Not Favorite, Song title: Here I Am, Author: Dolly Parton, Album: Coat of Many Colors, Rating: Not Rated Yet, Not Favorite, Song title: A Better Place to Live, Author: Dolly Parton, Album: Coat of Many Colors, Rating: Not Rated Yet, Not Favorite, Song title: I Feel The Earth Move, Author: Carol King, Album: Tapestry, Rating: Not Rated Yet, Not Favorite, Song title: So Far Away, Author: Carol King, Album: Tapestry, Rating: Not Rated Yet, Not Favorite, Song title: Home Again, Author: Carol King, Album: Tapestry, Rating: Not Rated Yet, Not Favorite, Song title: Beautiful, Author: Carol King, Album: Tapestry, Rating: Not Rated Yet, Not Favorite, Song title: Way Over Yonder, Author: Carol King, Album: Tapestry, Rating: Not Rated Yet, Not Favorite, Song title: You've Got A Friend, Author: Carol King, Album: Tapestry, Rating: Not Rated Yet, Not Favorite, Song title: Where You Lead, Author: Carol King, Album: Tapestry, Rating: Not Rated Yet, Not Favorite, Song title: Will You Love Me Tomorrow?, Author: Carol King, Album: Tapestry, Rating: Not Rated Yet, Not Favorite, Song title: Tapestry, Author: Carol King, Album: Tapestry, Rating: Not Rated Yet, Not Favorite, Song title: (You Make Me Feel Like) A Natural Woman, Author: Carol King, Album: Tapestry, Rating: Not Rated Yet, Not Favorite, Song title: Heavy for You, Author: The Heavy, Album: Sons, Rating: Not Rated Yet, Not Favorite, Song title: The Thief, Author: The Heavy, Album: Sons, Rating: Not Rated Yet, Not Favorite, Song title: Better as One, Author: The Heavy, Album: Sons, Rating: Not Rated Yet, Not Favorite, Song title: Fire, Author: The Heavy, Album: Sons, Rating: Not Rated Yet, Not Favorite, Song title: Fighting for the Same Thing, Author: The Heavy, Album: Sons, Rating: Not Rated Yet, Not Favorite, Song title: Hurt Interlude, Author: The Heavy, Album: Sons, Rating: Not Rated Yet, Not Favorite, Song title: Put the Hurt on Me, Author: The Heavy, Album: Sons, Rating: Not Rated Yet, Not Favorite, Song title: Simple Things, Author: The Heavy, Album: Sons, Rating: Not Rated Yet, Not Favorite, Song title: A Whole Lot of Love, Author: The Heavy, Album: Sons, Rating: Not Rated Yet, Not Favorite, Song title: What Don't Kill You, Author: The Heavy, Album: Sons, Rating: Not Rated Yet, Not Favorite, Song title: Burn Bright, Author: The Heavy, Album: Sons, Rating: Not Rated Yet, Not Favorite]\r\n";
		assertEquals(s.strip(), mS.getSongData().toString().strip());	
	}
	
	@Test
	void testGetStore() {
		MusicStore mS = new MusicStore();
		mS.parseAlbums();
		String s = "";
		assertFalse(s.equals(mS.getStore().toString()));
	}
	
	@Test
	void testCheckStoreSong() {
		MusicStore mS = new MusicStore();
		mS.parseAlbums();
		assertTrue(mS.checkStoreSong("DayDreAmer"));
		assertFalse(mS.checkStoreSong("j"));
	}
	
	@Test
	void testCheckStoreByString() {
		MusicStore mS = new MusicStore();
		mS.parseAlbums();
		
		mS.searchSongbyString("DayDreamer");
		mS.searchAlbumbyString("a");
	}
	
	@Test
	void testCheckStoreAlbum() {
		MusicStore mS = new MusicStore();
		mS.parseAlbums();
		
		assertTrue(mS.checkStoreAlbum("19"));
		assertFalse(mS.checkStoreAlbum("Rayo"));
	}
	
	@Test
	void testSearchSongByTitleArtist() {
		MusicStore mS = new MusicStore();
		mS.parseAlbums();
		
		mS.searchSongByTitleArtist("b");
		mS.searchSongByTitleArtist("Adele");
	}
	
	@Test 
	void testSearchAlbumByTitleAuthor() {
		MusicStore mS = new MusicStore();
		mS.parseAlbums();
		
		mS.searchAlbumbyTitleAuthor("b");
		mS.searchAlbumbyTitleAuthor("Adele");
	}


}
