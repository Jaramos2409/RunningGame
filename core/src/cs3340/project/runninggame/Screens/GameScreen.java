package cs3340.project.runninggame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

import cs3340.project.runninggame.UI.Countdown;
import cs3340.project.runninggame.UI.Timer;
import cs3340.project.runninggame.World.GameMusic;
import cs3340.project.runninggame.World.GameState;
import cs3340.project.runninggame.World.Level;
import cs3340.project.runninggame.World.Player;
import cs3340.project.runninggame.RunningGame;
import cs3340.project.runninggame.World.WorldInput;

/**
 * Creates the core instance of the game that contains all the game logic and creates the main game window.
 * @author Jesus Ramos
 * @version 0.3
 * @since 11/28/2015
 */
public class GameScreen implements Screen {
    /**
     * The Game.
     */
    final RunningGame game;
    private GameState gameState;
    private GameMusic gameMusic;
    private Stage timerOnScreen;
    private Stage countdownOnScreen;
    private static final float UNIT_SCALE = 1/16f;
    /**
     * The Start pos x.
     */
    static int START_POS_X = 8;
    /**
     * The Start pos y.
     */
    static int START_POS_Y = 15;
    /**
     * The Timer.
     */
    Timer timer;
    /**
     * The Countdown.
     */
    Countdown countdown;
    /**
     * The Track world.
     */
    Level trackWorld;
    /**
     * The Renderer.
     */
    OrthogonalTiledMapRenderer renderer;
    /**
     * The Camera.
     */
    OrthographicCamera camera;
    private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
        @Override
        protected Rectangle newObject () {
            return new Rectangle();
        }
    };
    private Array<Rectangle> tiles = new Array<Rectangle>();

    /**
     * Handles all the input processing for the game
     */
    WorldInput worldInput;

    /**
     * Loads all the materials and initializes all the base features of the game
     * such as the camera, the map, the player sprites, the input processor,
     * and initial player position.
     *
     * @param gam the single RunningGame instance
     */
    public GameScreen(final RunningGame gam) {
        this.game = gam;

        gameMusic = new GameMusic();

        gameState = new GameState();

        countdownOnScreen = new Stage();
        timerOnScreen = new Stage();

        timer = new Timer();
        countdown = new Countdown();
        countdownOnScreen.addActor(countdown.getCountdown());
        timerOnScreen.addActor(timer.getTimer());
        timerOnScreen.addActor(timer.getTapMsg());

        trackWorld = new Level("RaceTrack_v2.tmx");
        renderer = new OrthogonalTiledMapRenderer(trackWorld.getMap(),UNIT_SCALE);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 16f, 16f);
        camera.position.x = START_POS_X;
        camera.position.y = START_POS_Y;
        camera.update();

        worldInput = new WorldInput(new Player(UNIT_SCALE, START_POS_X, START_POS_Y));
        Gdx.input.setInputProcessor(worldInput);
    }

    /**
     * Draws all of the game onto the screen such as the tiled map and the player.
     * Sets the games pre-race and race states whe necessary.
     * Also updates the camera after the player has moved.
     * @param delta the time since the last time the game updated
     */
    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(0, 1, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        switch(gameState.getCurGameState()) {
            case Prerace:
                countdown.updateCountdown();
                camera.position.x = worldInput.getPlayer().getPosition().x;
                camera.update();

                renderer.setView(camera);
                renderer.render();

                renderPlayer();

                countdownOnScreen.act(delta);
                countdownOnScreen.draw();

                if(countdown.getCounter() == 0) {
                    gameState.setCurGameState(GameState.CurGameState.Race);
                    timer.startTimer();
                    countdownOnScreen.dispose();
                    gameMusic.musicPlay();
                }
                break;
            case Race:
                timer.updateTimer();

                updatePlayer(delta);

                camera.position.x = worldInput.getPlayer().getPosition().x;
                camera.update();

                renderer.setView(camera);
                renderer.render();

                renderPlayer();

                timerOnScreen.act(delta);
                timerOnScreen.draw();
                break;
        }

    }

    /**
     * Updates the players velocity depending on whether the
     * player is triggering movement or not. Shifts the playerState
     * of the player which is used in renderPlayer to trigger the respective animation.
     * Also keeps track of whether the player has reached the finish line.
     * @param deltaTime the time since the game last updated
     */
    private void updatePlayer (float deltaTime) {
        if (deltaTime == 0) return;
        worldInput.getPlayer().setStateTime(worldInput.getPlayer().getStateTime() + deltaTime);

        if (Math.abs(worldInput.getPlayer().getVelocity().x) > worldInput.getPlayer().getMaxVelocity()) {
            worldInput.getPlayer().getVelocity().x = Math.signum(worldInput.getPlayer().getVelocity().x) * worldInput.getPlayer().getMaxVelocity();
        }

        if (Math.abs(worldInput.getPlayer().getVelocity().x) < 1) {
            worldInput.getPlayer().getVelocity().x = 0;
            worldInput.getPlayer().setPlayerState(Player.PlayerState.Standing);
        }

        worldInput.getPlayer().getVelocity().scl(deltaTime);

        Rectangle playerRect = rectPool.obtain();
        playerRect.set(worldInput.getPlayer().getPosition().x
                , worldInput.getPlayer().getPosition().y + worldInput.getPlayer().getHEIGHT()*0.1f
                , worldInput.getPlayer().getWIDTH(), worldInput.getPlayer().getHEIGHT());

        int startX, startY, endX, endY;
        if (worldInput.getPlayer().getVelocity().x > 0) {
            startX = endX = (int)(worldInput.getPlayer().getPosition().x + worldInput.getPlayer().getWIDTH() + worldInput.getPlayer().getVelocity().x);
        } else {
            startX = endX = (int)(worldInput.getPlayer().getPosition().x + worldInput.getPlayer().getVelocity().x);
        }

        startY = (int)(worldInput.getPlayer().getPosition().y);
        endY = (int)(worldInput.getPlayer().getPosition().y + worldInput.getPlayer().getHEIGHT());
        getTiles(startX, startY, endX, endY, tiles);

        playerRect.x += worldInput.getPlayer().getVelocity().x;

        for (Rectangle tile : tiles) {
            if (playerRect.overlaps(tile)) {
                gameMusic.musicStop();
                game.setScreen(new EndMenuScreen(game, timer.getRaceTime()));
                break;
            }
        }
        playerRect.x = worldInput.getPlayer().getPosition().x;

        worldInput.getPlayer().getPosition().add(worldInput.getPlayer().getVelocity());
        worldInput.getPlayer().getVelocity().scl(1 / deltaTime);

        worldInput.getPlayer().getVelocity().x *= worldInput.getPlayer().getDAMPING();
    }

    /**
     * Triggers the proper animation on the screen depending on the playerState
     * in which the player is in.
     */
    private void renderPlayer () {
        TextureRegion frame = null;
        switch (worldInput.getPlayer().getPlayerState()) {
            case Standing:
                frame = worldInput.getPlayer().getStand().getKeyFrame(worldInput.getPlayer().getStateTime());
                break;
            case Running:
                frame = worldInput.getPlayer().getRun().getKeyFrame(worldInput.getPlayer().getStateTime());
                break;
        }

        Batch batch = renderer.getBatch();
        batch.begin();
        batch.draw(frame, worldInput.getPlayer().getPosition().x, worldInput.getPlayer().getPosition().y, worldInput.getPlayer().getWIDTH(), worldInput.getPlayer().getHEIGHT());
        batch.end();
    }

    /**
     * Retrieves all the nearby tiles from the player and inserts them into the Rectangle Pool.
     * @param startX the startX
     * @param startY the startY
     * @param endX the endX
     * @param endY the endY
     * @param tiles the tiles
     */
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
        worldInput.dispose();
        renderer.dispose();
        timerOnScreen.dispose();
        countdownOnScreen.dispose();
    }

}
