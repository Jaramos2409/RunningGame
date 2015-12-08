package cs3340.project.runninggame.World;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

/**
 * Handles all of the input during the race portion of the game.
 * @author Jesus Ramos
 * @version 0.1
 * @since 12/08/2015
 */
public class WorldInput extends InputAdapter {
    Player player;

    public WorldInput(Player p) {
        player = p;
    }

    /**
     * Is triggered when the player presses down anywhere on th touch screen. Makes
     * the player move to the right on the screen.
     * @param screenX the screenX
     * @param screenY the screenY
     * @param pointer the pointer
     * @param button the button
     * @return false
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(button == Input.Buttons.LEFT){
            player.setVelocity(new Vector2(player.getMaxVelocity(), 0));
            player.setPlayerState(Player.PlayerState.Running);
        }
        return false;
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /*
     * @param player the player object that contains all the player related
     * attributes
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Disposes of the player object when the game ends.
     */
    public void dispose() {
        player.dispose();
    }
}
