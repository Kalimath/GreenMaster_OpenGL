package UI;

import data.model.ToolController;
import org.newdawn.slick.opengl.Texture;

public abstract class LargeMenu {

  protected ToolController handler;
  protected Texture background;
  protected UI menuUI;
  protected boolean flag;

  public LargeMenu(ToolController handler, Texture background) {
    this.background = background;
    menuUI = new UI();
    flag = false;
    this.handler = handler;
  }
  //check if button clicked
  abstract void updateButtons();

  public abstract void update();

}