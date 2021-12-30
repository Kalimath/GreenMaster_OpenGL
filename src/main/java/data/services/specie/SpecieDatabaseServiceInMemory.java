package data.services.specie;

import data.model.placeables.species.Specie;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SpecieDatabaseServiceInMemory implements SpecieDatabaseServiceStrategy {
    private static List<Specie> species = new ArrayList();



    @Override
    public List<Specie> getAll() {
        return species;
    }

    @Override
    public void add(@NotNull Specie newSpecie) {
            species.add(newSpecie);
    }

    @Override
    public void addAll(List<Specie> newSpecies) {
        if(newSpecies.size()<1){
            throw new NullPointerException("list with new species is empty");
        }else{
            species.addAll(newSpecies);
        }

    }
}
