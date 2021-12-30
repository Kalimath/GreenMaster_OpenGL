package helpers;

import data.exception.InvalidPlaceException;
import data.model.placeables.Structure;
import data.model.PlaceHolder;
import data.model.placeables.species.Specie;
import data.ui.grid.GardenGrid;
import data.ui.tiles.GroundTile;
import data.ui.TileType;
import data.ui.TileZone;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Validator {

    public static boolean IsValidPlace(@NotNull PlaceHolder object, @NotNull GroundTile groundTile, @NotNull GardenGrid grid){
        boolean result = false;
        try{
            if(groundTile.getTileType().isPlantable && object.getFiller() instanceof Specie){
                result = true;
            }else if(groundTile.getTileType().equals(TileType.Water) && object.getFiller() instanceof Specie && ((Specie) object.getFiller()).isPlantableInWater()){
                result = true;
            }else if(groundTile.getTileType().isBuildable && object.getFiller() instanceof Structure){
                result = true;
            }else if (grid.getZoneFromPlaceHolder(object).getZone().contains(groundTile)){
                result = true;
            }else{
                throw new InvalidPlaceException(object.getType()+" could not be placed on the given location");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //checks if a groundTile can be placed in the grid on a specific location without interfering with zones
    public static boolean isValidTilePlace(@NotNull GroundTile groundTile, @NotNull GardenGrid grid){
        boolean isValid = true;
        for (TileZone tZone: grid.getZones()) {
            if (tZone.getZone().stream()
                    .anyMatch(t -> t.equals(groundTile))){
                isValid = false;
            }
        }
        return isValid;
    }

    //checks if a groundTile from a zone can be placed in the grid without interfering with zones
    public static boolean isValidTilePlaceForZone(@NotNull GroundTile groundTile, TileZone zone, @NotNull GardenGrid grid){
        boolean isValid = true;

        //Create list with all other zones in the grid
        List<TileZone> zonesWithoutCurrent = grid.getZones();
        if (zonesWithoutCurrent.contains(zone)) zonesWithoutCurrent.remove(zone);

        for (TileZone tZone: zonesWithoutCurrent) {
            if (!tZone.equals(zone) && tZone.getZone().stream().anyMatch(tile1 -> groundTile.equals(groundTile))){
                System.out.println("Hash tZone: " + tZone.hashCode());
                System.out.println("Hash zone: " + zone.hashCode());
                isValid = false;
            }
        }

        return isValid;
    }

//    public static void ValidateAndDraw(@NotNull PlaceHolder object, @NotNull GroundTile groundTile){
//        if(IsValidPlace(object, groundTile)){
//            object.draw();
//        }else{
//            throw new InvalidPlaceException(object.getName()+" could not be placed on the given location");
//        }
//    }


    //checks if a TileZone can be placed in the grid on a specific location without interfering with other zones
    public static boolean isValidTileZoneForGrid(@NotNull TileZone zone, @NotNull GardenGrid grid){
        boolean isValid = true;
        for (GroundTile t : zone.getZone()) {
            if (grid.isInTileZone(t.getXPlace(),t.getYPlace())) {
                isValid = false;
            }
        }
        return isValid;
    }

}
