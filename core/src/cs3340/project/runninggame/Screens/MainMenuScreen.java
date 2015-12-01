package cs3340.project.runninggame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import cs3340.project.runninggame.RunningGame;
import cs3340.project.runninggame.UI.MainMenuOptions;

/**
 * The main menu screen that contains all the two base options: Play Game and Quit.
 * @author Jesus Ramos
 * @version 0.2
 * @since 11/28/2015
 */
public class MainMenuScreen implements Screen {
    final RunningGame game;
    Stage mainMenu;
    MainMenuOptions mainMenuOptions;
    OrthographicCamera camera;

    /**
     * Creates and sets up the camera and main menu options.
     * @param gam the single RunningGame instance
     */
    public MainMenuScreen(final RunningGame gam) {
        game = gam;
        camera = new OrthographicCamera();

        mainMenu = new Stage(new StretchViewport(720, 1280, camera));
        mainMenuOptions = new MainMenuOptions(game);
        mainMenu.addActor(mainMenuOptions.getMainMenuOptionsTable());

        Gdx.input.setInputProcessor(mainMenu);
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

        mainMenu.act(delta);
        mainMenu.draw();
    }

    /**
     * Required for when a new viewport is attributed to the endMenu stage.
     * @param width the width of the new viewport
     * @param height the height of the new viewport
     */
    @Override
    public void resize(int width, int height) {
        mainMenu.getViewport().update(width, height, true);
    }

    /**
     * Disposes of the menu and the screen when they are not longer being used.
     */
    @Override
    public void dispose() {
        mainMenu.dispose();
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