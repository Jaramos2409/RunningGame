package cs3340.project.runninggame.World;

import com.badlogic.gdx.Game;

/**
 * Keeps track of the state the game is currently in.
 * @author Jesus Ramos
 * @version 0.1
 * @since 11/28/2015
 */
public class GameState {
    /**
     * Two States:
     * Prerace: The state where the game counts down to the beginning of the race.
     * Race: The state in which the race has started and the player must make it to the finish line.
     */
    public enum CurGameState {
        Prerace,
        Race
    }
    private CurGameState curGameState;

    /**
     * Sets the game state to Prerace by default.
     */
    public GameState() {
        curGameState = CurGameState.Prerace;
    }

    /**
     * @return the current GameState
     */
    public CurGameState getCurGameState() {
        return curGameState;
    }

    /**
     * Sets the current game state to whatever curGameState is.
     * @param curGameState the new state of the game.
     */
    public void setCurGameState(CurGameState curGameState) {
        this.curGameState = curGameState;
    }
}
