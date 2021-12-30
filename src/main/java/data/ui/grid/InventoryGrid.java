package data.ui.grid;

import data.ui.tiles.InventoryTile;

import java.util.HashMap;

public class InventoryGrid extends Grid {
    private HashMap<InventoryTile, Integer> items;
    private int rows, collumns;

    public InventoryGrid(int rows, int collumns) {
        this.rows = rows;
        this.collumns = collumns;
    }

    @Override
    void setGrid(int rows, int collumns) {

    }
}
