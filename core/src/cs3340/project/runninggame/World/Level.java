package cs3340.project.runninggame.World;

/**
 * Created by EVA Unit 02 on 11/28/2015.
 */
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class Level {

    private TiledMap map;
    private float tileWidth;
    private float tileHeight;

    public Level(String tilemapName){

        map = new TmxMapLoader().load(tilemapName);
        TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(0);
        tileWidth = layer.getTileWidth();
        tileHeight = layer.getTileHeight();
    }

    public void dispose() {
        map.dispose();
    }

    public TiledMap getMap() {
        return map;
    }

    public float getTileHeight() {
        return tileHeight;
    }

    public float getTileWidth() {
        return tileWidth;
    }
}
