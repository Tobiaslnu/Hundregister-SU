// Tobias Ljungman tolj3334

import java.util.ArrayList;


public class DogRegister {
    
    private static final String REGISTER_NEW_OWNER_CMD = "REGISTER NEW OWNER";
    private static final String REMOVE_OWNER_CMD = "REMOVE OWNER";
    private static final String REGISTER_NEW_DOG_CMD = "REGISTER NEW DOG";
    private static final String INCREASE_AGE_CMD = "INCREASE AGE";
    private static final String REMOVE_DOG_CMD = "REMOVE DOG";
    private static final String GIVE_DOG_TO_OWNER_CMD = "GIVE DOG TO OWNER";
    private static final String REMOVE_DOG_FROM_OWNER_CMD = "REMOVE DOG FROM OWNER";
    private static final String LIST_DOGS_CMD = "LIST DOGS";
    private static final String LIST_OWNERS_CMD = "LIST OWNERS";
    private static final String EXIT_CMD = "EXIT";

    private InputReader input = new InputReader(); //skapar instans av klassen som läser input
    private OwnerCollection ownerCollection = new OwnerCollection();
    private DogCollection dogCollection = new DogCollection();
    
    

    //metod som skriver ut "menynvalen"
    private void mainMenuStrings(){
        System.out.println("Dogregister\n");
        System.out.println("Register new owner");
        System.out.println("Remove owner");
        System.out.println("Register new dog");
        System.out.println("Increase age");
        System.out.println("Remove dog");
        System.out.println("Give dog to owner");
        System.out.println("Remove dog from owner");
        System.out.println("List owners");
        System.out.println("List dogs");
        
        System.out.println("Exit\n");

    }
    
    //metod som läser in och registrerar ny användare
    private void registerNewOwner() {
        String ownerName;
        //do-while som fortsätter ställa frågan om användaren gör blanka inmatningar
        do {
            System.out.println("Enter the name of the new owner:");
            ownerName = input.readString("Name"); 
    
            if (ownerName.isBlank()) {
                System.out.println("Error: A blank string is not allowed, try again");
            }
    
        } while (ownerName.isBlank());
    
        Owner newOwner = new Owner(ownerName);
    
        if (ownerCollection.addOwner(newOwner)) {
            System.out.println("The owner " + newOwner.getName() + " has been added to the register");
            System.out.println("Returning to the main menu...\n");
        } else {
            System.out.println("Error: Owner " + newOwner.getName() + " already exists or invalid input. Please try again.");
        }
    }
    
    //metod som tar bort en ägare
    private void removeOwner(){
        if (ownerCollection.getOwners().isEmpty()) {
            System.out.println("Error: No owners in register.");
            return;        
        }
        System.out.println("Enter the name of the owner you want to remove");
        String ownerNameToRemove = input.readString("Name");
        Owner ownerToRemove = ownerCollection.getOwner(ownerNameToRemove);

        if(ownerToRemove == null){
            System.out.println("Error: The owner " + ownerNameToRemove + " does'nt exist.");
            return;
        } 
        
            
        //kontroll för att se om ägaren har hundar
        if (!ownerToRemove.getDogs().isEmpty()) {
            //loopar genom ägarens hundar och ta bort dem en efter en från ägaren
            for (Dog dogInList : new ArrayList<>(ownerToRemove.getDogs())) {
                dogInList.setOwner(null);
                dogCollection.removeDog(dogInList);
                ownerToRemove.removeDog(dogInList);
            }
            System.out.println("Dogs owned by " + ownerToRemove.getName() + " have been removed.");
        }

        //removeOwner-metoden i OwnerCollection anropas för att ta bort ägaren
        if (ownerCollection.removeOwner(ownerToRemove)) {
            System.out.println("Owner removed successfully!");
            System.out.println("You removed: " + ownerToRemove);
        } else {
            System.out.println("Error: Failed to remove the owner " + ownerNameToRemove);
        }
        
    }
    
    //metod för registrering av ny hund
    private void registerNewDog(){
        String dogName; //loopar tills ett giltigt namn angetts
        do {
            dogName = input.readString("Enter the name of the new dog:");
            
            if (dogName.isBlank()) {
                System.out.println("Error: A blank string is not allowed for the dog's name. Please try again.");
            }
    
        } while (dogName.isBlank());
           
        if (dogCollection.containsDog(dogName)) { 
            System.out.println("Error: Dog with the name " + dogName + " already exists. Please choose a different name.");
            return;
        }

        String dogBreed;
        do {
            dogBreed = input.readString("Enter the breed of the new dog:");
            
            if (dogBreed.isBlank()) {
                System.out.println("Error: A blank string is not allowed for the dog's breed. Please try again.");
            }
    
        } while (dogBreed.isBlank());
    
        int dogAge = input.readInt("Enter the age of the new dog:");
        int dogWeight = input.readInt("Enter the weight of the new dog:");

        Dog newDog = new Dog(dogName, dogBreed, dogAge, dogWeight);

        if (dogCollection.addDog(newDog)){
            System.out.println("New dog " + newDog.getName() + " registered successfully!");
        }
        else System.out.println("Dog" + newDog.getName() + "already exists or invalid input. Please try again.");
    }
    
    //metod tar bort en hund
    private void removeDog(){
        //kontrollerar om det finns några hundar i aktuella registret att ta bort
        if (dogCollection.getDogs().isEmpty()) {
            System.out.println("Error: There are no dogs registered. Cannot delete a dog.");
            return;        
        }
        System.out.println("Enter the name of the dog you want to remove");
        String dogNameToRemove = input.readString("Name");

        Dog dogToRemove = dogCollection.getDog(dogNameToRemove);
        
        //om hunden ej finns (null) så ges ett felmedd
        if (dogToRemove == null) {
            System.out.println("Error: The dog named " + dogNameToRemove + " does not exist. Cannot delete.");
            return;
        }       
        // Om hunden har en ägare, ta bort hunden från ägaren
        if (dogToRemove.getOwner() != null) {
            dogToRemove.getOwner().removeDog(dogToRemove);
            dogToRemove.setOwner(null);
        }

        // Ta bort hunden från dogCollection
        if (dogCollection.removeDog(dogToRemove)) {
            System.out.println("The dog " + dogNameToRemove + " has been removed from the register");
        } else {
            System.out.println("Error: Failed to remove the dog " + dogNameToRemove);
        }
        
    }
    
    //metod som ökar angiven hunds ålder med 1 år
    private void increaseDogAge(){
        if (dogCollection.getDogs().isEmpty()) {
            System.out.println("Error: No dogs in register."); //errormedd om det inte finns nån hund i collection
            return;
        }
        System.out.println("Enter the name of the dog to increase its age");
        String dogNameToIncreaseAge = input.readString("Name"); //hämtar hundnamn
        Dog dogToIncreaseAge = dogCollection.getDog(dogNameToIncreaseAge);
        

        if(dogToIncreaseAge == null){ //finns inte hunden som angets skrivs errormedd ut
            System.out.println("Error: The dog named " + dogToIncreaseAge + " does not exist. Cannot delete.");
            return;
        }

        if(dogCollection.containsDog(dogToIncreaseAge)){ //finns hunden utförs addering av år med metodanrop från klassen Dog
            dogToIncreaseAge.updateDogAge(1);
            System.out.println("The dog " + dogNameToIncreaseAge + " is now one year older.");
        }
        
        
    }
    
    //metod som tilldelar angiven hund till angiven ägare
    private void giveDogToOwner(){
        //kontrollerar om registret är tomt på hundar eller ägare
        if (dogCollection.getDogs().isEmpty() || ownerCollection.getOwners().isEmpty()) {
            System.out.println("Error: " + (dogCollection.getDogs().isEmpty() ? "No dogs" : "No owners") + " in register.");
            return;
        }
    
        String dogNameToAssign = input.readString("Enter dog name "); //hämtar hundnamn
        Dog dogToAssign = dogCollection.getDog(dogNameToAssign);

        if(dogToAssign == null){ //kontrollerar om hunden finns i registret
            System.out.println("Error: The dog " + dogNameToAssign + " does'nt exist.");
            return; 
        }
               
        if(dogToAssign.getOwner() != null){ //kontrollerar om hunden redan har ägare
            System.out.println("Error: The dog " + dogNameToAssign + " already have an owner.");
            return;
        }

        String ownerNameForAssignment = input.readString("Enter owner name" ); //hämtar ägarnamn
        Owner ownerForAssignment = ownerCollection.getOwner(ownerNameForAssignment);
       
        if(ownerForAssignment == null){ // samma som kodblocket ovan fast för owner
            System.out.println("Error: The owner " + ownerNameForAssignment + " does'nt exist.");
            return; 
        }
       
        if(ownerForAssignment.addDog(dogToAssign)){ //anropar metoder för att lägga till hunden till ägare och sätta ägaren till hunden
            dogToAssign.setOwner(ownerForAssignment);
            System.out.println("The dog " + dogNameToAssign + " is now owned by " + ownerNameForAssignment);
        }
        else {
            System.out.println("The dog " + dogNameToAssign + " already have a owner");
        }
    }
    
    //metod tar bort hund från ägare
    private void removeDogFromOwner(){
        //kontroller för att se om hundar och ägare finns i registret
        if (dogCollection.getDogs().isEmpty()) {
            System.out.println("Error: No dogs in register.");
            return;
        }
        if(ownerCollection.getOwners().isEmpty()){
            System.out.println("Error: No owners in register.");
            return;
        }

        System.out.println("Enter dog name");
        String dogNameToRemoveFromOwner = input.readString("Name");
        Dog dogToRemoveFromOwner = dogCollection.getDog(dogNameToRemoveFromOwner);

        
        

        if(dogToRemoveFromOwner == null){
            System.out.println("Error: The dog " + dogNameToRemoveFromOwner + " does'nt exist.");
            return;
        }
        //tar bort hunden från ägaren och ägaren från hunden
        if(dogToRemoveFromOwner.getOwner().removeDog(dogToRemoveFromOwner)){
            dogToRemoveFromOwner.setOwner(null);
            System.out.println("The dog " + dogNameToRemoveFromOwner + " was successfully removed.");
            
            return;
        }

    }

    //metod skapar lista med ägare
    private void listOwners(){        
        if (ownerCollection.getOwners().isEmpty()) {
            System.out.println("Error: No owners registered.");
        } else {
            System.out.println("List of owners");
            for (Owner owner : ownerCollection.getOwners()) {
                System.out.println(owner);
            }
        }
        System.out.println("Returning to main menu...\n");
    }

    //metod skapar lista med hundar 
    private void listDogs() {
        if(dogCollection.getDogs().isEmpty()){
            System.out.println("Error: No dogs in register");
            return;
        }
        System.out.println("Enter minimum tail length:");
        double minTailLength = input.readDouble("Minimum Tail Length");
    
        //anropar metod getDogsWithMinimumTailLength för att hämta sorterad hundlista med hundar som har längre/lika svans än angiven svanslängd
        ArrayList<Dog> dogs = dogCollection.getDogsWithMinimumTailLength(minTailLength);
        

        System.out.println("List of dogs");
        System.out.printf("%-10s %-10s %-3s %-6s %-8s %s\n", "Name", "Breed", "Age", "Weight", "Tail", "Owner");
        System.out.println("================================================");
        //forloop hämtar värdena och skriver ut rad var för sig
        for (Dog dog : dogs) {
             
            String ownerName = (dog.getOwner() != null) ? dog.getOwner().getName() : "N/A";
            System.out.printf("%-10s %-10s %-3d %-6d %-8.1f %s\n",
            dog.getName(), dog.getBreed(), dog.getAge(), dog.getWeight(), dog.getTailLength(), ownerName);
        }
    
        System.out.println("Returning to main menu...\n");
    }

    //metod väntar och tar emot kommandon
    private void runCommandLoop(){
        
        String answer;
        do{
                        
            mainMenuStrings();            
            answer = input.readString("Write your command and press enter");                        
            
            switch (answer.toUpperCase()){
                case REGISTER_NEW_OWNER_CMD:
                    registerNewOwner();
                    break;
                case REMOVE_OWNER_CMD:
                    removeOwner();
                    break;
                case REGISTER_NEW_DOG_CMD:
                    registerNewDog();
                    break;
                case INCREASE_AGE_CMD:
                    increaseDogAge();
                    break;
                case REMOVE_DOG_CMD:
                    removeDog();
                    break;
                case GIVE_DOG_TO_OWNER_CMD:
                    giveDogToOwner();
                    break;
                case REMOVE_DOG_FROM_OWNER_CMD:
                    removeDogFromOwner();
                    break;
                case LIST_DOGS_CMD:
                    listDogs();
                    break;
                case LIST_OWNERS_CMD:
                    listOwners();
                    break;

                case EXIT_CMD:
                break;
                           
            default:
                System.out.println("Error: Wrong input, try again");
                break;
            
            }
        }while (!answer.equalsIgnoreCase(EXIT_CMD));

    }

    
    //metod som stänger input-resursen
    private void shutDown(){
        
        System.out.println("Program closing...");
        input.close();
    }

    //metod som startar programmet 
    private void start(){        
        System.out.print("\033[H\033[2J"); //rensar fönstret
        runCommandLoop();
        shutDown();
    }   

    //mainmetod
    public static void main (String[] args){                
        new DogRegister().start();  
    }
}
