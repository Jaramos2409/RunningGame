package cs3340.project.runninggame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Creates the core instance of the game that contains all the game logic, creates the window, and
 * processes all the input of the game thus far.
 *
 * @author Jesus Ramos
 * @version 0.1
 * @since 11/24/2015
 */
public class GameScreen implements Screen, InputProcessor {
    final RunningGame game;

    private static final float UNIT_SCALE = 1/16f;
    private static final float RUNNING_FRAME_DURATION = 0.09f;
    private float TILEWIDTH;
    private float TILEHEIGHT;

    /**
     * The Start pos x.
     */
    static int START_POS_X = 39;
    /**
     * The Start pos y.
     */
    static int START_POS_Y = 34;

    /**
     * The Tiled map.
     */
    Level trackWorld;

    OrthogonalTiledMapRenderer renderer;
    /**
     * The Camera.
     */
    OrthographicCamera camera;
    Player player;

    private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
        @Override
        protected Rectangle newObject () {
            return new Rectangle();
        }
    };
    private Array<Rectangle> tiles = new Array<Rectangle>();

    /**
     *  Loads all the materials and initializes all the base features of the game
     *  such as the camera, the map, the player sprites, the input processor,
     *  and initial player position.
     */
    public GameScreen(final RunningGame gam) {
        this.game = gam;


        trackWorld = new Level("MyCrappyMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(trackWorld.getMap(),UNIT_SCALE);

        TILEWIDTH = trackWorld.getTileWidth() * UNIT_SCALE;
        TILEHEIGHT = trackWorld.getTileHeight() * UNIT_SCALE;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 16f, 16f);
        camera.position.x = START_POS_X;
        camera.position.y = START_POS_Y;
        camera.update();

        Gdx.input.setInputProcessor(this);

        player = new Player(UNIT_SCALE, START_POS_X, START_POS_Y);
    }

    /**
     * Draws all of the game onto the screen such as the tiled map and the player.
     * Also updates the camera after the player has moved.
     */
    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float deltaTime = Gdx.graphics.getDeltaTime();

        updatePlayer(deltaTime);

        camera.position.x = player.getPosition().x;
        camera.update();

        renderer.setView(camera);
        renderer.render();

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
     * player is triggering movement or not. Also shifts the playerState
     * of the player which is used in renderPlayer to trigger the respective animation.
     * @param deltaTime the time since the game last updated
     */
    private void updatePlayer (float deltaTime) {
        if (deltaTime == 0) return;
        player.setStateTime(player.getStateTime()+deltaTime);

        if (Math.abs(player.getVelocity().x) > player.getMaxVelocity()) {
            player.getVelocity().x = Math.signum(player.getVelocity().x) * player.getMaxVelocity();
        }

        if (Math.abs(player.getVelocity().x) < 1) {
            player.getVelocity().x = 0;
            player.playerState = Player.PlayerState.Standing;
        }

        player.getVelocity().scl(deltaTime);

        Rectangle playerRect = rectPool.obtain();
        playerRect.set(player.getPosition().x, player.getPosition().y + player.getHEIGHT()*0.1f, player.getWIDTH(), player.getHEIGHT());

        int startX, startY, endX, endY;
        if (player.getVelocity().x > 0) {
            startX = endX = (int)(player.getPosition().x + player.getWIDTH() + player.getVelocity().x);
        } else {
            startX = endX = (int)(player.getPosition().x + player.getVelocity().x);
        }

        startY = (int)(player.getPosition().y);
        endY = (int)(player.getPosition().y + player.getHEIGHT());
        getTiles(startX, startY, endX, endY, tiles);

        playerRect.x += player.getVelocity().x;

        for (Rectangle tile : tiles) {
            if (playerRect.overlaps(tile)) {
                if(player.getVelocity().x > 0){
                    player.getPosition().x = tile.x - TILEWIDTH - TILEWIDTH * 0.40f;
                }else if(player.getVelocity().x < 0){
                    player.getPosition().x = tile.x + TILEWIDTH + TILEWIDTH * 0.05f;
                }

                player.getVelocity().x = 0;
                break;
            }
        }
        playerRect.x = player.getPosition().x;

        player.getPosition().add(player.getVelocity());
        player.getVelocity().scl(1 / deltaTime);

        player.getVelocity().x *= player.getDAMPING();
    }

    /**
     * Triggers the proper animation on the screen depending on the playerState
     * in which the player is in.
     */
    private void renderPlayer () {
        TextureRegion frame = null;
        switch (player.playerState) {
            case Standing:
                frame = player.getStand().getKeyFrame(player.getStateTime());
                break;
            case Running:
                frame = player.getRun().getKeyFrame(player.getStateTime());
                break;
        }

        // draw the koala, depending on the current velocity
        // on the x-axis, draw the koala facing either right
        // or left
        Batch batch = renderer.getBatch();
        batch.begin();
        batch.draw(frame, player.getPosition().x, player.getPosition().y, player.getWIDTH(), player.getHEIGHT());
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
            player.setVelocity(new Vector2(player.getMaxVelocity(), 0));
            player.playerState = Player.PlayerState.Running;
        }
        return false;
    }

    private void getTiles (int startX, int startY, int endX, int endY, Array<Rectangle> tiles) {
        TiledMapTileLayer layer = (TiledMapTileLayer) trackWorld.getMap().getLayers().get("FinishLine");
        rectPool.freeAll(tiles);
        tiles.clear();
        for (int y = startY; y <= endY; y++) {
            for (int x = startX; x <= endX; x++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x, y);
                if (cell != null) {
                    Rectangle rect = rectPool.obtain();
                    rect.set(x, y, 1, 1);
                    tiles.add(rect);
                }
            }
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        trackWorld.dispose();
        player.dispose();
        renderer.dispose();
        this.dispose();
    }

}
