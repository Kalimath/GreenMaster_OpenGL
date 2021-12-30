package data.ui.grid;

import data.exception.InvalidPlaceException;
import data.model.PlaceHolder;
import data.ui.TileType;
import data.ui.TileZone;
import data.ui.tiles.GroundTile;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import static helpers.Validator.*;
import static helpers.OpenGLAssistent.*;

public class GardenGrid extends Grid {
    public GroundTile[][] map;
    private List<TileZone> zones;

    public GardenGrid(int rows, int columns){
        zones = new ArrayList<>();
        try {
            setGrid(rows,columns);
        }catch (Exception e){
            e.fillInStackTrace();
        }
    }

    public GardenGrid(int[][] newMap, int rows, int columns) {
        this(rows,columns);
        convertToGrid(newMap);
    }

    private void convertToGrid(int[][] newMap){
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    switch (newMap[j][i]){
                        case 0: map[i][j] = new GroundTile(i*TILESIZE,j*TILESIZE,TILESIZE,TILESIZE, TileType.Grass);
                            break;
                        case 1: map[i][j] = new GroundTile(i*TILESIZE,j*TILESIZE,TILESIZE,TILESIZE, TileType.Water);
                            break;
                        case 2: map[i][j] = new GroundTile(i*TILESIZE,j*TILESIZE,TILESIZE,TILESIZE, TileType.Path);
                            break;
                        default: map[i][j] = new GroundTile(i*TILESIZE,j*TILESIZE,TILESIZE,TILESIZE, TileType.Dirt);
                            break;
                    }
                }
            }
    }

    @Override
    protected void setGrid(int rows, int columns) throws IllegalArgumentException{
        if(rows>0&&columns>0){
            map = new GroundTile[rows][columns];
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    map[i][j] = new GroundTile(i*TILESIZE,j*TILESIZE,TILESIZE,TILESIZE, TileType.Grass);
                }
            }
        }else{
            throw new IllegalArgumentException("Could not create GardenGrid with given dimensions");
        }
    }

    public void addTileZone(@NotNull TileZone zone){
        if(!zones.contains(zone)){
        boolean isValid = isValidTileZoneForGrid(zone, this);
        if(isValid){
            zones.add(zone);
            for (GroundTile t: zone.getZone()) {
                this.setTile(t);
                t.setPlaceable(zone.getPlaceHolder(), this);

            }
        }else{
            System.out.println("not placeable");
            throw new InvalidPlaceException(zone.getPlaceHolder().getType()+" could not be placed on the given location");
        }
        }else {
            System.out.println("test");
            throw new InvalidPlaceException(zone.getPlaceHolder().getType()+" could not be placed on the given location: TileZone already exists");
        }

    }

    private void setTileZone(TileZone zone) {
        if(!zones.contains(zone)){
            for (GroundTile t: zone.getZone()) {
                setTile(t);
            }
            System.out.println("TileZone added to GardenGrid");
        }

    }

    public void setTile(@NotNull GroundTile groundTile){

        try {
            if(isValidTilePlace(groundTile, this)||map[groundTile.getXPlace()][groundTile.getYPlace()].equals(groundTile)){
                map[groundTile.getXPlace()][groundTile.getYPlace()] = groundTile;
            }else{
                //System.out.println("GroundTile(x:"+groundTile.getXPlace()+",Y:"+groundTile.getYPlace()+") is zoned");
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new InvalidPlaceException("GroundTile can't be set to specified location in grid");
        }
    }

    public void setTile(int xCoord, int yCoord, @NotNull TileType type){
        try {
            setTile(new GroundTile(xCoord*TILESIZE,yCoord*TILESIZE,TILESIZE,TILESIZE, type));
            //map[xCoord][yCoord] = new GroundTile(xCoord*TILESIZE,yCoord*TILESIZE,TILESIZE,TILESIZE, type);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public GroundTile getTile(int xPlace, int yPlace){
        return map[xPlace][yPlace];
    }

    public void draw(){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                GroundTile t = map[i][j];
                t.draw();
            }
        }
        drawZones();
    }


    public List<TileZone> getZones() {
        return zones;
    }

    public void drawZones(){
        for (TileZone zone: zones) {
            zone.draw();
        }
    }

    public TileZone getZoneFromPlaceHolder(@NotNull PlaceHolder holder){
        TileZone zone = null;
        try{
            boolean b = false;
            for (TileZone zone1 : zones) {
                if (zone1.getPlaceHolder().equals(holder)) {
                    zone = zones.get(zones.indexOf(zone1));
                    break;
                }
            }
        }catch (Exception ignore){

        }
        return zone;
    }

    public boolean isInTileZone(int xPlace, int yPlace){
        boolean result = false;
        for (TileZone zone : zones) {
            if(zone.getZone().contains(getTile(xPlace,yPlace))){
                result = true;
                break;
            }
        }
        return result;
    }
}
