package data.model;

import org.jetbrains.annotations.NotNull;

public class ProgramStateManager {
    private ProgramState currentState = ProgramState.MAINMENU;

    private static ProgramStateManager INSTANCE;
    private ProgramStateManager(){

    }

    public static ProgramStateManager getInstance(){
        if(INSTANCE==null){
           INSTANCE = new ProgramStateManager();
        }
            return INSTANCE;
    }

    public ProgramState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(@NotNull ProgramState state) {
        currentState = state;
    }
}
