package data;

import data.model.*;
import org.lwjgl.opengl.Display;

import static helpers.OpenGLAssistent.*;

public class Boot {

    public Boot(){

        launchSession();

        DesignController controller = new DesignController();


        while (!Display.isCloseRequested()){
            controller.update();
            Display.update();
            Display.sync(FPS);
        }

        Display.destroy();
    }


    public static void main (String[] args){
        new Boot();
    }
}
