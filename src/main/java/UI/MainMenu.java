package UI;

import data.model.ProgramState;
import data.model.ProgramStateManager;
import data.model.ToolController;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import static helpers.OpenGLAssistent.*;

public class MainMenu extends LargeMenu {

    public MainMenu(ToolController handler) {
        super(handler, loadTexture("src/main/java/res/img/UI/1.png"));

        int halfTileSize = TILESIZE/2;
        menuUI.addButton("Editor", "src/main/java/res/img/UI/EditorButton.png", (WIDTH-(256))-128, 512);
        menuUI.addButton("Quit", "src/main/java/res/img/UI/QuitButton.png", (WIDTH-(256))-128, ((2*64)+512)+halfTileSize);
    }

    //check if button clicked
    protected void updateButtons() {

        if (Mouse.isButtonDown(0)&&!flag) {
            if (menuUI.isButtonClick("Editor")) {
                System.out.println("Editor selected");
                ProgramStateManager.getInstance().setCurrentState(ProgramState.EDITOR);
                handler.edit();
                System.out.println("EditorTool selected");
            } else if (menuUI.isButtonClick("Quit")) {
                System.out.println("shutting down...");
                //TODO save map
                System.exit(0);
            }
        }
        flag = Mouse.isButtonDown(0);
    }



    public void update() {
        drawQuadTex(background, 0, 0, 2000, 2000);
        menuUI.draw();
        updateButtons();
    }
}
