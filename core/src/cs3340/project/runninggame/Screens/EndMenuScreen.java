package cs3340.project.runninggame.Screens;

/**
 * Created by EVA Unit 02 on 11/29/2015.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import cs3340.project.runninggame.RunningGame;
import cs3340.project.runninggame.UI.EndMenuOptions;

public class EndMenuScreen implements Screen {

    final RunningGame game;

    long raceFinalTime;
    Stage endMenu;
    EndMenuOptions endMenuOptions;

    OrthographicCamera camera;

    public EndMenuScreen(final RunningGame gam, long raceTime) {
        game = gam;
        raceFinalTime = raceTime;

        camera = new OrthographicCamera();
        //camera.setToOrtho(false, 800, 480);

        endMenu = new Stage(new StretchViewport(720, 1280, camera));
        endMenuOptions = new EndMenuOptions(game, raceFinalTime);
        endMenu.addActor(endMenuOptions.getEndMenuOptionsTable());

        Gdx.input.setInputProcessor(endMenu);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        endMenu.act(delta);
        endMenu.draw();
    }

    @Override
    public void resize(int width, int height) {
        endMenu.getViewport().update(width, height, true);
    }

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