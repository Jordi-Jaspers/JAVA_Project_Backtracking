package backtracking2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * An exercise on Backtracking.
 * Find an algorithm that solves the problem of Philosophers Revisited 
 * with the input values of a JSON-file. (persoon, vrienden, nietvrienden)
 * 
 * @author Jordi Jaspers
 * @version V2.0
 */
public class BackTracking2 {

   private static backtracking2.Person[] persons;
   private static ArrayList<Person> personList;
   private static ArrayList<Person> table;
   private static ArrayList<ArrayList<Person>> result;
    
    /**
     * The main static method that runs at the start of the program.
     * 
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        backtracking2.Input[] inputFiles = backtracking2.Input.createFromJson(args[0]);
        
        ArrayList<JsonObject> solutions = new ArrayList<>();
       
        //voor iedere invoer een de arrays legen en solven.
        for (backtracking2.Input inputFile : inputFiles) {
            table = new ArrayList<>();
            personList = new ArrayList<>();
            result = new ArrayList<>();
            persons = inputFile.getInvoer();

            personList.addAll(Arrays.asList(persons));

            solveTable(personList);

            solutions.add(getSolutionByID(result));
            System.out.println("Gevonden oplossingen: " + result.size());
        }
        backtracking2.Output.saveToJson(solutions, args[0]);
    }
    
    /**
     * This method will add a person to the table if possible.
     * 
     * @param list list of persons needed to add to the table
     */
    private static void solveTable(ArrayList<Person> list)
    {
        for (Person person : list) {
            table = new ArrayList<>();
            table.add(person);
            checkPosibilities(person, table);
        }
    }
    
    /**
     * checks all the possibilities for each person we add at the table, then adds
     * them to the results. (=left-right tree structure method)
     * 
     * @param person person you are trying to add.
     * @param table table of persons
     */
    private static void checkPosibilities(Person person, ArrayList<Person> table) 
    {
        ArrayList<Person> peopleToCheck = (ArrayList<Person>) personList.clone();
        
        peopleToCheck.remove(person);
        
        for (Person x : GetNietVriendenAsList(person)) {
            int value = -1;
            value = peopleToCheck.indexOf(x);
            if (value != -1) {
                peopleToCheck.remove(value);
            }
        }
        
        for (Person x : table) {
            int value = -1;
            value = peopleToCheck.indexOf(x);
            if (value != -1) {
                peopleToCheck.remove(value);
            }
        }        
        
        for (Person possibleFriend : peopleToCheck) {
            if (!GetNietVriendenAsList(possibleFriend).contains(person))
            {
                ArrayList<Person> clonedTable = (ArrayList<Person>) table.clone();
                clonedTable.add(possibleFriend);
                
                if (clonedTable.size() == personList.size() && !GetNietVriendenAsList(clonedTable.get(0)).contains(possibleFriend))
                {
                    result.add(clonedTable);
                    return;
                }                
                checkPosibilities(possibleFriend, clonedTable);
            }
        }
    }
    
    /**
     * Converts the Array to a list and returns the not-friends as list.
     * 
     * @param person person who his not friends you are searching.
     * @return nFriendsList
     */
    private static ArrayList<Person> GetNietVriendenAsList(Person person){

        ArrayList<Person> nFriendsList = new ArrayList<>();
        
        int[] notFriends = person.getNietvrienden();
        for (int notFriendId : notFriends) {
            nFriendsList.add(personList.get(notFriendId - 1));
        }
        
        return nFriendsList;
    }
    
    /**
     * Returns the solutions as a List of person ID's çause the teacher wants a list like that.
     * 
     * @param result results of the lists in class Person.
     * @return finalResult as a list of Integers.
     */
    private static JsonObject getSolutionByID(ArrayList<ArrayList<Person>> result){
        ArrayList<ArrayList<Integer>> finalResult = new ArrayList<>();
        
        for (ArrayList<Person> arrayList : result) {
            ArrayList<Integer> tempList = new ArrayList<>();
            for (Person person : arrayList) {
                tempList.add(person.getPersoon());
            }
            finalResult.add(tempList);
        }
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement element = gson.toJsonTree(finalResult, new TypeToken<ArrayList<ArrayList<Integer>>>() {}.getType());
        JsonArray jsonArray = element.getAsJsonArray();
        JsonObject obj = new JsonObject();
        obj.add("oplossingen: ", jsonArray);
        
        return obj;
    }
}
