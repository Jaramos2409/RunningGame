package cs3340.project.runninggame.World;

/**
 * Created by EVA Unit 02 on 11/28/2015.
 */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * The type Player.
 */
public class Player {
    /**
     * The Width.
     */
    private static float WIDTH;
    /**
     * The Height.
     */
    private static float HEIGHT;
    /**
     * The Max velocity.
     */
    private static float MAX_VELOCITY = 10f;
    /**
     * The Damping.
     */
    private static float DAMPING = 0.87f;

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

    public void dispose() {
        runningGuyTexture.dispose();
    }

    public static float getWIDTH() {
        return WIDTH;
    }

    public static void setWIDTH(float WIDTH) {
        Player.WIDTH = WIDTH;
    }

    public static float getHEIGHT() {
        return HEIGHT;
    }

    public static void setHEIGHT(float HEIGHT) {
        Player.HEIGHT = HEIGHT;
    }

    public static float getMaxVelocity() {
        return MAX_VELOCITY;
    }

    public static void setMaxVelocity(float maxVelocity) {
        MAX_VELOCITY = maxVelocity;
    }

    public static float getDAMPING() {
        return DAMPING;
    }

    public static void setDAMPING(float DAMPING) {
        Player.DAMPING = DAMPING;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setPosition(int x, int y) {
        Vector2 newPosition = new Vector2(x,y);
        this.position = newPosition;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public float getStateTime() {
        return stateTime;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector2 acceleration) {
        this.acceleration = acceleration;
    }

    public void loadTextAnimations() {
        runningGuyTexture = new Texture("data/RunningGuy.png");
        regions = TextureRegion.split(runningGuyTexture,32,32)[0];
        stand = new Animation(0, regions[2]);
        run = new Animation(0.15f, regions[0], regions[1], regions[2]);
        run.setPlayMode(Animation.PlayMode.LOOP);
    }

    public Texture getRunningGuyTexture() {
        return runningGuyTexture;
    }

    public void setRunningGuyTexture(Texture runningGuyTexture) {
        this.runningGuyTexture = runningGuyTexture;
    }

    public TextureRegion[] getRegions() {
        return regions;
    }

    public void setRegions(TextureRegion[] regions) {
        this.regions = regions;
    }

    public Animation getStand() {
        return stand;
    }

    public void setStand(Animation stand) {
        this.stand = stand;
    }

    public Animation getRun() {
        return run;
    }

    public void setRun(Animation run) {
        this.run = run;
    }
}
