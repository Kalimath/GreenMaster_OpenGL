package data.model;

import org.newdawn.slick.opengl.Texture;

import static helpers.OpenGLAssistent.drawQuadTex;

public abstract class Placeable {
    protected double width,height,length;
    protected float x,y;
    protected String name;
    Texture texture;

    public Placeable(Texture texture, String name, float x, float y, double width, double height, double length) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        setLength(length);
        setName(name);
        setTexture(texture);
    }

    public Placeable() {
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public Texture getTexture() {
        return texture;
    }

    protected void setTexture(Texture texture) {
        this.texture = texture;
    }

    public float getX() {
        return x;
    }

    protected void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    protected void setY(float y) {
        this.y = y;
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

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }



    public void draw(){
        drawQuadTex(texture, x, y, (float)width, (float)height);
    }
}
