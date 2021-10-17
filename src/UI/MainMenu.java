package UI;

import data.model.ToolController;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import static helpers.OpenGLAssistent.*;

public class MainMenu {

  private ToolController handler;
  private Texture background;
  private UI menuUI;
  boolean flag;

  public MainMenu(ToolController handler) {
    background = loadTextureWithFormat("res/img/Goliszew.jpg", "JPG");
    menuUI = new UI();
    flag = false;
    this.handler = handler;
    int halfTileSize = TILESIZE/2;
    menuUI.addButton("Edit", "res/icons/2.png", (ROWS*TILESIZE)+halfTileSize, halfTileSize);
    menuUI.addButton("Generate", "res/icons/4.png", ((ROWS)*TILESIZE)+64+halfTileSize, halfTileSize);
    //menuUI.addButton("Quit", "quit", WIDTH / 2 - 128, (int) (HEIGHT * 0.85f));
  }

  //check if button clicked
  private void updateButtons() {

      if (Mouse.isButtonDown(0)&&!flag) {
        if (menuUI.isButtonClick("Generate")) {
            System.out.println("KEY DOWN!");
            handler.autoGenerate();
          System.out.println("Generate selected");
        }else if (menuUI.isButtonClick("Edit")) {
          System.out.println("Editor selected");
          handler.edit();
          System.out.println("EditorTool selected");
        }
      }
      flag = Mouse.isButtonDown(0);
    }



  public void update() {
    //drawQuadTex(background, 0, 0, WIDTH, HEIGHT);
    menuUI.draw();
    updateButtons();
  }

}