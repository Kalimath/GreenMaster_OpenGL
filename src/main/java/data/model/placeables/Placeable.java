package data.model.placeables;

import data.exception.TextureNotFoundException;
import data.model.placeables.species.PlantType;
import org.jetbrains.annotations.NotNull;
import org.newdawn.slick.opengl.Texture;

import static helpers.OpenGLAssistent.loadTexture;

public abstract class Placeable {
    protected String name;
    protected double width;
    protected double height;
    protected double length;
    protected Type type;
    protected String texturePath;

    public Placeable(String name, double width, double height, double length, Type type, String texturePath) {
        setName(name);
        setWidth(width);
        setHeight(height);
        setLength(length);
        setType(type);
        setTexturePath(texturePath);
    }

    private void setType(Type type) {
        this.type = type;
    }

    public Placeable() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setTexturePath(@NotNull String texturePath){
        this.texturePath = texturePath;
    }

    public Texture getTexture() {
        try {
            return loadTexture(texturePath);
        }catch (Exception e){
            e.printStackTrace();
            throw new TextureNotFoundException(e.getMessage());
        }
    }

    public Type getType() {
        return type;
    }




}
