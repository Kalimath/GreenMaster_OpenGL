package helpers;

import data.exception.InvalidPlaceException;
import data.model.Construction;
import data.model.Placeable;
import data.model.Plant;
import data.ui.Tile;
import data.ui.TileGrid;
import data.ui.TileType;
import data.ui.TileZone;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Validator {

    public static boolean IsValidPlace(@NotNull Placeable object, @NotNull Tile tile, @NotNull TileGrid grid){
        boolean result = false;
        try{
            if(tile.getTileType().isPlantable && object instanceof Plant){
                result = true;
            }else if(tile.getTileType().equals(TileType.Water) && object instanceof Plant && ((Plant) object).getType().isPlantableInWater){
                result = true;
            }else if(tile.getTileType().isBuildable && object instanceof Construction){
                result = true;
            }else if (grid.getZoneFromPlaceable(object).getZone().contains(tile)){
                result = true;
            }else{
                throw new InvalidPlaceException(object.getName()+" could not be placed on the given location");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //checks if a tile can be placed in the grid on a specific location without interfering with zones
    public static boolean isValidTilePlace(@NotNull Tile tile, @NotNull TileGrid grid){
        boolean isValid = true;
        for (TileZone tZone: grid.getZones()) {
            if (tZone.getZone().stream()
                    .anyMatch(t -> t.equals(tile))){
                isValid = false;
            }
        }
        return isValid;
    }

    //checks if a tile from a zone can be placed in the grid without interfering with zones
    public static boolean isValidTilePlaceForZone(@NotNull Tile tile, TileZone zone, @NotNull TileGrid grid){
        boolean isValid = true;

        //Create list with all other zones in the grid
        List<TileZone> zonesWithoutCurrent = grid.getZones();
        if (zonesWithoutCurrent.contains(zone)) zonesWithoutCurrent.remove(zone);

        for (TileZone tZone: zonesWithoutCurrent) {
            if (!tZone.equals(zone) && tZone.getZone().stream().anyMatch(tile1 -> tile.equals(tile))){
                System.out.println("Hash tZone: " + tZone.hashCode());
                System.out.println("Hash zone: " + zone.hashCode());
                isValid = false;
            }
        }

        return isValid;
    }

//    public static void ValidateAndDraw(@NotNull Placeable object, @NotNull Tile tile){
//        if(IsValidPlace(object, tile)){
//            object.draw();
//        }else{
//            throw new InvalidPlaceException(object.getName()+" could not be placed on the given location");
//        }
//    }


    //checks if a TileZone can be placed in the grid on a specific location without interfering with other zones
    public static boolean isValidTileZoneForGrid(@NotNull TileZone zone, @NotNull TileGrid grid){
        boolean isValid = true;
        for (Tile t : zone.getZone()) {
            if (grid.isInTileZone(t.getXPlace(),t.getYPlace())) {
                isValid = false;
            }
        }
        return isValid;
    }

}
