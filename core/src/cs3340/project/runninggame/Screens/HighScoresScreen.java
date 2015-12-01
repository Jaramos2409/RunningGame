package cs3340.project.runninggame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import cs3340.project.runninggame.RunningGame;
import cs3340.project.runninggame.UI.HighScoresOptions;

/**
 * The screen that contains all of the player's run times
 * sorted in ascending order.
 * @author Jesus Ramos
 * @version 0.1
 * @since 11/28/2015
 */
public class HighScoresScreen implements Screen{
    final RunningGame game;
    Stage highScoresMenu;
    HighScoresOptions highScoresOptions;
    OrthographicCamera camera;

    /**
     * Creates and sets up the screen's list, button, and message. Also
     * sets up the camera.
     * @param gam the single RunningGame instance
     */
    public HighScoresScreen(final RunningGame gam) {
        game = gam;

        camera = new OrthographicCamera();

        highScoresMenu = new Stage(new StretchViewport(720, 1280, camera));
        highScoresOptions = new HighScoresOptions(game);

        highScoresMenu.addActor(highScoresOptions.getHighScoresOptionsTable());

        Gdx.input.setInputProcessor(highScoresMenu);
    }

    /**
     * Prints the menu out on to the screen.
     * @param delta the time since the game last updated
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        highScoresMenu.act(delta);
        highScoresMenu.draw();
    }

    /**
     * Required for when a new viewport is attributed to the endMenu stage.
     * @param width the width of the new viewport
     * @param height the height of the new viewport
     */
    @Override
    public void resize(int width, int height) {
        highScoresMenu.getViewport().update(width, height, true);
    }

    /**
     * Disposes of the menu and the screen when they are not longer being used.
     */
    @Override
    public void dispose() {
        highScoresMenu.dispose();
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
