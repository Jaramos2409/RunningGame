package cs3340.project.runninggame.World;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * Loads the tile map and records it's width and height.
 * @author Jesus Ramos
 * @version 0.1
 * @since 11/28/2015
 */
public class Level {
    private TiledMap map;
    private float tileWidth;
    private float tileHeight;

    /**
     * Takes in a filename of the tile map .tmx and loads it
     * @param tilemapName the filename of the tile map .tmx
     */
    public Level(String tilemapName){
        map = new TmxMapLoader().load(tilemapName);
        TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(0);
        tileWidth = layer.getTileWidth();
        tileHeight = layer.getTileHeight();
    }

    /**
     * Disposes of the map
     */
    public void dispose() {
        map.dispose();
    }

    /**
     * @return the world map
     */
    public TiledMap getMap() {
        return map;
    }

}
