package data.ui;

public enum TileType {

    Grass("grass","src/main/java/res/textures/tiles/Map_tile_17.png", true, false),
    Dirt("dirt","src/main/java/res/textures/tiles/Map_tile_128.png", true, true),
    Path("dirt","src/main/java/res/textures/tiles/grassy_path.png", false, false),
    Water("water","src/main/java/res/textures/tiles/Map_tile_37.png", false, false);

    public final String texturename, texturePath;
    public final boolean isBuildable, isPlantable;

    TileType(String textureName, String texturePath, boolean isBuildable, boolean isPlantable) {
        this.texturename = textureName;
        this.texturePath = texturePath;
        this.isBuildable = isBuildable;
        this.isPlantable = isPlantable;
    }

}
