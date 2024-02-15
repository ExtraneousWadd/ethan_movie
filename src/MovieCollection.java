import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection {
    private ArrayList<Movie> movieList;
    private Scanner scan;



    public MovieCollection(){
        scan = new Scanner(System.in);
        start();
    }

    public void start(){

    }

    public void menu(){
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scan.nextLine();

            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }
    private void readData() {

        try {
            File myFile = new File("src\\movies_data.csv");
            Scanner fileScanner = new Scanner(myFile);
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String p =data.substring(0, data.indexOf(","));
                double n = Double.parseDouble(data.substring(data.indexOf(",")+1));

            }
        } catch(IOException exception) {

        }


    }

}
