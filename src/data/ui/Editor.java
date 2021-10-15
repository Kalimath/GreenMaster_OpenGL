package data.ui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.Arrays;

import static helpers.OpenGLAssistent.*;

public class Editor {
     private TileGrid grid;
     private TileType[] tiletypes;
     private int index;

    public Editor(TileGrid grid) {
        this.grid = grid;
        tiletypes = TileType.values();
        index = 0;
    }

    public void setTile(){
        System.out.println("Mouse X: " + Mouse.getX() + "Tile x: " + (Math.floor(Mouse.getX() / TILESIZE)) + ", Tile Y: " + ((HEIGHT - Mouse.getY() - 1) / TILESIZE));
        grid.setTile((int)Math.floor(Mouse.getX()/TILESIZE), (int)Math.floor((HEIGHT-Mouse.getY()-1)/TILESIZE), tiletypes[index]);
    }

    public void update(){
        if (Mouse.isButtonDown(0)){
            setTile();
        }
        while(Keyboard.next()){
            if(Keyboard.getEventKey() == Keyboard.KEY_TAB && Keyboard.getEventKeyState()){
                moveIndex();
            }
        }
    }

    private void moveIndex() {
        if((index+1)>=tiletypes.length){
            index = 0;
        }else{
            index++;
        }
    }
}
