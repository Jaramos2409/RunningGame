package cs3340.project.runninggame.World;

/**
 * Created by EVA Unit 02 on 11/28/2015.
 */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Keeps track of the player's size, velocity, and player state
 * while also handling the loading of it's texture and animations.
 * @author Jesus Ramos
 * @version 0.1
 * @since 11/28/2015
 */
public class Player {
    /**
     * The Width.
     */
    private static float WIDTH;
    /**
     * The Height.
     */
    private float HEIGHT;
    /**
     * The Max velocity.
     */
    private float MAX_VELOCITY = 10f;
    /**
     * The Damping.
     */
    private  float DAMPING = 0.87f;

    /**
     * The enum PlayerState.
     */
     public enum PlayerState {
        /**
         * Standing playerState.
         */
        Standing, /**
         * Running playerState.
         */
        Running
    }

    /**
     * The Position.
     */
    private Vector2 position;
    /**
     * The Velocity.
     */
    private Vector2 velocity;
    /**
     * The PlayerState.
     */
    private PlayerState playerState;
    /**
     * The PlayerState time.
     */
    private float stateTime = 0;

    private Vector2 acceleration;

    /**
     * The Running guy texture.
     */
    Texture runningGuyTexture;
    TextureRegion[] regions;
    /**
     * The Stand.
     */
    Animation stand;
    /**
     * The Run.
     */
    Animation run;

    /**
     * Creates a player by loading all it's texture/animations, setting it's position,
     * default velocity, acceleration, playerstate, stateTime, and size.
     * @param UNIT_SCALE the scale of the player within the world i.e. 16x16 for this game.
     * @param x the x coordinate position of the player on the map.
     * @param y the y coordinate position of the player on the map
     */
    public Player(float UNIT_SCALE, int x, int y){
        loadTextAnimations();
        position = new Vector2(x, y);
        velocity = new Vector2();
        acceleration = new Vector2();
        playerState = PlayerState.Standing;
        stateTime = 0;
        WIDTH = UNIT_SCALE * regions[0].getRegionWidth();
        HEIGHT = UNIT_SCALE * regions[0].getRegionHeight();
    }

    /**
     * Disposes of the player's texture.
     */
    public void dispose() {
        runningGuyTexture.dispose();
    }

    /**
     * @return the player width
     */
    public float getWIDTH() {
        return WIDTH;
    }

    /**
     * @return the player height
     */
    public  float getHEIGHT() {
        return HEIGHT;
    }

    /**
     * @return the player's max velocity
     */
    public float getMaxVelocity() {
        return MAX_VELOCITY;
    }

    /**
     * @return the velocity damping
     */
    public float getDAMPING() {
        return DAMPING;
    }

    /**
     * @return the player's coordinate position
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * @return the player's velocity
     */
    public Vector2 getVelocity() {
        return velocity;
    }

    /**
     * Sets the player's currently velocity
     * @param velocity the new velocity
     */
    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    /**
     * @return the current player state
     */
    public PlayerState getPlayerState() {
        return playerState;
    }

    /**
     * Sets the current player state
     * @param playerState the new player state
     */
    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    /**
     * @return the current state time
     */
    public float getStateTime() {
        return stateTime;
    }

    /**
     * Sets the current state time
     * @param stateTime the new state time
     */
    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

    /**
     * Loads the player's texture sheet and splits it up into the
     * two basic animations for the player in the game.
     */
    public void loadTextAnimations() {
        runningGuyTexture = new Texture("data/RunningGuy.png");
        regions = TextureRegion.split(runningGuyTexture,32,32)[0];
        stand = new Animation(0, regions[2]);
        run = new Animation(0.15f, regions[0], regions[1], regions[2]);
        run.setPlayMode(Animation.PlayMode.LOOP);
    }

    /**
     * @return the stand animation
     */
    public Animation getStand() {
        return stand;
    }

    /**
     * @return the run animation
     */
    public Animation getRun() {
        return run;
    }
}
