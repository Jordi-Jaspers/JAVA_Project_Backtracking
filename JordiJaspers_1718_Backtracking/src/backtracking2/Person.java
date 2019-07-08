package backtracking2;


/**
 * An exercise on Backtracking.
 * Find an algorithm that solves the problem of Philosophers Revisited 
 * with the input values of a JSON-file. (persoon, vrienden, nietvrienden)
 * 
 * @author Jordi Jaspers
 * @version V1.0
 */
public class Person {
    
    private int persoon;
    private int[] vrienden;
    private int[] nietvrienden;

    /**
     * Constructor of the Person Object.
     * 
     * @param persoon id of the person
     * @param vrienden friends of the person
     * @param nietvrienden not-friends of the person
     */
    public Person(int persoon, int[] vrienden, int[] nietvrienden) {
        this.persoon = persoon;
        this.vrienden = vrienden;
        this.nietvrienden = nietvrienden;
    }
    
    /**
     * getter for the id of a person.
     * 
     * @return persoon
     */
    public int getPersoon() {
        return persoon;
    }

    /**
     * getter for all the friends of a person.
     * 
     * @return vrienden
     */
    public int[] getVrienden() {
        return vrienden;
    }

    /**
     * getter for al the not-friends of a person.
     * 
     * @return nietVrienden
     */
    public int[] getNietvrienden() {
        return nietvrienden;
    }
}
