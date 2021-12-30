package data.model;

import data.exception.NotAllocatedException;
import data.model.placeables.Placeable;
import data.model.placeables.Type;
import org.jetbrains.annotations.NotNull;
import org.newdawn.slick.opengl.Texture;

import static helpers.OpenGLAssistent.drawQuadTex;

public class PlaceHolder {
    protected double width,height,length;
    protected float x,y;
    protected Type type;
    private Placeable filler;
    private boolean allocated = false;

    public PlaceHolder(String name, float x, float y, double width, double height, double length) {
        setX(x);
        setY(y);
        setAllocated(true);
        setWidth(width);
        setHeight(height);
        setLength(length);

    }
    public PlaceHolder(@NotNull Placeable filler, String name, float x, float y, double width, double height, double length) {
        this(name,x,y,width,height,length);
        setFiller(filler);
    }

    public PlaceHolder() {
    }

    public PlaceHolder(@NotNull Placeable placeable) {
        this(placeable.getName(),0,0, placeable.getWidth(), placeable.getHeight(), placeable.getLength());
        setFiller(placeable);
    }

    public Placeable getFiller() {
        return filler;
    }

    public void setFiller( @NotNull Placeable filler) {
        this.filler = filler;
        if(filler.getType()!=null){
            this.type = filler.getType();
        }else{
            throw new NullPointerException("filler without type can't be set");
        }

    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public Texture getTexture() {
        return filler.getTexture();
    }

    public float getX() {
        return x;
    }

    protected void setX(float x) {
        if (x>0){
            this.x = x;
            if (y>0){
                allocated = true;
            }
        }

    }

    public float getY() {
        return y;
    }

    protected void setY(float y) {
        if (y>0){
            this.y = y;
            if (x>0){
                allocated = true;
            }
        }
    }

    public double getWidth() {
        return width;
    }

    protected void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    protected void setHeight(double height) {
        this.height = height;
    }

    public Type getType() {
        return type;
    }

    public void draw(){
        if(allocated){
            drawQuadTex(getTexture(), x, y, (float)width, (float)height);
        }else{
            throw new NotAllocatedException("Placeholder can not be drawn");
        }

    }

    public boolean isAllocated() {
        return allocated;
    }

    private void setAllocated(boolean allocated) {
        this.allocated = allocated;
    }
}
