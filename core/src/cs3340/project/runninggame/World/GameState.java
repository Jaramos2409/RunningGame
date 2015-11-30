package cs3340.project.runninggame.World;

import com.badlogic.gdx.Game;

/**
 * Created by EVA Unit 02 on 11/29/2015.
 */
public class GameState {
    public enum CurGameState {
        Prerace,
        Race
    }
    private CurGameState curGameState;

    public GameState() {
        curGameState = CurGameState.Prerace;
    }

    public CurGameState getCurGameState() {
        return curGameState;
    }

    public void setCurGameState(CurGameState curGameState) {
        this.curGameState = curGameState;
    }
}
