// Tobias Ljungman tolj3334

import java.util.ArrayList;

public class Owner implements Comparable<Owner> {

    private String name;
    private ArrayList<Dog> dogs; //Denna lagrar ägarens hundar

    //Konstruktor skapar ny ägare
    public Owner(String name){
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        this.dogs = new ArrayList<>(); //Tom lista som kan lagrar ägarens hundar
    }
    //Getter-metod som möjliggör hämtning av ägarens namn
    public String getName(){
        return name;
    }
    //möjliggör hämtning av ägarens hundar
    public ArrayList<Dog> getDogs() {
        //Returnerar en oföränderlig kopia för att undvika att ändra på den faktiska listan
        ArrayList<Dog> sortedDogs = new ArrayList<>(dogs);
        DogSorter.sortDogs(new DogNameComparator(), sortedDogs);
        return sortedDogs;
    }

    //Metod för att lägga till en hund till ägarens hundlista
    public boolean addDog(Dog dog) {
        if (dog.getOwner() != null && !dog.getOwner().equals(this)) {
            return false;
        }
        if (dog != null && !dogs.contains(dog)) {
            dogs.add(dog);
            return true;
        }
        return false;
    }

    //Metod för att ta bort en hund från ägarens hundlista
    public boolean removeDog(Dog dog) {
        if (dog != null && dogs.contains(dog)) {
            dogs.remove(dog);
            return true;
        }
        return false;
    }

    public int compareTo(Owner other){
        return this.name.compareTo(other.name);
    }

    public String toString(){       
        StringBuilder dogList = new StringBuilder();

    for (Dog dog : dogs) {
        dogList.append(dog.getName()).append(" ");
    }

    if (dogs.size() == 0) {
        dogList.append("No owned dogs");
    }

    return String.format("%s - %s", name, dogList.toString());
    }
}