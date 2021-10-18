package UI;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import static helpers.OpenGLAssistent.*;

public class UI {

  private ArrayList<Button> buttonList;
  private ArrayList<Menu> menuList;

  public UI() {
    buttonList = new ArrayList<Button>();
    menuList = new ArrayList<Menu>();
  }

  public void addButton(String name, String textureName, int x, int y) {
    buttonList.add(new Button(name, loadTexture(textureName), x, y));
  }

  public boolean isButtonClick(String buttonName) {
    Button b = getButton(buttonName);
    float mouseY = HEIGHT - Mouse.getY() - 1;
    if (Mouse.getX() > b.getX() && Mouse.getX() < (b.getX() + b.getWidth()) && mouseY > b.getY()
            && mouseY < (b.getY() + b.getHeight())) {
      return true;
    }
    return false;
  }

  private Button getButton(String buttonName) {
    for (Button b : buttonList) {
      if (b.getName().equals(buttonName)) {
        return b;
      }
    }
    return null;
  }

  public void createMenu(String menuName, int x, int y, int width, int height, int optionWidth, int optionHeight) {
    menuList.add(new Menu(menuName, x, y, width, height, optionWidth, optionHeight));

  }

  public Menu getMenu(String menuName) {
    for (Menu m : menuList) {
      if (menuName.equals(m.getName())) {
        return m;
      }
    }
    return null;
  }

  public void draw() {
    for (Button b : buttonList) {
      drawQuadTex(b.getTexture(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
    }
    for (Menu m : menuList) {
      m.draw();
    }
  }

  public class Menu {

    private ArrayList<Button> menuButton;
    private int x, y, width, height, buttonAmount, optionWidth, optionHeight, padding;
    String menuName;

    public Menu(String menuName, int x, int y, int width, int height, int optionWidth, int optionHeight) {
      this.menuName = menuName;
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.optionHeight = optionHeight;
      this.optionWidth = optionWidth;
      this.buttonAmount = 0;
      this.menuButton = new ArrayList<Button>();
    }

    public void addButton(Button b) {
      setButton(b);
    }

    public void quickAdd(String name, String buttonTextureName) {
      Button b = new Button(name, loadTexture(buttonTextureName), 0, 0);
      setButton(b);
    }

    public void setButton(Button b) {
      if (optionWidth != 0) {
        b.setY(y + (buttonAmount / optionWidth) * TILESIZE);
      }
      b.setX(x + (buttonAmount % optionWidth) * TILESIZE);
      buttonAmount++;
      menuButton.add(b);
    }

    public boolean isButtonClick(String buttonName) {
      Button b = getButton(buttonName);
      float mouseY = HEIGHT - Mouse.getY() - 1;
      if (Mouse.getX() > b.getX() && Mouse.getX() < (b.getX() + b.getWidth()) && mouseY > b.getY()
              && mouseY < (b.getY() + b.getHeight())) {
        return true;
      }
      return false;
    }

    private Button getButton(String buttonName) {
      for (Button b : menuButton) {
        if (b.getName().equals(buttonName)) {
          return b;
        }
      }
      return null;
    }

    public void draw() {
      for (Button b : menuButton) {
        drawQuadTex(b.getTexture(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
      }
    }

    public String getName() {
      return menuName;
    }
  }
}