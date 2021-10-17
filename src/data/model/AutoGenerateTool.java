package data.model;

import data.ui.TileGrid;

public class AutoGenerateTool extends DesignTool {

    Organiser organiser;
    private Inventory objectsForPlacement;

    public AutoGenerateTool(TileGrid grid, Inventory objectsForPlacement) {
        super(grid);
        this.objectsForPlacement = objectsForPlacement;
        organiser = new Organiser(grid, objectsForPlacement);
    }

    @Override
    public void update() {
        for (Placeable p: objectsForPlacement.getObjects()) {
            if(grid.getZoneFromPlaceable(p)==null){
                set();
                break;
            }
        }

    }

    @Override
    public void set() {
        System.out.println("autogenerate set method");
        organiser.fillMapWithObjects();
    }
}
