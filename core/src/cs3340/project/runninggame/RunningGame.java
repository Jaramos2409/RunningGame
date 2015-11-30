package cs3340.project.runninggame;

import com.badlogic.gdx.Game;

/**
 * Created by EVA Unit 02 on 11/28/2015.
 */
public class RunningGame extends Game {

    public void create() {
        this.setScreen(new cs3340.project.runninggame.Screens.MainMenuScreen(this));
    }

    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
    }
}
