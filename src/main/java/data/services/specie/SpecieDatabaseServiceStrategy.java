package data.services.specie;

import data.model.placeables.species.Specie;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface SpecieDatabaseServiceStrategy {
    List<Specie> getAll();
    void add(@NotNull Specie newSpecie);
    void addAll(List<Specie> newSpecies);
    //TODO

}
