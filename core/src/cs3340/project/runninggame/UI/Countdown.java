package cs3340.project.runninggame.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by EVA Unit 02 on 11/29/2015.
 */
public class Countdown {
    private Label countdown;
    private long counter;
    private long elapsedTime;
    private long startTime;
    private BitmapFont font;

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

    public void updateCountdown () {
        elapsedTime = TimeUtils.timeSinceMillis(startTime);
        if ((elapsedTime/1000) == 1) {
            startTime = TimeUtils.millis();
            counter--;
        }
        countdown.setText("Start in: " + counter);
    }

    public Label getCountdown() {
        return countdown;
    }

    public void setCountdown(Label countdown) {
        this.countdown = countdown;
    }

    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }
}
