package data.ui;

import data.exception.InvalidPlaceException;
import data.model.Placeable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import static com.wagnerandade.coollection.Coollection.*;
import static helpers.Validator.*;
import static helpers.OpenGLAssistent.*;


public class TileGrid {
    public Tile[][] map;
    private List<TileZone> zones;

    public TileGrid(int rows,int columns){
        zones = new ArrayList<>();
        try {
            setGrid(rows,columns);
        }catch (Exception e){
            e.fillInStackTrace();
        }

    }

    public TileGrid(int[][] newMap,int rows,int columns) {
        this(rows,columns);
        convertToGrid(newMap);

    }

    private void convertToGrid(int[][] newMap){
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    switch (newMap[j][i]){
                        case 0: map[i][j] = new Tile(i*TILESIZE,j*TILESIZE,TILESIZE,TILESIZE, TileType.Grass);
                            break;
                        case 1: map[i][j] = new Tile(i*TILESIZE,j*TILESIZE,TILESIZE,TILESIZE, TileType.Water);
                            break;
                        case 2: map[i][j] = new Tile(i*TILESIZE,j*TILESIZE,TILESIZE,TILESIZE, TileType.Path);
                            break;
                        default: map[i][j] = new Tile(i*TILESIZE,j*TILESIZE,TILESIZE,TILESIZE, TileType.Dirt);
                            break;
                    }
                }
            }
    }

    private void setGrid(int rows, int columns) throws IllegalArgumentException{
        if(rows>0&&columns>0){
            map = new Tile[rows][columns];
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    map[i][j] = new Tile(i*TILESIZE,j*TILESIZE,TILESIZE,TILESIZE, TileType.Grass);
                }
            }
        }else{
            throw new IllegalArgumentException("Could not create TileGrid with given dimensions");
        }
    }

    public void addTileZone(@NotNull TileZone zone){
        if(!zones.contains(zone)){
        boolean isValid = isValidTileZoneForGrid(zone, this);
        if(isValid){
            zones.add(zone);
            for (Tile t: zone.getZone()) {
                this.setTile(t);
                t.setObject(zone.getFiller(), this);

            }
        }else{
            System.out.println("not placeable");
            throw new InvalidPlaceException(zone.getFiller().getName()+" could not be placed on the given location");
        }
        }else {
            System.out.println("test");
            throw new InvalidPlaceException(zone.getFiller().getName()+" could not be placed on the given location: TileZone already exists");
        }

    }

    private void setTileZone(TileZone zone) {
        if(!zones.contains(zone)){
            for (Tile t: zone.getZone()) {
                setTile(t);
            }
            System.out.println("TileZone added to TileGrid");
        }

    }

    public void setTile(@NotNull Tile tile){

        try {
            if(isValidTilePlace(tile, this)||map[tile.getXPlace()][tile.getYPlace()].equals(tile)){
                map[tile.getXPlace()][tile.getYPlace()] = tile;
            }else{
                //System.out.println("Tile(x:"+tile.getXPlace()+",Y:"+tile.getYPlace()+") is zoned");
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new InvalidPlaceException("Tile can't be set to specified location in grid");
        }
    }

    public void setTile(int xCoord, int yCoord, @NotNull TileType type){
        try {
            setTile(new Tile(xCoord*TILESIZE,yCoord*TILESIZE,TILESIZE,TILESIZE, type));
            //map[xCoord][yCoord] = new Tile(xCoord*TILESIZE,yCoord*TILESIZE,TILESIZE,TILESIZE, type);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Tile getTile(int xPlace, int yPlace){
        return map[xPlace][yPlace];
    }

    public void draw(){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Tile t = map[i][j];
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

    public TileZone getZoneFromPlaceable(@NotNull Placeable object){
        TileZone zone = null;
        try{
            boolean b = false;
            for (TileZone zone1 : zones) {
                if (zone1.getFiller().equals(object)) {
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
