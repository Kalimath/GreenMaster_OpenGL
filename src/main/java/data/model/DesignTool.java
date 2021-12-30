package data.model;

import data.ui.grid.GardenGrid;
import data.ui.TileType;

public abstract class DesignTool {
    protected GardenGrid grid;
    protected TileType[] tileTypes;

    public DesignTool(GardenGrid grid) {
        this.grid = grid;
        setTileTypes();
    }

    protected abstract void update();
    protected abstract void set();

    private void setTileTypes(){
        tileTypes = TileType.values();
    }
}
