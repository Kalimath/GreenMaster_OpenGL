package data.model;

public enum PlantType {
    HighTree("res/textures/plants/9.png", false, true,false,false,"High tree"),
    Bush("res/textures/plants/1.png", false, false,false,true,"High bush"),
    Birch("res/textures/plants/5.png", true, true,false,false,"Birch tree"),
    Foliage("res/textures/plants/foliage5.png", true, false,false,false,"Some small foliage");

    public final String texturePath, info;
    public final boolean isWide, isHigh, isPlantableInWater,isPlantableInPot;

    PlantType(String texturePath, boolean isWide, boolean isHigh, boolean isPlantableInWater, boolean isPlantableInPot, String info) {
        this.texturePath = texturePath;
        this.info = info;
        this.isHigh = isHigh;
        this.isWide = isWide;
        this.isPlantableInWater = isPlantableInWater;
        this.isPlantableInPot = isPlantableInPot;
    }

    public String getTexturePath() {
        return texturePath;
    }

    public String getInfo() {
        return info;
    }

    public boolean isWide() {
        return isWide;
    }

    public boolean isHigh() {
        return isHigh;
    }
}
