package data.model;

import data.ui.grid.GardenGrid;

public class AutoGenerateTool extends DesignTool {

    Organiser organiser;
    private Inventory objectsForPlacement;

    public AutoGenerateTool(GardenGrid grid, Inventory objectsForPlacement) {
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
