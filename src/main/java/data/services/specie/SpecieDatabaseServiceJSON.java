package data.services.specie;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.model.placeables.Placeable;
import data.model.placeables.species.Specie;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SpecieDatabaseServiceJSON implements SpecieDatabaseServiceStrategy {
    private final String JSON_SPICIES;
    ObjectMapper mapper = new ObjectMapper();


    public SpecieDatabaseServiceJSON() {
        //JSON_SPICIES = System.getProperty("specieUrl");
        JSON_SPICIES = "src\\main\\java\\res\\doc\\species.json";
    }

    @Override
    public List<Specie> getAll() {
        List<Specie> species = new ArrayList<>();
        try (Reader reader = new FileReader(JSON_SPICIES)) {

            // Convert JSON File to Java Object
            species.addAll(Arrays.asList(mapper.readValue(new URL(JSON_SPICIES), Specie[].class)));

            // print object
            System.out.println(species);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return species;
    }

    @Override
    public void add(@NotNull Specie newSpecie) {
        try (FileWriter writer = new FileWriter(JSON_SPICIES)) {
            mapper.writeValue(new File(JSON_SPICIES), newSpecie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAll(@NotNull List<Specie> newSpecies) {
        if(newSpecies.size()>0){
            for (Specie s:newSpecies) {
                add(s);
            }
        }
    }
}
