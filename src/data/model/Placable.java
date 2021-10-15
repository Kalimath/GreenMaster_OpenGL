package data.model;

import org.newdawn.slick.opengl.Texture;

import static helpers.OpenGLAssistent.DrawQuadTex;

public abstract class Placable {
    protected int  width, height;
    protected float x,y;
    protected String name;
    Texture texture;

    public Placable(Texture texture, String name, float x, float y, int width, int height) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        setName(name);
        setTexture(texture);
    }

    public Texture getTexture() {
        return texture;
    }

    private void setTexture(Texture texture) {
        this.texture = texture;
    }

    public float getX() {
        return x;
    }

    private void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    private void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    private void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    private void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public void draw(){
        DrawQuadTex(texture, x, y, width, height);
    }
}
