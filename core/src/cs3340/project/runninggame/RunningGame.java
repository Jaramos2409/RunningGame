package cs3340.project.runninggame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by EVA Unit 02 on 11/28/2015.
 */
public class RunningGame extends Game {

    public SpriteBatch batch;

    public void create() {
        batch = new SpriteBatch();
        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render(); //important!
    }

    public void dispose() {
        batch.dispose();
    }

}
