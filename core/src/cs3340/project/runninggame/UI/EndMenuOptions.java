package cs3340.project.runninggame.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import cs3340.project.runninggame.RunningGame;
import cs3340.project.runninggame.Screens.GameScreen;
import cs3340.project.runninggame.Screens.HighScoresScreen;
import cs3340.project.runninggame.Screens.MainMenuScreen;

/**
 * Sets up all the buttons, messages, and pushes them onto a table
 * for proper formatting.
 * @author Jesus Ramos
 * @version 0.1
 * @since 11/28/2015
 */
public class EndMenuOptions {
    final RunningGame game;
    long raceFinalTime;
    private Table endMenuOptionsTable;
    private Skin skin;
    private Label timeMsg;
    private TextButton playAgainBtn;
    private TextButton returnBtn;
    private TextButton highScoresBtn;
    private TextButton quitBtn;

    /**
     * Initializes all the materials needed for the menu options
     * @param gam the single RunningGame instance.
     * @param rFT the final race time.
     */
    public EndMenuOptions(RunningGame gam, long rFT) {
        this.game = gam;
        this.raceFinalTime = rFT;

        initSkin();

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
     * @return the endMenuOptionsTable
     */
    public Table getEndMenuOptionsTable() {
        return endMenuOptionsTable;
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
     * Initializes all the labels which will be used to render messages on the screen.
     */
    public void initLabels() {
        timeMsg = new Label("Final Time: " + ((double)raceFinalTime/1000.0), skin, "default");
        timeMsg.setAlignment(Align.center, Align.center);
        timeMsg.setFontScale(5);
    }

    /**
     * Initializes all buttons which will be used to give the player options to press
     * on the screen.
     */
    private void initButtons() {
        playAgainBtn = new TextButton("Play Again", skin, "default");
        playAgainBtn.setWidth(200f);
        playAgainBtn.setHeight(20f);
        playAgainBtn.getLabel().setFontScale(3);
        playAgainBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });

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

        highScoresBtn = new TextButton("Check High Scores", skin, "default");
        highScoresBtn.setWidth(200f);
        highScoresBtn.setHeight(20f);
        highScoresBtn.getLabel().setFontScale(3);
        highScoresBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new HighScoresScreen(game));
            }
        });

        quitBtn = new TextButton("Quit", skin, "default");
        quitBtn.setWidth(200f);
        quitBtn.setHeight(20f);
        quitBtn.getLabel().setFontScale(3);
        quitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    /**
     * Initializes the table that is used to format all the buttons and messages into a
     * neat table format for the page.
     */
    public void initTable() {
        endMenuOptionsTable = new Table();
        endMenuOptionsTable.setFillParent(true);

        endMenuOptionsTable.defaults().width(600).height(100);
        endMenuOptionsTable.add(timeMsg).expandX();
        endMenuOptionsTable.row().fill();
        endMenuOptionsTable.add().expandX();
        endMenuOptionsTable.row().fill();
        endMenuOptionsTable.add(playAgainBtn).expandX();
        endMenuOptionsTable.row().fill();
        endMenuOptionsTable.add(returnBtn).expandX();
        endMenuOptionsTable.row().fill();
        endMenuOptionsTable.add(highScoresBtn).expandX();
        endMenuOptionsTable.row().fill();
        endMenuOptionsTable.add(quitBtn).expandX();

        endMenuOptionsTable.setDebug(false);
        endMenuOptionsTable.setVisible(true);
    }
}
