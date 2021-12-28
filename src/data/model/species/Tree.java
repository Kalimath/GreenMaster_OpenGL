package data.model.species;

import org.jetbrains.annotations.NotNull;

public class Tree extends Specie {
    public Tree(@NotNull String name, @NotNull String info, @NotNull String texturePath, boolean isWide, boolean isHigh, boolean isPlantableInWater, boolean isPlantableInPot, double metricWidth, double metricHeight, double metricLength) {
        super(name, info, texturePath, isWide, isHigh, isPlantableInWater, isPlantableInPot, metricWidth, metricHeight, metricLength);
    }
}
