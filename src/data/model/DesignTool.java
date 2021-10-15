package data.model;

import data.ui.TileGrid;
import data.ui.TileType;

public abstract class DesignTool {
    protected TileGrid grid;
    protected TileType[] tileTypes;

    public DesignTool(TileGrid grid) {
        this.grid = grid;
        setTileTypes();
    }

    protected abstract void update();
    protected abstract void set();

    private void setTileTypes(){
        tileTypes = TileType.values();
    }
}
