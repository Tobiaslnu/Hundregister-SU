// Tobias Ljungman tolj3334


public class Dog {
    private static final double DACHSHUND_TAIL_LENGTH = 3.7;
    private static final double TAIL_LENGTH_CALCULATION_DENOMINATOR = 10.0;
    
    private String name;
    private String breed;
    private int age;
    private int weight;
    private double tailLength;
    private Owner owner;  
    
    

    public Dog(String name, String breed, int age, int weight){
        //Ändrar Strängarna för name och breed till normaliserat format med toUpperCase och Lower
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        this.breed = breed.substring(0, 1).toUpperCase() + breed.substring(1).toLowerCase();
        this.age = age;
        this.weight = weight;
        this.tailLength = calculateTailLength(); 
        this.owner = null;       
    }

    //5 följande "Getter-metoder" möjliggör hämtning av olika attributvärden
    public String getName(){
        return name;
    }

    public String getBreed(){
        return breed;
    }

    public int getAge(){
        return age;
    }

        public int getWeight(){
        return weight;
    }

    public double getTailLength(){
        return tailLength;
    }

    // Metod för att hämta hundens ägare
    public Owner getOwner() {
        
        return owner;
    }

    //Metod sätter ägare till hunden
    public boolean setOwner(Owner owner) {
        if (this.owner != null && owner == null){
            this.owner.removeDog(this);
            this.owner = null;
            return false;
        }
        // Om det redan finns en ägare och den inte är samma som den nya ägaren
        if (this.owner != null && (owner == null || !this.owner.equals(owner))){
            return false;
        }  
        
            
        //Sätt nya ägaren på hunden
        this.owner = owner;
        
        //Lägger till hunden i den nya ägarens lista om det finns en ägare
        if (owner != null) {
            owner.addDog(this);
        }

        return true;
    }


    //Metod som uppdaterar hundens ålder
    public void updateDogAge(int years) {
        if (years > 0) {
            // Maximalt värde för en int
            int maxIntValue = Integer.MAX_VALUE;
    
            //Kontrollerar om åldern överskrider det maximala värdet för en int
            if (years <= maxIntValue - this.age) {
                this.age += years;
                this.tailLength = calculateTailLength(); //Ser även till att svanslängd uppdateras när åldern ändras
            } else {
                //Om overflow - sätt åldern till det maximala värdet för en int
                this.age = maxIntValue;
            }
        }
    }
    
    //Hjälpmetod för att beräkna svanslängd
    private double calculateTailLength(){
        //Om ras är tax sätts längden till 3.7 (DACHSHUND_TAIL_LENGTH)
        if (breed.equalsIgnoreCase("tax") || breed.equalsIgnoreCase("dachshund")){   
            double dachshundTailLength = DACHSHUND_TAIL_LENGTH;         
            return dachshundTailLength;
        }
        else {
            return age * (weight/TAIL_LENGTH_CALCULATION_DENOMINATOR); //För andra raser beräknas svanslängden
        }
    }

    //toString-metod som returnerar sträng med alla attribut-värden
    public String toString(){       
        String ownerName = (owner != null) ? owner.getName() : " ";
        return String.format("%-10s %-10s %-3d %-6d %-8.1f %s", name, breed, age, weight, getTailLength(), ownerName);
        
    }


}


