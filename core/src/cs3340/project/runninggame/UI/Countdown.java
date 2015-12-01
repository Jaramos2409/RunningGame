package cs3340.project.runninggame.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * The countdown timer at the beginning of the game.
 * @author Jesus Ramos
 * @version 0.1
 * @since 11/29/2015
 */
public class Countdown {
    private Label countdown;
    private long counter;
    private long elapsedTime;
    private long startTime;
    private BitmapFont font;

    /**
     * Creates the countdown timer by setting the countdown to 3, and creates
     * the label that will contain the message that shows at the beginning
     * of the game.
     */
    public Countdown () {
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        counter = 3;
        startTime = TimeUtils.millis();
        elapsedTime = 0;

        countdown = new Label("Start In: " + counter, new Label.LabelStyle(font, Color.WHITE));
        countdown.setFontScale(5);
        countdown.setPosition(10, Gdx.graphics.getHeight() * 0.9f);
    }

    /**
     * Updates the counter by subtracting it second by second.
     * It updates the label to display the current count respectively.
     */
    public void updateCountdown () {
        elapsedTime = TimeUtils.timeSinceMillis(startTime);
        if ((elapsedTime/1000) == 1) {
            startTime = TimeUtils.millis();
            counter--;
        }
        countdown.setText("Start in: " + counter);
    }

    /**
     * @return the countdown label
     */
    public Label getCountdown() {
        return countdown;
    }

    public long getCounter() {
        return counter;
    }

}
