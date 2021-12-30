package UI;

import data.model.ProgramState;
import data.model.ProgramStateManager;
import data.model.ToolController;
import org.lwjgl.input.Mouse;

import static helpers.OpenGLAssistent.*;

public class SideBarMenu extends LargeMenu {
    public SideBarMenu(ToolController handler) {
        super(handler, loadTexture("src/main/java/res/img/UI/2.png"));

        int halfTileSize = TILESIZE/2;
        menuUI.addButton("Generate", "src/main/java/res/img/UI/ComposeButton.png", (WIDTH-(2*256))-32, halfTileSize);
        menuUI.addButton("MainMenu", "src/main/java/res/img/UI/MenuButton.png", (WIDTH-(256))-32, halfTileSize);
    }

    //check if button clicked
    protected void updateButtons() {

        if (Mouse.isButtonDown(0)&&!flag) {
            if (menuUI.isButtonClick("Generate")) {
                System.out.println("KEY DOWN!");
                handler.autoGenerate();
                System.out.println("Generate selected");
            } else if (menuUI.isButtonClick("MainMenu")) {
                System.out.println("KEY DOWN!");
                ProgramStateManager.getInstance().setCurrentState(ProgramState.MAINMENU);
                System.out.println("Main menu selected");
            }
        }
        flag = Mouse.isButtonDown(0);
    }



    public void update() {
        drawQuadTex(background, 0, 0, 2000, 2000);
        handler.getGrid().draw();
        menuUI.draw();
        updateButtons();
    }
}
