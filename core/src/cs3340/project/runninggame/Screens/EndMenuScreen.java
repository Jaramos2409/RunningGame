package cs3340.project.runninggame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import cs3340.project.runninggame.RunningGame;
import cs3340.project.runninggame.UI.EndMenuOptions;

/**
 * Creates the end screen that the player sees after they finish a race.
 * @author Jesus Ramos
 * @version 0.1
 * @since 11/30/2015
 */
public class EndMenuScreen implements Screen {
    final RunningGame game;
    long raceFinalTime;
    Stage endMenu;
    EndMenuOptions endMenuOptions;
    OrthographicCamera camera;

    /**
     * Carrying over the single RunningGame instance and the final
     * race time, this constructor sets up the camera and menu buttons
     * for the screen. It also saves the final race time to the database.
     * @param gam the single game instance
     * @param raceTime the final race time
     */
    public EndMenuScreen(final RunningGame gam, long raceTime) {
        game = gam;
        raceFinalTime = raceTime;
        save();

        camera = new OrthographicCamera();

        endMenu = new Stage(new StretchViewport(720, 1280, camera));
        endMenuOptions = new EndMenuOptions(game, raceFinalTime);
        endMenu.addActor(endMenuOptions.getEndMenuOptionsTable());

        Gdx.input.setInputProcessor(endMenu);
    }

    /**
     * A constantly called function that draws all the menu buttons and messages
     * to the screen.
     * @param delta the time since the last time the game updated
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        endMenu.act(delta);
        endMenu.draw();
    }

    /**
     * Saves the final race time to the database.
     */
    public void save() {
        game.timeDatabase.saveTime((double)raceFinalTime/1000.0);
    }

    /**
     * Required for when a new viewport is attributed to the endMenu stage.
     * @param width the width of the new viewport
     * @param height the height of the new viewport
     */
    @Override
    public void resize(int width, int height) {
        endMenu.getViewport().update(width, height, true);
    }

    /**
     * Disposes of the menu and the screen when they are not longer being used.
     */
    @Override
    public void dispose() {
        endMenu.dispose();
        this.dispose();
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

}