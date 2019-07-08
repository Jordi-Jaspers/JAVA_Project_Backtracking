package backtracking2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * An exercise on Backtracking.
 * Find an algorithm that solves the problem of Philosophers Revisited 
 * with the input values of a JSON-file. (persoon, vrienden, nietvrienden)
 * 
 * @author Jordi Jaspers
 * @version V1.0
 */
public class Output {
    
    /**
     * This method will write all the solutions in a JSON-formatted text file.
     * 
     * @param solution The solutions of the algorithm.
     * @param args a string name for the new file.
     * @throws IOException An exception if there would be no input-file.
     */
    public static void saveToJson(ArrayList<JsonObject> solution, String args) throws IOException{
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(solution);
        
        try (FileWriter file = new FileWriter(args + "uit.txt")) {
            file.write(jsonOutput);
            file.close();
        }
        
    }
    
}
