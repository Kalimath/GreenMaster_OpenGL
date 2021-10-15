package data.ui;

public enum TileType {

    Grass("grass","res/textures/tiles/Map_tile_17.png", true, false),
    Dirt("dirt","res/textures/tiles/Map_tile_128.png", true, true),
    Water("water","res/textures/tiles/Map_tile_37.png", false, false);

    public final String texturename, texturePath;
    public final boolean isWorkable, isPlantable;

    TileType(String textureName, String texturePath, boolean isBuildable, boolean isPlantable) {
        this.texturename = textureName;
        this.texturePath = texturePath;
        this.isWorkable = isBuildable;
        this.isPlantable = isPlantable;
    }

}
