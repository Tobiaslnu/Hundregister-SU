// Tobias Ljungman tolj3334

import java.util.ArrayList;
import java.util.Comparator;

public class DogSorter {



    private static void swapDogs(ArrayList<Dog> dogs, int indexOne, int indexTwo) {
        //kontroll för att se om indexen är inom gränserna för listan dogs
        if(indexOne >= 0 && indexOne < dogs.size() && indexTwo >= 0 && indexTwo < dogs.size()) {
            //Byter plats på hundarna med index1 och index2 manuellt
            Dog temporary = dogs.get(indexOne);
            dogs.set(indexOne, dogs.get(indexTwo));
            dogs.set(indexTwo, temporary);
        }
        else {
            System.out.println("Ogiltiga index, inget byte av plats har genomförts");
        }
    }

    private static int nextDog(Comparator<Dog> comparator, ArrayList<Dog> dogs, int start) {
        int minIndex = start;

        for(int i = start + 1; i <dogs.size(); i++) {
            //Hunden på position i jämförs med den minsta hunden hittills
            if (comparator.compare(dogs.get(i), dogs.get(minIndex)) < 0) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static int sortDogs(Comparator<Dog> comparator, ArrayList<Dog> dogs) {
        //Antal byten lagras i denna variabel
        int numberOfSwaps = 0;

        for (int i = 0; i < dogs.size() - 1; i++) {
            //nextDog används för att hitta index för nästa hund i den sorterade delen
            int nextDogIndex = nextDog(comparator, dogs, i);

            //Om nextDogIndex är olika från i, byts platserna på hundarna
            if (nextDogIndex != i) {
                swapDogs(dogs, i, nextDogIndex);
                numberOfSwaps++; //Adderar byte till räknar-variabeln varje gång koden når hit
            }
        }

        return numberOfSwaps;
    }
    
}
    

