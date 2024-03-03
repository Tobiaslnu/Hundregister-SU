// Tobias Ljungman tolj3334


import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;

public class OwnerCollection {
    private static final int INITIAL_CAPACITY = 10; //Initiala storleken på array
    private Owner[] owners; //Array för ägare
    private int size; //Representerar antal ägare i array

    //Konstruktor för att skapa en samling med ägare
    public OwnerCollection() {
        owners = new Owner[INITIAL_CAPACITY];
        size = 0;
    }

    //Metod för att försäkra att arrayen har tillräcklig storlek/kapacitet
    private void ensureCapacity() {
        if (size == owners.length) {
            //Arrayen dupliceras om den är full
            owners = Arrays.copyOf(owners, size * 2);
        }
    }

    //Kontrollmetod kontrollerar om en specifik ägare finns i samlingen
    public boolean containsOwner(Owner owner) {
        for (int i = 0; i < size; i++) {
            if (owners[i].equals(owner)) {
                return true;
            }
        }
        return false;
    }

    //Kontrollmetod kontrollerar om en specifik ägare finns i samlingen baserat på dess namn
    public boolean containsOwner(String ownerName) {
        for (int i = 0; i < size; i++) {
            if (owners[i].getName().equalsIgnoreCase(ownerName)) {
                return true;
            }
        }
        return false;
    }

    //Getter-metod för att hämta en ägare med ett visst namn i samlingen
    public Owner getOwner(String ownerName) {
        for (int i = 0; i < size; i++) {
            if (owners[i].getName().equalsIgnoreCase(ownerName)) {
                return owners[i];
            }
        }
        return null; // Returnera null om ägaren inte hittas
    }

    //Getter-metod för att hämta alla ägare i en ArrayList
    public ArrayList<Owner> getOwners() {
        return new ArrayList<>(Arrays.asList(Arrays.copyOf(owners, size)));

    }

    //Kontrollerar om ny ägare redan existerar. Lägger endast till ny om den ej redan finns i samling. 
    //Returnerar bool som visar om ägaren lagts till eller ej
    public boolean addOwner(Owner newOwner) {
        if (!containsOwner(newOwner.getName())) {
            ensureCapacity(); //Ser till att arrayen har tillräcklig kapacitet
            owners[size++] = newOwner;
            //sorterar ägarna i bokstavsordning
            Arrays.sort(owners, 0, size, Comparator.comparing(Owner::getName));

            return true;

        }
        return false;
    }

    //Metod tar bort en specifik ägare ur samling
    public boolean removeOwner(Owner ownerToRemove) {
        for (int i = 0; i < size; i++) {
            if (owners[i].equals(ownerToRemove)) {
                //Om ägare har hund/hundar: avbryt
                if(owners[i].getDogs().size() > 0){
                    return false;
                }
                //Flyttar de efterföljande ägarna ett steg framåt i samling 
                System.arraycopy(owners, i + 1, owners, i, size - i - 1);
                owners[--size] = null; //Sista elementet sätts till null
                return true;
            }
        }
        return false;
    }

    //Liknande metod som ovan men tar bort en specifik ägare ur samling baserat på dess namn
    public boolean removeOwner(String ownerName) {
        for (int i = 0; i < size; i++) {
            if (owners[i].getName().equalsIgnoreCase(ownerName)) { 
                
                if(owners[i].getDogs().size() > 0){
                    return false;
                }
                System.arraycopy(owners, i + 1, owners, i, size - i - 1);
                owners[--size] = null; 
                return true;
            }
        }
        return false;
    }
}

