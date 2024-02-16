import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MovieCollection {
    private ArrayList<Movie> movieCollection;
    private Scanner scan;



    public MovieCollection(){
        scan = new Scanner(System.in);
        movieCollection = new ArrayList<>();
        start();
    }

    public void start(){
        readData();
        menu();
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
            System.out.println();
        }
        int count = 0;
        for (int i = 0; i < movieCollection.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < movieCollection.size(); j++) {
                count++;
                String first = movieCollection.get(j).getTitle();
                String sec = movieCollection.get(minIndex).getTitle();
                if (first.compareTo(sec) < 0) {
                    minIndex = j;
                }
            }
            Movie temp = movieCollection.get(i); movieCollection.set(i, movieCollection.get(minIndex));
            movieCollection.set(minIndex, temp);
            System.out.println("SELECTION SORT: Number of loop iterations: "+ count);
        }

    }

    private void searchTitles(){
        ArrayList<Movie> results = new ArrayList<>();
        System.out.println("Enter a keyword for the title of the movie: ");
        String search = scan.nextLine().toLowerCase();
        for(Movie movie: movieCollection){
            if(movie.getTitle().toLowerCase().contains(search)){
                results.add(movie);
            }
        }
        for(int i = 0; i < results.size(); i++) {
            System.out.println(i + 1 + ": " + results.get(i).getTitle());
        }
        System.out.println("Enter the number of the movie you want to go further into: ");
        int choice = scan.nextInt();
        scan.nextLine();
        results.get(choice).getInfo();
        menu();
    }

    private void searchCast(){
        ArrayList<Movie> results = new ArrayList<>();
        System.out.println("Enter a cast member (first or last name): ");
        String search = scan.nextLine().toLowerCase();
        for(Movie movie: movieCollection){
            String[] movieCast = movie.getCast();
            for(int i = 0; i < movieCast.length; i++) {
                String person = movieCast[i];
                if (person.equals(search)) {
                    results.add(movie);
                }
            }
        }
        for(int i = 0; i < results.size(); i++) {
            System.out.println(i + 1 + ": " + results.get(i).getTitle());
        }
        System.out.println("Enter the number of the movie you want to go further into: ");
        int choice = scan.nextInt();
        scan.nextLine();
        results.get(choice).getInfo();
        menu();
    }
}
