// Tobias Ljungman tolj3334

import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class InputReader {
    //Statisk HashSet för att hålla reda på skapade instanser av InputReader för varje InputStream.
    private static Set<InputStream> createdInstances = new HashSet<>();

    private Scanner scanner;

    //Konstruktor för att möjliggöra parameterlös inläsning från System.in (genom anropande av den andra konstruktorn)
    public InputReader() {       
        this(System.in);
    }


    //Konstruktor som möjliggör inläsning med valfri InputStream
    public InputReader(InputStream inputStream) {
        //Kontroll om det redan finns en instans av angiven Inputstream
        if (createdInstances.contains(inputStream)) {
            throw new IllegalStateException("Endast en instans per InputStream är tillåten.");
        }
        //Lägger till aktuella InputStream:en i HashSeten för skapade instanser
        createdInstances.add(inputStream);
        this.scanner = new Scanner(inputStream); //Skapar scanner kopplad till aktuell inputstream
    }
                

    //Metod för att läsa in ett heltal med ledtexten prompt och "?>"
    public int readInt(String prompt) {
        System.out.print(prompt + "?>");
        int intResult = scanner.nextInt();
        scanner.nextLine(); //tömmer inmatningsbufferten        
        return intResult;
    }


    //Metod för att läsa in ett decimaltal
    public double readDouble(String prompt) {
        System.out.print(prompt + "?>");
        double doubleResult = scanner.nextDouble();
        scanner.nextLine(); 
        return doubleResult;
    }


    //Metod för att läsa in en sträng
    public String readString(String prompt) {
        System.out.print(prompt + "?>");
        return scanner.nextLine();
    } 


    //Metod som möjliggör stängning av scanner
    public void close() {
        scanner.close();
    }
    
}

