package cs3340.project.runninggame.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * The timer used to keep track of the player's time running the track.
 * @author Jesus Ramos
 * @version 0.1
 * @since 11/29/2015
 */
public class Timer {
    private Label timer;
    private Label tapMsg;
    private long raceTime;
    private long startTime;
    private BitmapFont font;

    /**
     * Sets up the timer and labels that will display the ongoing time and the brief
     * message that tells the player to tap the screen in order to run.
     */
    public Timer () {
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        startTime = 0;
        raceTime = 0;

        timer = new Label("Time: " + raceTime, new Label.LabelStyle(font, Color.WHITE));
        timer.setFontScale(5);
        timer.setPosition(10, Gdx.graphics.getHeight() * 0.9f);

        tapMsg = new Label("Tap the screen to run!",  new Label.LabelStyle(font, Color.WHITE));
        tapMsg.setFontScale(3);
        tapMsg.setPosition(200, Gdx.graphics.getHeight() * 0.2f, Align.bottom);
    }

    /**
     * Starts the timer.
     */
    public void startTimer() {
        startTime = TimeUtils.millis();
    }

    /**
     * Updates the timer and the timer label. Sets the "Tap the screen to run!" to nothing when three seconds pass.
     */
    public void updateTimer() {
        raceTime = TimeUtils.timeSinceMillis(startTime);

        if ((raceTime/1000) == 3) tapMsg.setText("");

        timer.setText("Time: " + ((double) raceTime / 1000.0));
    }

    /**
     * @return the timer label
     */
    public Label getTimer() {
        return timer;
    }

    /**
     * @return the current race time.
     */
    public long getRaceTime() {
        return raceTime;
    }

    /**
     * @return the "Tap the screen!" message.
     */
    public Label getTapMsg() {
        return tapMsg;
    }

}
