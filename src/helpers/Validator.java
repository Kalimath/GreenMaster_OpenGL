package helpers;

import data.exception.InvalidPlaceException;
import data.model.Construction;
import data.model.Placable;
import data.model.Plant;
import data.ui.Tile;
import data.ui.TileGrid;
import data.ui.TileType;
import data.ui.TileZone;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static java.util.stream.Collectors.toSet;

public class Validator {

    public static boolean IsValidPlace(@NotNull Placable object, @NotNull Tile tile){
        boolean result = false;
        if(tile.getTileType().isPlantable && object instanceof Plant){
            result = true;
        }else if(tile.getTileType().equals(TileType.Water) && object instanceof Plant && ((Plant) object).getType().isPlantableInWater){
            result = true;
        }else if(tile.getTileType().isWorkable && object instanceof Construction){
            result = true;
        }else{
            throw new InvalidPlaceException(object.getName()+" could not be placed on the given location");
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
            if (tZone.getZone().stream()
                    .anyMatch(t -> t.equals(tile))){
                isValid = false;
            }
        }

        return isValid;
    }

    public static void ValidateAndDraw(@NotNull Placable object, @NotNull Tile tile){
        if(IsValidPlace(object, tile)){
            object.draw();
        }else{
            throw new InvalidPlaceException(object.getName()+" could not be placed on the given location");
        }
    }


    //checks if a TileZone can be placed in the grid on a specific location without interfering with other zones
    public static boolean isValidTileZoneForGrid(@NotNull TileZone zone, @NotNull TileGrid grid){
        boolean isValid = true;
        for (Tile t : zone.getZone()) {
            if (!isValidTilePlaceForZone(t,zone,grid)) isValid = false;
        }
        return isValid;
    }

}
