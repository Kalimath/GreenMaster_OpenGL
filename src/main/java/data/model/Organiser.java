package data.model;

import data.exception.AutoGenerateException;
import data.ui.tiles.GroundTile;
import data.ui.grid.GardenGrid;
import data.ui.TileZone;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static helpers.OpenGLAssistent.TILESIZE;
import static helpers.OpenGLAssistent.convertDimensionsToScale;

public class Organiser {
    private GardenGrid grid;
    private Inventory inventory;
    private ObjectSorter sorter;
    private int[][] collisionMap;

    public Organiser(GardenGrid grid, Inventory inventory) {
        this.grid = grid;
        collisionMap = new int[grid.map.length][grid.map[0].length];
        this.inventory = inventory;
        this.sorter = new ObjectSorter();
    }

    //sets collisionFreeMap with 0 if not suited(like water), else 1
    public void setCollisionMap() {
        GroundTile[][] map = grid.map;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (!map[i][j].getTileType().isBuildable || map[i][j].getObject() != null || grid.isInTileZone(i, j)) {
                    collisionMap[i][j] = 0;
                } else {
                    collisionMap[i][j] = 1;
                    if (i == 17 && j == 0) {
                        System.out.println(i + "," + j);
                    }
                }
            }
        }
        System.out.println("Collision-map generated!");
    }

    //puts objects in the grid depending on the collision, size and height
    public void getComposedGrid() {
        GroundTile[][] map = grid.map;
        List<TileZone> zones = new ArrayList<>();
        try {
            for (PlaceHolder object : inventory.getObjects()) {
                zones.add(createTileZoneForPlaceable(object));
            }
            Deque<TileZone> sortedZones = sorter.arrangeTileZonesBySize(zones);
            System.out.println("Sorted items for GardenGrid!");
            int amount = sortedZones.size();
            System.out.println("SortedZones length: " + amount);
            while (!sortedZones.isEmpty()) {
                placeObjectInGrid(sortedZones.pollFirst());
            }

            System.out.println("placed all " + amount + " objects in GardenGrid!");

        } catch (Exception e) {
            e.printStackTrace();
            String message = "Grid with objects failed to compose: invalid place for object!";
            JOptionPane.showMessageDialog(null,message,"Waarschuwing",JOptionPane.ERROR_MESSAGE);
            throw new AutoGenerateException(message);

        }

        System.out.println("Grid with objects is composed!");
    }

    //Creates a zone with collision for the placeable
    public TileZone createTileZoneForPlaceable(@NotNull PlaceHolder placeable) {
        TileZone objectZone = new TileZone(placeable, grid);
        System.out.println("TileZone generated for object!");
        return objectZone;
    }

    //places object in the grid at the most suitable place
    public void placeObjectInGrid(@NotNull TileZone objectzone) {
        try {
            double[] dimensions = convertDimensionsToScale(objectzone.getPlaceHolder().width, objectzone.getPlaceHolder().height);
            System.out.println(dimensions[0]);
            boolean asigned = false;

            //itereer over map en als xlefttop vrij is daar plaatsen
            for (int i = 0; i < collisionMap.length; i++) {
                for (int j = 0; j < collisionMap[0].length; j++) {
                    System.out.println("collisionFreeMap.length: " + collisionMap.length);
                    System.out.println("collisionFreeMap[0].length: " + collisionMap[0].length);
                    if (collisionMap[i][j] == 1) {
                        int diameter = (int) Math.floor(dimensions[0]);
                        int xPlaceMostLowerRight = j + (diameter / TILESIZE);
                        int yPlaceMostLowerRight = i + (diameter / TILESIZE);

                        if (hasDesignatedSpace(j, i, xPlaceMostLowerRight, yPlaceMostLowerRight)) {
                            objectzone.asignZone(j * TILESIZE, i * TILESIZE, xPlaceMostLowerRight * TILESIZE, yPlaceMostLowerRight * TILESIZE);
                            System.out.println("placed " + objectzone.getClass().getSimpleName() + " in GardenGrid with upperleft: (" + j + "," + i + ")!");

                            asigned = true;
                            break;
                        }
                    }
                }
                if (asigned) break;
            }

            setCollisionMap();

            System.out.println("#zones: " + grid.getZones().size());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AutoGenerateException(e.getMessage());
        }
    }

    public boolean hasDesignatedSpace(int xPlaceMostUpperLeft, int yPlaceMostUpperLeft, int xPlaceMostLowerRight, int yPlaceMostLowerRight) {
        boolean isValid = true;
        for (int x = yPlaceMostUpperLeft; x < yPlaceMostLowerRight; x++) {
            for (int y = xPlaceMostUpperLeft; y < xPlaceMostLowerRight; y++) {
                if (collisionMap[y][x] == 0) {
                    isValid = false;
                    break;
                }
            }
            if (!isValid) break;
        }
        return isValid;
    }


    public void fillMapWithObjects() {
        setCollisionMap();
        getComposedGrid();
    }
}
