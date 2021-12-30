package data.ui.tiles;

import data.model.PlaceHolder;
import data.ui.grid.Grid;
import data.ui.grid.GardenGrid;
import org.newdawn.slick.opengl.Texture;

import static helpers.OpenGLAssistent.*;
import static helpers.Validator.IsValidPlace;

public abstract class ItemTile {
    protected float x, y, width, height;
    protected Texture texture;
    protected PlaceHolder object;

    public ItemTile(float x, float y, float width, float height) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }

    public float getX() {
        return x;
    }

    public int getXPlace(){
        return (int) x / TILESIZE;
    }

    protected void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public int getYPlace(){
        return (int) y / TILESIZE;
    }

    protected void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    protected void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    protected void setHeight(float height) {
        this.height = height;
    }

    public Texture getTexture() {
        return texture;
    }

    protected void setTexture(Texture texture) {
        this.texture = texture;
    }

    public PlaceHolder getObject() {
        return object;
    }

    public void setPlaceable(PlaceHolder object, Grid grid) {
        if( grid instanceof GardenGrid){
            if(IsValidPlace(object,(GroundTile) this, (GardenGrid) grid)){
                this.object = object;
            }else{
                System.out.println("ERROR: Invalid place for object " + object.getType());
            }
        }else{
            this.object = object;
        }
    }

    public void draw(){
        drawQuadTex(texture, x, y, width, height);
        if(object!=null){
            object.draw();
        }
    }
}
