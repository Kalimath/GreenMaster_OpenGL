package data.services.specie;

import com.google.gson.Gson;
import data.model.species.Specie;
import data.services.specie.SpecieDatabaseServiceStrategy;
import org.jetbrains.annotations.NotNull;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class SpecieDatabaseServiceJSON implements SpecieDatabaseServiceStrategy {
    private final String JSON_SPICIES;
    private Gson gson = new Gson();

    public SpecieDatabaseServiceJSON() {
        //JSON_SPICIES = System.getProperty("specieUrl");
        JSON_SPICIES = "D:\\Hobby\\Moestuin\\Software\\Software met OpenGL\\GreenMaster_OpenGL\\src\\res\\doc\\species.json";
    }

    @Override
    public Optional<List<Specie>> getAll() {
        List<Specie> species = new ArrayList<>();
        try (Reader reader = new FileReader(JSON_SPICIES)) {

            // Convert JSON File to Java Object

            species = gson.fromJson(reader, ArrayList.class);

            // print object
            System.out.println(species);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.of(species);
    }

    @Override
    public void add( @NotNull Specie newSpecie) {
        try (FileWriter writer = new FileWriter(JSON_SPICIES)) {
            gson.toJson(newSpecie, writer);
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
