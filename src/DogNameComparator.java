// Tobias Ljungman tolj3334

import java.util.Comparator;
//Comparator för att jämföra hundar baserat på dess namn
public class DogNameComparator implements Comparator<Dog> {

    public int compare(Dog first, Dog second){

        return first.getName().compareTo(second.getName());
        //Returnerar negativt heltal om första namnet mindre än andra
        //Returnerar positivt heltal om första namnet större än andra
        //Är de lika returneras 0
    }
    
}
