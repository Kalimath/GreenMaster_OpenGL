package data.model;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Inventory {
    private List<Placeable> objects;

    public Inventory(){
        setObjects(new ArrayList<Placeable>());
    }

    public List<Placeable> getObjects() {
        return objects;
    }

    public List<Plant> getPlants(){
        List<Plant> inventoryPlants = new ArrayList<>();
        for (Placeable object: objects) {
            if(object instanceof Plant) inventoryPlants.add((Plant)object);
        }
        return inventoryPlants;
    }

    public void setObjects(@NotNull List<Placeable> objects) {
        if(this.objects == null){
            this.objects = objects;
        }else{
            this.objects.addAll(objects);
        }
    }

    public void add(@NotNull Placeable placeable){
        objects.add(placeable);
    }
}
