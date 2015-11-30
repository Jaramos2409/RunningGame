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
public class Timer {
    private Label timer;
    private Label tapMsg;
    private long raceTime;
    private long startTime;
    private BitmapFont font;

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

    public void startTimer() {
        startTime = TimeUtils.millis();
    }

    public void updateTimer() {
        raceTime = TimeUtils.timeSinceMillis(startTime);

        if ((raceTime/1000) == 3) tapMsg.setText("");

        timer.setText("Time: " + ((double) raceTime / 1000.0));
    }

    public Label getTimer() {
        return timer;
    }

    public void setTimer(Label timer) {
        this.timer = timer;
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getRaceTime() {
        return raceTime;
    }

    public void setRaceTime(long raceTime) {
        this.raceTime = raceTime;
    }

    public Label getTapMsg() {
        return tapMsg;
    }

    public void setTapMsg(Label tapMsg) {
        this.tapMsg = tapMsg;
    }
}
