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
 * Created by EVA Unit 02 on 11/28/2015.
 */
public class MainMenuOptions {
    final RunningGame game;
    private Table mainMenuOptionsTable;
    private Label gameTitle;
    private Skin skin;
    private TextButton startBtn;
    private TextButton quitBtn;

    public MainMenuOptions (RunningGame gam) {
        this.game = gam;

        initSkin();

        initLabels();

        initButtons();

        initTable();
    }

    public RunningGame getGame() {
        return game;
    }

    public Table getMainMenuOptionsTable() {
        return mainMenuOptionsTable;
    }

    public void setMainMenuOptionsTable(Table mainMenuOptionsTable) {
        this.mainMenuOptionsTable = mainMenuOptionsTable;
    }

    public Label getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(Label gameTitle) {
        this.gameTitle = gameTitle;
    }

    public Skin getSkin() {
        return skin;
    }


    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public void initLabels() {
        gameTitle = new Label("Running Game", skin, "default");
        gameTitle.setAlignment(Align.center, Align.center);
        gameTitle.setFontScale(5);
    }

    public void initSkin () {
        skin = new Skin(Gdx.files.internal("ui/uiskin.json")
                ,new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas")));
    }

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
