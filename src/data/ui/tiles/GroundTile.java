package data.ui.tiles;

import data.ui.TileType;
import data.ui.tiles.ItemTile;

import static helpers.OpenGLAssistent.*;

public class GroundTile extends ItemTile {
    private TileType tileType;

    public GroundTile(float x, float y, float width, float height, TileType tileType) {
        super(x,y,width,height);
        setTexture(loadTexture(tileType.texturePath));
        setTileType(tileType);
    }

    public TileType getTileType() {
        return tileType;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }
}
