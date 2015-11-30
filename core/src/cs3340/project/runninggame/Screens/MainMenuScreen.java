package cs3340.project.runninggame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import cs3340.project.runninggame.RunningGame;
import cs3340.project.runninggame.UI.MainMenuOptions;

public class MainMenuScreen implements Screen {

    final RunningGame game;

    Stage mainMenu;
    MainMenuOptions mainMenuOptions;

    OrthographicCamera camera;

    public MainMenuScreen(final RunningGame gam) {
        game = gam;
        camera = new OrthographicCamera();
        //camera.setToOrtho(false, 800, 480);

        mainMenu = new Stage(new StretchViewport(720, 1280, camera));
        mainMenuOptions = new MainMenuOptions(game);
        mainMenu.addActor(mainMenuOptions.getMainMenuOptionsTable());

        Gdx.input.setInputProcessor(mainMenu);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        mainMenu.act(delta);
        mainMenu.draw();
    }

    @Override
    public void resize(int width, int height) {
        mainMenu.getViewport().update(width, height, true);
    }

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