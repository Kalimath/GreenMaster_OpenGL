package data.model;

import data.ui.TileGrid;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import static helpers.OpenGLAssistent.*;

public class EditorTool extends DesignTool {
     private int index;

    public EditorTool(TileGrid grid) {
        super(grid);
        index = 1;
    }

    @Override
    public void set(){
        if(Mouse.getX() < (TILESIZE*ROWS) && Mouse.getY() < (TILESIZE*COLUMNS)){
            //System.out.println("Mouse X: " + Mouse.getX() + "Tile x: " + (Math.floor(Mouse.getX() / TILESIZE)) + ", Tile Y: " + ((HEIGHT - Mouse.getY() - 1) / TILESIZE));
            grid.setTile((int)Math.floor(Mouse.getX()/TILESIZE), (int)Math.floor((HEIGHT-Mouse.getY()-1)/TILESIZE), tileTypes[index]);
        }

    }

    @Override
    public void update(){
        if (Mouse.isButtonDown(0)){
            set();
        }
        while(Keyboard.next()){
            if(Keyboard.getEventKey() == Keyboard.KEY_TAB && Keyboard.getEventKeyState()){
                moveIndex();
            }
        }
    }

    private void moveIndex() {
        if((index+1)>=tileTypes.length){
            index = 0;
        }else{
            index++;
        }
    }
}
