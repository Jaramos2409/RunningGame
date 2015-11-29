package cs3340.project.runninggame;

/**
 * Created by EVA Unit 02 on 11/28/2015.
 */

import com.badlogic.gdx.math.Vector2;

/**
 * The type Player.
 */
class Player {
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
     * The enum State.
     */
    enum State {
        /**
         * Standing state.
         */
        Standing, /**
         * Running state.
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
     * The State.
     */
    State state = State.Standing;
    /**
     * The State time.
     */
    private float stateTime = 0;

    private Vector2 acceleration;

    public Player(){
        position = new Vector2();
        velocity = new Vector2();
        acceleration = new Vector2();
        state = State.Standing;
        stateTime = 0;
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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
}
