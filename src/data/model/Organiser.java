package data.model;

import data.exception.AutoGenerateException;
import data.exception.InvalidPlaceException;
import data.ui.Tile;
import data.ui.TileGrid;
import data.ui.TileZone;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

import static helpers.OpenGLAssistent.TILESIZE;
import static helpers.OpenGLAssistent.convertDimensionsToScale;

public class Organiser {
    private TileGrid grid;
    private Inventory inventory;
    private ObjectSorter sorter;
    private int[][] collisionFreeMap;

    public Organiser(TileGrid grid, Inventory inventory) {
        this.grid = grid;
        collisionFreeMap = new int[grid.map.length][grid.map[0].length];
        this.inventory = inventory;
        this.sorter = new ObjectSorter();
    }

    //sets collisionFreeMap with 0 if not suited(like water), else 1
    public void setCollisionFreeMap(){
        Tile[][] map = grid.map;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].getTileType().isBuildable){
                    collisionFreeMap[i][j]=1;
                }}}
        System.out.println("Collision-free grid generated!");
    }

    //puts objects in the grid depending on the collision, size and height
    public void getComposedGrid(){
        Tile[][] map = grid.map;
        List<TileZone> zones = new ArrayList<>();
        try{
            for (Placeable object: inventory.getObjects()) {
                zones.add(createTileZoneForPlaceable(object));
            }
            Deque<TileZone> sortedZones = sorter.arrangeTileZonesBySize(zones);
            System.out.println("Sorted items for TileGrid!");
            placeObjectsInGrid(sortedZones);

        }catch (Exception e){
            e.printStackTrace();
            throw new AutoGenerateException("Grid with objects failed to compose: invalid place for object!");
        }

        System.out.println("Grid with objects is composed!");
    }

    //Creates a zone with collision for the placeable
    public TileZone createTileZoneForPlaceable(@NotNull Placeable placeable){
        TileZone objectZone = new TileZone(placeable, grid);
        System.out.println("TileZone generated for object!");
        return objectZone;
    }

    //TODO
    //places object in the grid at the most suitable place
    public void placeObjectsInGrid(@NotNull Deque<TileZone> objectZones){
        try {
            int amount = objectZones.size();
            while (!objectZones.isEmpty()) {
                double[] dimensions = convertDimensionsToScale(objectZones.getFirst().getFiller().width, objectZones.getFirst().getFiller().height);
                System.out.println(dimensions[0]);
                boolean asigned = false;

                //itereer over map en als xlefttop vrij is daar plaatsen
                for (int i = 0; i < collisionFreeMap.length; i++) {
                for (int j = 0; j < collisionFreeMap[0].length; j++) {
                    System.out.println("collisionFreeMap.length: "+collisionFreeMap.length);
                    System.out.println("collisionFreeMap[0].length: "+collisionFreeMap[0].length);
                        if (collisionFreeMap[i][j] == 1) {
                            int diameter = (int) Math.floor(dimensions[0]);
                            int xPlaceMostLowerRight = j+(diameter/TILESIZE);
                            int yPlaceMostLowerRight = i+(diameter/TILESIZE);

                            if (hasDesignatedSpace(j, i,xPlaceMostLowerRight,yPlaceMostLowerRight)) {
                                objectZones.getFirst().asignZone(j*TILESIZE, i*TILESIZE, xPlaceMostLowerRight*TILESIZE, yPlaceMostLowerRight*TILESIZE);
                                System.out.println("placed "+objectZones.getFirst().getClass().getSimpleName()+" in TileGrid with upperleft: ("+j+","+i+")!");

                                asigned = true;
                                break;
                            }
                        }
                    }
                    if(asigned) break;
                }
                objectZones.removeFirst();
                System.out.println(objectZones.size());
            }
            setCollisionFreeMap();
            System.out.println("placed all "+amount+" objects in TileGrid!");
            System.out.println("#zones: "+grid.getZones().size());
        }catch (Exception e){
            e.printStackTrace();
            throw new AutoGenerateException(e.getMessage());
        }
    }

    public boolean hasDesignatedSpace(int xPlaceMostUpperLeft, int yPlaceMostUpperLeft,int xPlaceMostLowerRight,int yPlaceMostLowerRight){
        boolean isValid = true;
        for (int x = yPlaceMostUpperLeft; x < yPlaceMostLowerRight; x++) {
            for (int y = xPlaceMostUpperLeft; y < xPlaceMostLowerRight; y++) {
                if(collisionFreeMap[y][x] == 0) {
                    isValid = false;
                    break;
                }
            }
            if(!isValid)break;
        }
        return isValid;
    }


    public void fillMapWithObjects() {
        setCollisionFreeMap();
        getComposedGrid();
    }
}
