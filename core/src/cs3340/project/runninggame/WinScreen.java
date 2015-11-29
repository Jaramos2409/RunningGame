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
public class WinScreen {
    private Table winScreenTable;
    private Label winMessage;
    private Skin skin;
    private TextButton returnBtn;
    private TextButton highScoresBtn;
    private TextButton quitBtn;


    public WinScreen () {
        winScreenTable = new Table();
        winScreenTable.setFillParent(true);
        skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        winMessage = new Label("Congrats! You won!", skin, "default");

        returnBtn = new TextButton("Return to Main Menu", skin, "default");
        returnBtn.setWidth(200f);
        returnBtn.setHeight(20f);
        returnBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                returnBtn.setText("You clicked the button");
            }
        });

        highScoresBtn = new TextButton("Check High Scores", skin, "default");
        highScoresBtn.setWidth(200f);
        highScoresBtn.setHeight(20f);
        highScoresBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                highScoresBtn.setText("You clicked the button");
            }
        });

        quitBtn = new TextButton("Quit", skin, "default");
        quitBtn.setWidth(200f);
        quitBtn.setHeight(20f);
        quitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                quitBtn.setText("You clicked the button");
            }
        });

        winScreenTable.add(winMessage);
        winScreenTable.row();
        winScreenTable.add(returnBtn);
        winScreenTable.row();
        winScreenTable.add(highScoresBtn);
        winScreenTable.row();
        winScreenTable.add(quitBtn);

        winScreenTable.setDebug(true);
        winScreenTable.setVisible(false);
    }

    public Table getWinScreenTable() {
        return winScreenTable;
    }

    public void setWinScreenTable(Table winScreenTable) {
        this.winScreenTable = winScreenTable;
    }

    public Label getWinMessage() {
        return winMessage;
    }

    public void setWinMessage(Label winMessage) {
        this.winMessage = winMessage;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public TextButton getReturnBtn() {
        return returnBtn;
    }

    public void setReturnBtn(TextButton returnBtn) {
        this.returnBtn = returnBtn;
    }

    public TextButton getHighScoresBtn() {
        return highScoresBtn;
    }

    public void setHighScoresBtn(TextButton highScoresBtn) {
        this.highScoresBtn = highScoresBtn;
    }

    public TextButton getQuitBtn() {
        return quitBtn;
    }

    public void setQuitBtn(TextButton quitBtn) {
        this.quitBtn = quitBtn;
    }
}
