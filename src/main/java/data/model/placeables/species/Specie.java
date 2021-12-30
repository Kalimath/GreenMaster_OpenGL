package data.model.placeables.species;

import data.model.placeables.Placeable;
import org.jetbrains.annotations.NotNull;

public class Specie extends Placeable {


    private String info;
    private boolean isWide = false;
    private boolean isHigh = false;
    private boolean isPlantableInWater = false;
    private boolean isPlantableInPot = false;

    //TODO
    /*private PeriodOfYear periodAtItsBest;*/
    //protected double length, lengthMax, spaceBetween,age,ageMax;

    public Specie(@NotNull String name, @NotNull String info, PlantType type, @NotNull String texturePath, boolean isWide, boolean isHigh, boolean isPlantableInWater, boolean isPlantableInPot, double metricWidth, double metricHeight, double metricLength) {
        super(name, metricWidth, metricHeight, metricLength,type, texturePath);
        setInfo(info);
        setWide(isWide);
        setHigh(isHigh);
        setPlantableInWater(isPlantableInWater);
        setPlantableInPot(isPlantableInPot);
    }

    public Specie() {
        super();
    }





    private Specie(Builder builder) {
        texturePath = builder.texturePath;
        setName(builder.name);
        setInfo(builder.info);
        setWide(builder.isWide);
        setHigh(builder.isHigh);
        setPlantableInWater(builder.isPlantableInWater);
        setPlantableInPot(builder.isPlantableInPot);
        setWidth(builder.width);
        setHeight(builder.height);
        setLength(builder.length);
        setType(builder.type);

    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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

    public void setType(PlantType type) {
        this.type = type;
    }


    public static final class Builder {
        private String texturePath;
        private String name;
        private String info;
        private PlantType type;
        private boolean isWide;
        private boolean isHigh;
        private boolean isPlantableInWater;
        private boolean isPlantableInPot;
        private double width;
        private double height;
        private double length;

        public Builder() {
        }

        public Builder texture(String texturePath) {
            this.texturePath = texturePath;
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

        public Builder type(PlantType val) {
            type = val;
            return this;
        }

        public Specie build() {
            return new Specie(this);
        }

    }
}


