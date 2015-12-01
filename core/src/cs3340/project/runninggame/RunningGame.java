package cs3340.project.runninggame;

import com.badlogic.gdx.Game;

import cs3340.project.runninggame.Data.TimeDatabase;

/**
 * The core RunningGame instance that creates the database access for all platforms
 * and creates the main menu screen.
 * @author Jesus Ramos
 * @version 0.1
 * @since 11/28/2015
 */
public class RunningGame extends Game {
    public TimeDatabase timeDatabase;

    /**
     * Creates and loads in the database for the game.
     * @param timeDatabase the database
     */
    public RunningGame(TimeDatabase timeDatabase) {
        this.timeDatabase = timeDatabase;
    }

    /**
     * Creates and sets the main menu screen.
     */
    public void create() {
        this.setScreen(new cs3340.project.runninggame.Screens.MainMenuScreen(this));
    }

    /**
     * The main renderer for the game.
     */
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
    }
}
