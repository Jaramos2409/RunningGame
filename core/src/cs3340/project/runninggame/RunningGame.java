package cs3340.project.runninggame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Creates the core instance of the game that contains all the game logic, creates the window, and
 * processes all the input of the game thus far.
 *
 * @author Jesus Ramos
 * @version 0.1
 * @since 11/24/2015
 */
public class RunningGame extends ApplicationAdapter implements InputProcessor {
    /**
     * The Start pos x.
     */
    static int START_POS_X = 39;
    /**
     * The Start pos y.
     */
    static int START_POS_Y = 34;

    /**
     * The type Player.
     */
    static class Player {
        /**
         * The Width.
         */
        static float WIDTH;
        /**
         * The Height.
         */
        static float HEIGHT;
        /**
         * The Max velocity.
         */
        static float MAX_VELOCITY = 10f;
        /**
         * The Damping.
         */
        static float DAMPING = 0.87f;

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
        final Vector2 position = new Vector2();
        /**
         * The Velocity.
         */
        final Vector2 velocity = new Vector2();
        /**
         * The State.
         */
        State state = State.Standing;
        /**
         * The State time.
         */
        float stateTime = 0;
    }

    /**
     * The Tiled map.
     */
    TiledMap tiledMap;
    /**
     * The Camera.
     */
    OrthographicCamera camera;
    /**
     * The Running guy texture.
     */
    Texture runningGuyTexture;
    /**
     * The Tiled map renderer.
     */
    OrthogonalTiledMapRenderer tiledMapRenderer;
    /**
     * The Stand.
     */
    Animation stand;
    /**
     * The Run.
     */
    Animation run;
    /**
     * The Player.
     */
    Player player;

    /**
     *  Loads all the materials and initializes all the base features of the game
     *  such as the camera, the map, the player sprites, the input processor,
     *  and initial player position.
     */
    @Override
    public void create () {
        runningGuyTexture = new Texture("data/RunningGuy.png");
        TextureRegion[] regions = TextureRegion.split(runningGuyTexture,32,32)[0];
        stand = new Animation(0, regions[2]);
        run = new Animation(0.15f, regions[0], regions[1], regions[2]);
        run.setPlayMode(Animation.PlayMode.LOOP);

        Player.WIDTH = 1/16f * regions[0].getRegionWidth();
        Player.HEIGHT = 1/16f * regions[0].getRegionHeight();

        tiledMap = new TmxMapLoader().load("MyCrappyMap.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap,1/16f);

        camera = new OrthographicCamera();
        camera.setToOrtho(false,16,16);
        camera.update();

        Gdx.input.setInputProcessor(this);

        player = new Player();
        player.position.set(START_POS_X,START_POS_Y);
    }

    /**
     * Draws all of the game onto the screen such as the tiled map and the player.
     * Also updates the camera after the player has moved.
     */
    @Override public void render () {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float deltaTime = Gdx.graphics.getDeltaTime();

        updatePlayer(deltaTime);

        camera.position.x = player.position.x;
        camera.position.y = player.position.y;
        camera.update();

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        renderPlayer();
    }

    /**
     *
     * @param keycode the keycode
     * @return false
     */
    @Override public boolean keyDown(int keycode) {
        return false;
    }

    /**
     *
     * @param keycode the keycode
     * @return false
     */
    @Override public boolean keyUp(int keycode) {
        return false;
    }

    /**
     *
     * @param character the character
     * @return false
     */
    @Override public boolean keyTyped(char character) {

        return false;
    }

    /**
     *
     * @param screenX the screenX
     * @param screenY the screenY
     * @param pointer the pointer
     * @param button the button
     * @return false
     */
    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    /**
     *
     * @param screenX the screenX
     * @param screenY the screenY
     * @param pointer the pointer
     * @return false
     */
    @Override public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    /**
     *
     * @param screenX the screenX
     * @param screenY the screenY
     * @return false
     */
    @Override public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    /**
     *
     * @param amount the amount
     * @return false
     */
    @Override public boolean scrolled(int amount) {
        return false;
    }

    /**
     * Updates the players velocity depending on whether the
     * player is triggering movement or not. Also shifts the state
     * of the player which is used in renderPlayer to trigger the respective animation.
     * @param deltaTime the time since the game last updated
     */
    private void updatePlayer (float deltaTime) {
        if (deltaTime == 0) return;
        player.stateTime += deltaTime;

        // clamp the velocity to the maximum, x-axis only
        if (Math.abs(player.velocity.x) > Player.MAX_VELOCITY) {
            player.velocity.x = Math.signum(player.velocity.x) * Player.MAX_VELOCITY;
        }

        // clamp the velocity to 0 if it's < 1, and set the state to standing
        if (Math.abs(player.velocity.x) < 1) {
            player.velocity.x = 0;
            player.state = Player.State.Standing;
        }

        // multiply by delta time so we know how far we go
        // in this frame
        player.velocity.scl(deltaTime);

        // unscale the velocity by the inverse delta time and set
        // the latest position
        player.position.add(player.velocity);
        player.velocity.scl(1 / deltaTime);

        // Apply damping to the velocity on the x-axis so we don't
        // walk infinitely once a key was pressed
        player.velocity.x *= Player.DAMPING;
    }

    /**
     * Triggers the proper animation on the screen depending on the state
     * in which the player is in.
     */
    private void renderPlayer () {
        // based on the koala state, get the animation frame
        TextureRegion frame = null;
        switch (player.state) {
            case Standing:
                frame = stand.getKeyFrame(player.stateTime);
                break;
            case Running:
                frame = run.getKeyFrame(player.stateTime);
                break;
        }

        // draw the koala, depending on the current velocity
        // on the x-axis, draw the koala facing either right
        // or left
        Batch batch = tiledMapRenderer.getBatch();
        batch.begin();
        batch.draw(frame, player.position.x, player.position.y, Player.WIDTH, Player.HEIGHT);
        batch.end();
    }

    /**
     * Is triggered when the player presses down anywhere on th touch screen. Makes
     * the player move to the left on the screen.
     * @param screenX the screenX
     * @param screenY the screenY
     * @param pointer the pointer
     * @param button the button
     * @return false
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(button == Input.Buttons.LEFT){
            player.velocity.x = Player.MAX_VELOCITY;
            player.state = Player.State.Running;
        }
        return false;
    }
}

