package data.model;

import org.jetbrains.annotations.NotNull;
import org.newdawn.slick.opengl.Texture;

import static helpers.OpenGLAssistent.loadTexture;

public class Plant extends Placeable {

    private PlantType type;
    //protected double length, lengthMax, spaceBetween,age,ageMax;

    public Plant(PlantType type, float x, float y, double width, double height, double metricLength) {
        super(loadTexture(type.getTexturePath()), type.name(), x, y, width, height, metricLength);
        this.type = type;
    }

    public Plant(Texture texture, String name, float x, float y, double width, double height, double length) {
        super(texture, name, x, y, width, height, length);
    }

    public Plant(Texture texture, String name, float x, float y, double width, double height, double length, PlantType type) {
        super(texture, name, x, y, width, height, length);
        this.type = type;
    }

    private Plant(Builder builder) {
        setType(builder.type);
        setWidth(builder.width);
        setHeight(builder.height);
        setLength(builder.length);
        setX(builder.x);
        setY(builder.y);
        setName(type.name());

        setTexture(loadTexture(type.getTexturePath()));
    }

    public PlantType getType() {
        return type;
    }

    public void setType(PlantType type) {
        this.type = type;
    }


    public static final class Builder {
        private double width;
        private double height;
        private double length;
        private float x;
        private float y;
        private String name;
        private Texture texture;
        private PlantType type;

        public Builder() {
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

        public Builder x(float val) {
            x = val;
            return this;
        }

        public Builder y(float val) {
            y = val;
            return this;
        }

        public Builder name( @NotNull String val) {
            name = val;
            return this;
        }

        public Builder texture(@NotNull Texture val) {
            texture = val;
            return this;
        }

        public Builder plantType(PlantType val) {
            type = val;
            return this;
        }

        public Placeable Build() {
            return new Plant(this);
        }
    }
}



    //    public Plant(Texture tex, String name, float x, float y, int width, int height, double age, int ageMax, double length, double lengthMax, double spaceBetween) {
//        super(tex, name, x, y, width, height);
//        this.age = age;
//        this.ageMax = ageMax;
//        this.length = length;
//        this.lengthMax = lengthMax;
//        this.spaceBetween = spaceBetween;
//    }

