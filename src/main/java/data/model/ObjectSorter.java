package data.model;

import data.model.placeables.species.Specie;
import data.ui.TileZone;
import org.jetbrains.annotations.NotNull;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class ObjectSorter {

    public static Deque<Specie> arrangePlantsBySize(@NotNull List<Specie> unsortedPlants){
        Deque<Specie> sortedPlants = new LinkedList<>();
        for (Specie p: unsortedPlants) {
            if(p.getWidth()>=sortedPlants.getFirst().getWidth()){
                sortedPlants.addFirst(p);
            }
        }
        return sortedPlants;
    }

    public static Deque<TileZone> arrangeTileZonesBySize(@NotNull List<TileZone> unsortedZones){
        Deque<TileZone> sortedZones = new LinkedList<>();

            for (TileZone p: unsortedZones) {
                if(sortedZones== null||sortedZones.isEmpty()||p.calculateWidth()*p.calculateHeight()>= sortedZones.getFirst().calculateWidth()*sortedZones.getFirst().calculateHeight()){
                    sortedZones.addFirst(p);
                }
            }


        return sortedZones;
    }

//    public static Deque<Object> ArrangeZonesAndPlantsBySize(@NotNull List<Plant> unsortedPlants,@NotNull List<TileZone> unsortedZones){
//        Deque<Plant> sortedObjects = new LinkedList<>();
//        for (Plant p: unsortedPlants) {
//            if((p.width^2)>=(sortedObjects.getFirst().calculateWidth()*sortedObjects.getFirst().calculateHeight())){
//                sortedObjects.addFirst(p);
//            }
//        }
//        return sortedObjects;
//    }
}
