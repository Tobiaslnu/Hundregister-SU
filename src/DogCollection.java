// Tobias Ljungman tolj3334

import java.util.ArrayList;


public class DogCollection {
    
    private ArrayList<Dog> dogs = new ArrayList<>();


    //Kontroll om specifik hund finns i samling
    public boolean containsDog(Dog dog){
        if (dogs.contains(dog)){
            return true;
        }
        return false;
    }

    //Samma kontroll som ovan men baserat på hundens namn
    public boolean containsDog(String dogName) {
        if (getDog(dogName) != null){
            return true;
        }
        return false;
    }

    //Returnerar en hund baserat på namn.
    public Dog getDog(String dogName) {
        return dogs.stream().filter(dog -> dog.getName().equalsIgnoreCase(dogName)).findFirst().orElse(null);
    }
    //returnerar kopia på listan med hundobjekt
    public ArrayList<Dog> getDogs() {
        //Kopia skapas av listan
        ArrayList<Dog> dogsCopy = new ArrayList<>(dogs);

        //DogSorter används för att sortera kopian med DogNameComparator            
        DogSorter.sortDogs(new DogNameComparator(), dogsCopy);
    
        return dogsCopy;
    }
   
    //Returnerar sorterad lista med alla hundar som har en minst lika lång svans som argumentet
    public ArrayList<Dog> getDogsWithMinimumTailLength(double tailLength) {
        ArrayList<Dog> selectedDogs = new ArrayList<>();
      
        for (Dog dog : dogs) {
            if (dog.getTailLength() >= tailLength) {
                selectedDogs.add(dog);
            }
        }
        DogSorter.sortDogs(new DogTailNameComparator(), selectedDogs);
        return selectedDogs;
    }

    //Kontrollerar om ny hund redan existerar. Lägger endast till ny om den ej redan finns. 
    //Returnerar bool som visar om hund lagts till eller ej
    public boolean addDog(Dog newDog){
        if (!containsDog(newDog.getName())) {
            dogs.add(newDog);
            return true; 
        }
        return false;
    }
    //Tar bort specifik hund ur samling
    public boolean removeDog(Dog dogToRemove){
        if (dogToRemove.getOwner() != null) {
            return false; //Har hunden en ägare: avbryt borttagning
        }
        return dogs.remove(dogToRemove);
    }
    //Tar bort hund baserat på namn
    public boolean removeDog(String dogName){
        Dog dogToRemove = getDog(dogName);
        if (dogToRemove == null) {   
            return false;    
        } 
        if (dogs.isEmpty()){
            return false;
        }
        if (dogToRemove.getOwner() != null) {
            return false; //Har hunden en ägare: avbryt borttagning
        } 
        else{
            dogs.remove(dogToRemove);           
        }
        return true;
    }
}
