package cs3340.project.runninggame.UI;

/**
 * Created by EVA Unit 02 on 11/30/2015.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;
import java.util.Collections;

import cs3340.project.runninggame.Data.DateTimePair;
import cs3340.project.runninggame.RunningGame;
import cs3340.project.runninggame.Screens.MainMenuScreen;

/**
 * Creates and sets up the scrollpane, time list, and main menu button
 * that is displayed on the high score screen
 * @author Jesus Ramos
 * @version 0.2
 * @since 11/30/2015
 */
public class HighScoresOptions {
    final RunningGame game;
    private Table highScoresOptionsTable;
    private Skin skin;
    List<DateTimePair> timeList;
    ScrollPane scrollPane;
    Label scoreMsg;
    TextButton returnBtn;

    /**
     * Initializes all the materials needed for the menu options
     * @param gam the single RunningGame instance.
     */
    public HighScoresOptions(RunningGame gam) {
        this.game = gam;

        initSkin();

        initList();

        initScroll();

        initLabels();

        initButtons();

        initTable();
    }

    /**
     * @return the single RunningGame Instance
     */
    public RunningGame getGame() {
        return game;
    }

    /**
     * Loads the skin style that will be used to style the buttons and messages
     * to be rendered on the screen.
     */
    public void initSkin () {
        skin = new Skin(Gdx.files.internal("ui/uiskin.json")
                ,new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas")));
    }

    /**
     * Initializes the List that contains all the high scores sorted in
     * ascended order.
     */
    public void initList() {
        timeList = new List<DateTimePair>(skin, "default");
        ArrayList<DateTimePair> timesList = game.timeDatabase.loadTimes();
        Collections.sort(timesList);

        timeList.setItems(timesList.toArray(new DateTimePair[timesList.size()]));
        timeList.getStyle().font.getData().setScale(2);
    }

    /**
     * Initializes the scrollPane that contains the high score list.
     */
    public void initScroll() {
        scrollPane = new ScrollPane(timeList, skin);
    }

    /**
     * Initializes all the labels which will be used to render messages on the screen.
     */
    public void initLabels() {
        scoreMsg = new Label("High Scores: ", skin, "default");
        scoreMsg.setFontScale(4);
    }

    /**
     * Initializes all buttons which will be used to give the player options to press
     * on the screen.
     */
    private void initButtons() {
        returnBtn = new TextButton("Return to Main Menu", skin, "default");
        returnBtn.setWidth(200f);
        returnBtn.setHeight(20f);
        returnBtn.getLabel().setFontScale(3);
        returnBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
    }

    /**
     * Initializes the table that is used to format the buttons, message, scrollpane and list into a
     * neat table format for the page.
     */
    public void initTable() {
        highScoresOptionsTable = new Table();
        highScoresOptionsTable.setFillParent(true);

        highScoresOptionsTable.defaults().width(500).height(100);
        highScoresOptionsTable.add(scoreMsg).expandX();
        highScoresOptionsTable.row().fill();
        highScoresOptionsTable.add(scrollPane).height(500f);
        highScoresOptionsTable.row().fill();
        highScoresOptionsTable.add(returnBtn).expandX();

        highScoresOptionsTable.setDebug(false);
        highScoresOptionsTable.setVisible(true);
    }

    /**
     * @return the getHighScoresOptionsTable
     */
    public Table getHighScoresOptionsTable() {
        return highScoresOptionsTable;
    }
}

