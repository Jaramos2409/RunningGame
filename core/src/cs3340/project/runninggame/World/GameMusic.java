package cs3340.project.runninggame.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Contains the one music track the game uses. Contains
 * the functions that allow the game to start and stop
 * playing the music.
 * @author Jesus Ramos
 * @version 0.3
 * @since 11/30/2015
 */
public class GameMusic {
    Music gameMusic;

    /**
     * Loads the mp3 file and sets the volume at 50%
     */
    public GameMusic() {
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("music/WagnerTheRideOfTheValkyries.mp3"));
        gameMusic.setVolume(.5f);
    }

    /**
     * Starts playing the music
     */
    public void musicPlay( ) {
        gameMusic.play();
    }

    /**
     * Stops playing the music
     */
    public void musicStop() {
        gameMusic.stop();
    }
}
