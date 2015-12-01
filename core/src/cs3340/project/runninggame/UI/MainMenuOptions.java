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

/**
 * Creates and sets up the options that will be displayed on the main menu
 * of the game.
 * @author Jesus Ramos
 * @version 0.1
 * @since 11/28/2015
 */
public class MainMenuOptions {
    final RunningGame game;
    private Table mainMenuOptionsTable;
    private Label gameTitle;
    private Skin skin;
    private TextButton startBtn;
    private TextButton quitBtn;

    /**
     * Initializes all the materials needed for the menu options
     * @param gam the single RunningGame instance.
     */
    public MainMenuOptions (RunningGame gam) {
        this.game = gam;

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
     * @return the getMainMenuOptionsTable
     */
    public Table getMainMenuOptionsTable() {
        return mainMenuOptionsTable;
    }

    /**
     * Initializes all the labels which will be used to render messages on the screen.
     */
    public void initLabels() {
        gameTitle = new Label("Running Game", skin, "default");
        gameTitle.setAlignment(Align.center, Align.center);
        gameTitle.setFontScale(5);
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
     * Initializes all buttons which will be used to give the player options to press
     * on the screen.
     */
    private void initButtons() {
        startBtn = new TextButton("Start Game", skin, "default");
        startBtn.setWidth(200f);
        startBtn.setHeight(20f);
        startBtn.getLabel().setFontScale(3);
        startBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new cs3340.project.runninggame.Screens.GameScreen(game));
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
     * Initializes the table that is used to format the buttons and game title message into a
     * neat table format for the page.
     */
    public void initTable() {
        mainMenuOptionsTable = new Table();
        mainMenuOptionsTable.setFillParent(true);

        mainMenuOptionsTable.defaults().width(600).height(100);
        mainMenuOptionsTable.add(gameTitle).expandX().center();
        mainMenuOptionsTable.row().fill();
        mainMenuOptionsTable.add().expandX();
        mainMenuOptionsTable.row().fill();
        mainMenuOptionsTable.add(startBtn).expandX();
        mainMenuOptionsTable.row().fill();
        mainMenuOptionsTable.add(quitBtn).expandX();

        mainMenuOptionsTable.setDebug(false);
        mainMenuOptionsTable.setVisible(true);
    }
}
