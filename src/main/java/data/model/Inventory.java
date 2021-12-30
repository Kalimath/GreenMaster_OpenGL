package data.model;

import data.model.placeables.Placeable;
import data.model.placeables.species.Specie;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Inventory {
    private List<PlaceHolder> objects;

    public Inventory(){
        objects = new ArrayList<>();
    }

    public List<PlaceHolder> getObjects() {
        return objects;
    }

    public List<PlaceHolder> getPlants(){
        List<PlaceHolder> inventoryPlants = new ArrayList<>();
        for (PlaceHolder placeHolder: objects) {
            if(placeHolder.getFiller() instanceof Specie) inventoryPlants.add(placeHolder);
        }
        return inventoryPlants;
    }

    public void setObjects(@NotNull List<Placeable> placeables) {
        //TODO: setObjects aanpassen aan placeholder, zodat elke placeable in placeholder wordt gestoken
        for (Placeable p: placeables) {
            add(p);
        }
    }

    public void add(@NotNull Placeable placeable){
        //TODO: add aanpassen zodat placeable in placeholder wordt gestoken
        // en placeholder een constructor met enkel placeable en setters die andere variabelen juist zetten met de gegevens uit de placeable
        try {
            objects.add(new PlaceHolder(placeable));
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
}
