package data.ui;

import data.model.Placable;
import org.newdawn.slick.opengl.Texture;

import static helpers.OpenGLAssistent.*;
import static helpers.Validator.IsValidPlace;

public class Tile {
    private float x, y, width, height;
    private Texture texture;
    private TileType tileType;
    private Placable object;

    public Tile(float x, float y, float width, float height, TileType tileType) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        setTexture(loadTexture(tileType.texturePath));
        setTileType(tileType);
    }

    public Placable getObject() {
        return object;
    }

    public void setObject(Placable object) {
        if(IsValidPlace(object,this)){
            this.object = object;
        }else{
            System.out.println("ERROR: Invalid place for object " + object.getName());
        }
    }

    public void draw(){
        drawQuadTex(texture, x, y, width, height);
        if(object!=null){
            object.draw();
        }
    }

    public float getX() {
        return x;
    }

    public int getXPlace(){
        return (int) x / TILESIZE;
    }

    private void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public int getYPlace(){
        return (int) y / TILESIZE;
    }

    private void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    private void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    private void setHeight(float height) {
        this.height = height;
    }

    public Texture getTexture() {
        return texture;
    }

    private void setTexture(Texture texture) {
        this.texture = texture;
    }
    public TileType getTileType() {
        return tileType;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }
}
