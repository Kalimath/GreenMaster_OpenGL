package data.ui.tiles;

import data.model.Placeable;
import data.ui.grid.GardenGrid;
import data.ui.grid.Grid;

public class InventoryTile extends ItemTile {

    private int amount;

    public InventoryTile(float x, float y, float width, float height, Placeable object, int amount) {
        super(x,y,width,height);
        setPlaceable(object);
        setAmount(amount);
    }

    public int getAmount() {
        return amount;
    }

    private void setAmount(int amount) {
        if(amount>0) this.amount = amount;
    }

    private void setPlaceable(Placeable object) {
        if(object!=null) this.object = object;
    }

    @Override
    public void setPlaceable(Placeable object, Grid grid){
        if( grid instanceof GardenGrid){
            throw new IllegalArgumentException("Placeable (in inventoryTile) can't be placed in a GroundTileGrid");
        }else{
            this.object = object;
        }
    }
}