// Tobias Ljungman tolj3334

import java.util.Comparator;

public class DogTailNameComparator implements Comparator<Dog> {
    
    private final DogTailComparator tailComparator = new DogTailComparator();
    private final DogNameComparator nameComparator = new DogNameComparator();

    public int compare(Dog first, Dog second){
        int tailComparison = tailComparator.compare(first, second);

        if (tailComparison == 0) {
            return nameComparator.compare(first, second);
        }
        else{
            return tailComparison;
        }
    }

}
