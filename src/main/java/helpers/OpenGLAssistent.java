package helpers;

import org.jetbrains.annotations.NotNull;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.*;

import java.io.IOException;
import java.io.InputStream;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.newdawn.slick.util.ResourceLoader.getResourceAsStream;

public class OpenGLAssistent {
    public static final int WIDTH = 1850, HEIGHT = 960,ROWS = 40,COLUMNS = 30, TILESIZE = 32, FPS = 60;
    public static final double SCALE = 22;
    //public static final double SCALE = 0.01;

    public static void launchSession(){
        Display.setTitle("GreenMaster");
        try {
            Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, WIDTH, HEIGHT,0,1,-1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    public static void drawQuad(float x, float y, float width, float height){
        glBegin(GL_QUADS);
        glVertex2f(x,y);                                  //top left corner
        glVertex2f(x + width,y);                       //top right corner
        glVertex2f(x + width,y + height);           //bottom right corner
        glVertex2f(x,y + height);                      //bottom left corner
        glEnd();
    }

    public static void drawQuadTex(Texture tex, float x, float y, float width, float height){
        tex.bind();
        glTranslatef(x,y,0);
        glBegin(GL_QUADS);
        glTexCoord2f(0,0);
        glVertex2f(0,0);
        glTexCoord2f(1,0);
        glVertex2f((float) width,0);
        glTexCoord2f(1,1);
        glVertex2f(width,height);
        glTexCoord2f(0,1);
        glVertex2f(0,height);
        glEnd();
        glLoadIdentity();
    }

    public static Texture loadTextureWithFormat(@NotNull String path, @NotNull String format){
        Texture tex = null;
        try {
            InputStream in = getResourceAsStream(path);
            tex = TextureLoader.getTexture(format,in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tex;
    }

    public static double[] convertDimensionsToScale(double width, double height){
        double[] dimensions = new double[2];
        dimensions[0] =  width*SCALE;
        dimensions[1] = height*SCALE;
        return dimensions;
    }

    public static Texture loadTexture(@NotNull String path){
        return loadTextureWithFormat(path, "PNG");
    }
}
