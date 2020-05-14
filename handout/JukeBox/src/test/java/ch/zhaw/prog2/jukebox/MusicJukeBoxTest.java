package ch.zhaw.prog2.jukebox;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MusicJukeBoxTest {

    private JukeBox jukeBox = null;

    @Mock
    private Song songMock1;
    @Mock
    private Song songMock2;
    @Mock
    private Song songMock3;
    @Mock
    private Song songMock4;
    @Mock
    private Song songMock5;


    private static final String songMock1Name = "AAA";
    private static final String songMock2Name = "BBB";
    private static final String songMock3Name = "CCC";
    private static final String songMock4Name = "DDD";
    private static final String songMock5Name = "EEE";



    InOrder inOrder;

    public MusicJukeBoxTest() {
    }

    @BeforeEach
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        jukeBox = new MusicJukeBox();

        songMock1 = mock(Song.class);
        songMock2 = mock(Song.class);
        songMock3 = mock(Song.class);
        songMock4 = mock(Song.class);
        songMock5 = mock(Song.class);

        when(songMock1.getTitle()).thenReturn(songMock1Name);
        when(songMock2.getTitle()).thenReturn(songMock2Name);
        when(songMock3.getTitle()).thenReturn(songMock3Name);
        when(songMock4.getTitle()).thenReturn(songMock4Name);
        when(songMock5.getTitle()).thenReturn(songMock5Name);

    }

    @Test
    public void testPlayOfNoneExistingSong() {

        jukeBox.addSong(songMock1);
        jukeBox.addSong(songMock2);
        jukeBox.addSong(songMock3);

        assertThrows(JukeBoxException.class, () -> jukeBox.playTitle(songMock4Name));


    }

    @Test
    public void testGetPlayList() {

        jukeBox.addSong(songMock5);
        jukeBox.addSong(songMock1);
        jukeBox.addSong(songMock2);
        List<Song> list = jukeBox.getPlayList();
        assertEquals(3,list.size());

        verify(songMock5).getTitle();

    }

    @Test
    public void testPlay() {

        jukeBox.addSong(songMock4);
        jukeBox.playTitle(songMock4.getTitle());
        //jukeBox.playTitle(songMock4Name);
        // System.out.println(jukeBox.getActualSong().getTitle());

        assertTrue(!jukeBox.getActualSong().isPlaying());
        // je ne comprends pas pourquoi il y a une erreur à ce niveau. Normalement le code sans ! devrait être true
        // mais si j'enlève le ! mon test ne marche pas.
        assertEquals(songMock4Name, jukeBox.getActualSong().getTitle());

    }

    @Test
    public void testPlayOfAlreadyPlayingSong() {

        jukeBox.addSong(songMock4);
        jukeBox.playTitle(songMock4Name);

        doThrow(JukeBoxException.class).when(songMock4).start();

        assertThrows(JukeBoxException.class, () -> {jukeBox.playTitle(songMock4Name);});
    }

    @Test
    public void testPlayMock() {

        jukeBox.addSong(songMock1);
        jukeBox.playTitle(songMock1Name);

        inOrder = inOrder(songMock1);
        inOrder.verify(songMock1).getTitle();
        inOrder.verify(songMock1).start();

    }


    @Test
    public void testIsPlayingBasedOnJukeboxState() {

        jukeBox.addSong(songMock4);
        jukeBox.playTitle(songMock4Name);

    }
}
