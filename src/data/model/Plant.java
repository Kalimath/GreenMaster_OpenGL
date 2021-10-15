package data.model;

import org.newdawn.slick.opengl.Texture;

import static helpers.OpenGLAssistent.LoadTexture;

public class Plant extends Placable {

    private PlantType type;
    //protected double length, lengthMax, spaceBetween,age,ageMax;

    public Plant(PlantType type, float x, float y, int width, int height) {
        super(LoadTexture(type.getTexturePath()), type.name(), x, y, width, height);
        this.type = type;
    }

    public PlantType getType() {
        return type;
    }

    //    public Plant(Texture tex, String name, float x, float y, int width, int height, double age, int ageMax, double length, double lengthMax, double spaceBetween) {
//        super(tex, name, x, y, width, height);
//        this.age = age;
//        this.ageMax = ageMax;
//        this.length = length;
//        this.lengthMax = lengthMax;
//        this.spaceBetween = spaceBetween;
//    }
}
