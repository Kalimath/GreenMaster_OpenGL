package data.services.specie;

import data.model.species.Specie;

import java.util.List;
import java.util.Optional;

public interface SpecieDatabaseServiceStrategy {
    Optional<List<Specie>> getAll();
    void add(Specie newSpecie);
    void addAll(List<Specie> newSpecies);
    //TODO

}
