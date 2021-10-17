package data.model;

import data.exception.AutoGenerateException;
import data.ui.TileGrid;

public class ToolController {
    private TileGrid grid;
    private DesignTool editorTool;
    private DesignTool autoGenerateTool;
    private boolean allowedToGenerate;

    public ToolController(TileGrid grid, Inventory inventory){
        this.grid = grid;
        allowedToGenerate = true;
        editorTool = new EditorTool(grid);
        autoGenerateTool = new AutoGenerateTool(grid, inventory);
    }

    public void edit() {
        try {
            //System.out.println("editor selected");
            editorTool.update();
            allowedToGenerate = true;
        }catch (Exception e){
            throw new IllegalStateException("editor tool gave an error\n\n" + e.getMessage());
        }
    }


    public void autoGenerate() {
        if(allowedToGenerate){
            try {
                System.out.println("generate selected");
                autoGenerateTool.update();
                allowedToGenerate = false;
            }catch (Exception e){
                throw new AutoGenerateException("auto-Generator tool gave an error\n\n" + e.getMessage());
            }
        }else{
            System.out.println("Warning: generation already executed!");
        }
    }
}
