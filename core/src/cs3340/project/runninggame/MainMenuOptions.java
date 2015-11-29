package cs3340.project.runninggame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

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
        mainMenuOptionsTable = new Table();
        mainMenuOptionsTable.setFillParent(true);
        skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        gameTitle = new Label("Running Game", skin, "default");

        startBtn = new TextButton("Start Game", skin, "default");
        startBtn.setWidth(200f);
        startBtn.setHeight(20f);
        startBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });

        quitBtn = new TextButton("Quit", skin, "default");
        quitBtn.setWidth(200f);
        quitBtn.setHeight(20f);
        quitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        mainMenuOptionsTable.defaults().width(400).height(50);
        mainMenuOptionsTable.add(gameTitle).expandX().center();
        mainMenuOptionsTable.row().fill();
        mainMenuOptionsTable.add(startBtn).expandX();
        mainMenuOptionsTable.row().fill();
        mainMenuOptionsTable.add(quitBtn).expandX();

        mainMenuOptionsTable.setDebug(true);
        mainMenuOptionsTable.setVisible(true);
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
}
