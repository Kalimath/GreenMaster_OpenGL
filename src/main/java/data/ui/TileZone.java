package data.ui;

import data.model.PlaceHolder;
import data.ui.grid.GardenGrid;
import data.ui.tiles.GroundTile;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static helpers.OpenGLAssistent.*;

public class TileZone {
    private List<GroundTile> zone;
    private PlaceHolder placeHolder;
    private GardenGrid grid;
    private int xPlaceMostUpperLeft, yPlaceMostUpperLeft, xPlaceMostLowerRight, yPlaceMostLowerRight;

    //not tested!!
    public TileZone(int xCoordMostUpperLeft, int yCoordMostUpperLeft, int xCoordMostLowerRight, int yCoordMostLowerRight, PlaceHolder placeHolder, GardenGrid grid) {
        this.zone = new ArrayList<>();
        setPlaceHolder(placeHolder);
        this.grid = grid;
        setxPlaceMostUpperLeft(xCoordMostUpperLeft);
        setyPlaceMostUpperLeft(yCoordMostUpperLeft);
        setxPlaceMostLowerRight(xCoordMostLowerRight);
        setyPlaceMostLowerRight(yCoordMostLowerRight);
        asignZone(xPlaceMostUpperLeft, yPlaceMostUpperLeft, xPlaceMostLowerRight, yPlaceMostLowerRight);
    }

    public TileZone(GroundTile centerGroundTile, int rayTiles, PlaceHolder placeHolder, GardenGrid grid) {
        this.zone = new ArrayList<>();
        setPlaceHolder(placeHolder);
        this.grid = grid;
        defineZoneAroundTile(centerGroundTile, rayTiles);
    }

    public TileZone(PlaceHolder placeHolder, GardenGrid grid){
        this.zone = new ArrayList<>();
        setPlaceHolder(placeHolder);
        this.grid = grid;
    }

    private void defineZoneAroundTile(@NotNull GroundTile centerGroundTile, int rayTiles) {
        //set locations to match center tile
        xPlaceMostUpperLeft = (int) centerGroundTile.getX()/TILESIZE;
        xPlaceMostLowerRight = (int) (centerGroundTile.getX() + TILESIZE)/TILESIZE;
        yPlaceMostUpperLeft = (int) centerGroundTile.getY()/TILESIZE;
        yPlaceMostLowerRight = (int) (centerGroundTile.getY() + TILESIZE)/TILESIZE;

        //set locations to final value
        xPlaceMostUpperLeft -= rayTiles;
        xPlaceMostLowerRight += rayTiles;
        yPlaceMostUpperLeft -= rayTiles;
        yPlaceMostLowerRight += rayTiles;
        defineZone();
    }

    public List<GroundTile> getZone() {
        return zone;
    }

    public void defineZone() {
        //get 2 extreme tiles
        GroundTile tUpperLeft = grid.getTile(xPlaceMostUpperLeft,yPlaceMostUpperLeft);
        GroundTile tLowerRight = grid.getTile(xPlaceMostLowerRight,yPlaceMostLowerRight);

        //fill drawn zone with the dedicated filler
        for (int i = yPlaceMostUpperLeft; i < yPlaceMostLowerRight; i++) {
            for (int j = xPlaceMostUpperLeft; j < xPlaceMostLowerRight; j++) {
                zone.add(grid.getTile(j,i));
            }
        }
        grid.addTileZone(this);
    }

    public void asignZone(int xCoordMostUpperLeft, int yCoordMostUpperLeft, int xCoordMostLowerRight, int yCoordMostLowerRight) {
        setxPlaceMostUpperLeft(xCoordMostUpperLeft);
        setyPlaceMostUpperLeft(yCoordMostUpperLeft);
        setxPlaceMostLowerRight(xCoordMostLowerRight);
        setyPlaceMostLowerRight(yCoordMostLowerRight);
        defineZone();
    }

    public void draw(){
        for (GroundTile t: zone) {
            t.draw();
        }
        drawQuadTex(placeHolder.getTexture(), xPlaceMostUpperLeft*TILESIZE, yPlaceMostUpperLeft*TILESIZE, calculateWidth(), calculateHeight());
    }

    // returns total height of the zone
    public int calculateHeight(){
       return (yPlaceMostLowerRight-yPlaceMostUpperLeft)*TILESIZE;
    }

    // returns total width of the zone
    public int calculateWidth(){
        return (xPlaceMostLowerRight-xPlaceMostUpperLeft)*TILESIZE;
    }

    public PlaceHolder getPlaceHolder() {
        return placeHolder;
    }

    private void setPlaceHolder(PlaceHolder placeHolder) {
        this.placeHolder = placeHolder;
    }

    public int getxPlaceMostUpperLeft() {
        return xPlaceMostUpperLeft;
    }

    private void setxPlaceMostUpperLeft(int xPlaceMostUpperLeft) {
        this.xPlaceMostUpperLeft = xPlaceMostUpperLeft/TILESIZE;
    }

    public int getyPlaceMostUpperLeft() {
        return yPlaceMostUpperLeft;
    }

    private void setyPlaceMostUpperLeft(int yPlaceMostUpperLeft) {
        this.yPlaceMostUpperLeft = yPlaceMostUpperLeft/TILESIZE;
    }

    public int getxPlaceMostLowerRight() {
        return xPlaceMostLowerRight;
    }

    private void setxPlaceMostLowerRight(int xPlaceMostLowerRight) {
        this.xPlaceMostLowerRight = xPlaceMostLowerRight/TILESIZE;
    }

    public int getyPlaceMostLowerRight() {
        return yPlaceMostLowerRight;
    }

    private void setyPlaceMostLowerRight(int yPlaceMostLowerRight) {
        this.yPlaceMostLowerRight = yPlaceMostLowerRight/TILESIZE;
    }
}
