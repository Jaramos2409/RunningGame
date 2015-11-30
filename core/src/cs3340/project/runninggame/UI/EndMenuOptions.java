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
import cs3340.project.runninggame.Screens.MainMenuScreen;

/**
 * Created by EVA Unit 02 on 11/28/2015.
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

    public EndMenuOptions(RunningGame gam, long rFT) {
        this.game = gam;
        this.raceFinalTime = rFT;

        initSkin();

        initLabels();

        initButtons();

        initTable();
    }

    public RunningGame getGame() {
        return game;
    }

    public Table getEndMenuOptionsTable() {
        return endMenuOptionsTable;
    }

    public void setEndMenuOptionsTable(Table endMenuOptionsTable) {
        this.endMenuOptionsTable = endMenuOptionsTable;
    }

    public Skin getSkin() {
        return skin;
    }


    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public void initSkin () {
        skin = new Skin(Gdx.files.internal("ui/uiskin.json")
                ,new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas")));
    }

    public void initLabels() {
        timeMsg = new Label("Final Time: " + ((double)raceFinalTime/1000.0), skin, "default");
        timeMsg.setAlignment(Align.center, Align.center);
        timeMsg.setFontScale(5);
    }

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
                highScoresBtn.setText("You clicked the button");
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
