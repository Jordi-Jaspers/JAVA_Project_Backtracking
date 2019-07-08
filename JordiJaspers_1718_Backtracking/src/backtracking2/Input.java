package backtracking2;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * An exercise on Backtracking.
 * Find an algorithm that solves the problem of Philosophers Revisited 
 * with the input values of a JSON-file. (persoon, vrienden, nietvrienden)
 * 
 * @author Jordi Jaspers
 * @version V1.0
 */
public class Input {
    
    private Person[] invoer;
    
    /**
     * A method to process the file to usable input-values.
     * 
     * @param file JSON-File with input-values.
     * @return Input-Values of the file.
     */
     public static Input[] createFromJson(String file) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(new FileReader(file), Input[].class);
        } catch (FileNotFoundException ex) {
	     System.out.println("Bestand niet gevonden.");
        }
        return null;
    }

     /**
      * Gets all the persons of every input.
      * 
      * @return persons
      */
    public Person[] getInvoer() {
        return invoer;
    }     
}
