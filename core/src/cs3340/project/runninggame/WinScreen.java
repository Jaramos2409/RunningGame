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
    private TextButton button;


    public WinScreen () {
        winScreenTable = new Table();
        skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        winMessage = new Label("Congrats! You won!", skin, "default");

        button.setWidth(200f);
        button.setHeight(20f);
        button.setPosition(Gdx.graphics.getWidth() /2 - 100f, Gdx.graphics.getHeight()/2 - 10f);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                button.setText("You clicked the button");
            }
        });

        winScreenTable.add(winMessage);
        winScreenTable.row();
        winScreenTable.add(button);
    }

}
