package data.model.species;

import data.exception.TextureNotFoundException;
import data.model.Placeable;
import data.model.PlantType;
import org.jetbrains.annotations.NotNull;
import org.newdawn.slick.opengl.Texture;

import static helpers.OpenGLAssistent.loadTexture;

public class Specie {


    private Texture texture;
    private String name;
    private String info;
    private boolean isWide = false;
    private boolean isHigh = false;
    private boolean isPlantableInWater = false;
    private boolean isPlantableInPot = false;
    private double width;
    private double height;
    private double length;
    //protected double length, lengthMax, spaceBetween,age,ageMax;

    public Specie(@NotNull String name, @NotNull String info, @NotNull String texturePath, boolean isWide, boolean isHigh, boolean isPlantableInWater, boolean isPlantableInPot, double metricWidth, double metricHeight, double metricLength) {
        setName(name);
        setInfo(info);
        setTexture(texturePath);
        setWide(isWide);
        setHigh(isHigh);
        setPlantableInWater(isPlantableInWater);
        setPlantableInPot(isPlantableInPot);
        setWidth(metricWidth);
        setHeight(metricHeight);
        setLength(metricLength);

    }

    public Specie() {
    }

    private Specie(Builder builder) {
        texture = builder.texture;
        setName(builder.name);
        setInfo(builder.info);
        setWide(builder.isWide);
        setHigh(builder.isHigh);
        setPlantableInWater(builder.isPlantableInWater);
        setPlantableInPot(builder.isPlantableInPot);
        setWidth(builder.width);
        setHeight(builder.height);
        setLength(builder.length);
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(@NotNull String texturePath) throws TextureNotFoundException{
        try {
            this.texture = loadTexture(texturePath);
        }catch (Exception e){
            e.printStackTrace();
            throw new TextureNotFoundException(e.getMessage());
        }

    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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

    public boolean isWide() {
        return isWide;
    }

    public void setWide(boolean wide) {
        isWide = wide;
    }

    public boolean isHigh() {
        return isHigh;
    }

    public void setHigh(boolean high) {
        isHigh = high;
    }

    public boolean isPlantableInWater() {
        return isPlantableInWater;
    }

    public void setPlantableInWater(boolean plantableInWater) {
        isPlantableInWater = plantableInWater;
    }

    public boolean isPlantableInPot() {
        return isPlantableInPot;
    }

    public void setPlantableInPot(boolean plantableInPot) {
        isPlantableInPot = plantableInPot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static final class Builder {
        private Texture texture;
        private String name;
        private String info;
        private boolean isWide;
        private boolean isHigh;
        private boolean isPlantableInWater;
        private boolean isPlantableInPot;
        private double width;
        private double height;
        private double length;

        public Builder() {
        }

        public Builder texture(String texturePath) throws TextureNotFoundException {
            try {
                this.texture = loadTexture(texturePath);
            }catch (Exception e){
                e.printStackTrace();
                throw new TextureNotFoundException(e.getMessage());
            }
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder info(String val) {
            info = val;
            return this;
        }

        public Builder isWide(boolean val) {
            isWide = val;
            return this;
        }

        public Builder isHigh(boolean val) {
            isHigh = val;
            return this;
        }

        public Builder isPlantableInWater(boolean val) {
            isPlantableInWater = val;
            return this;
        }

        public Builder isPlantableInPot(boolean val) {
            isPlantableInPot = val;
            return this;
        }

        public Builder width(double val) {
            width = val;
            return this;
        }

        public Builder height(double val) {
            height = val;
            return this;
        }

        public Builder length(double val) {
            length = val;
            return this;
        }

        public Specie build() {
            return new Specie(this);
        }
    }
}


