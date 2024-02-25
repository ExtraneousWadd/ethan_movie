import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLOutput;
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
            fileScanner.nextLine();
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] movieInfo = data.split(",");
                String movieTitle = movieInfo[0];
                String castList = movieInfo[1];
                String movieDirector = movieInfo[2];
                String movieOverView = movieInfo[3];
                int movieTime = Integer.parseInt(movieInfo[4]);
                double movieRating = Double.parseDouble(movieInfo[5]);
                castList = castList.replace("|", ",");
                String[] castLists = castList.split(",");
                movieCollection.add(new Movie(movieTitle, castLists, movieDirector, movieOverView, movieTime, movieRating));
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
            Movie temp = movieCollection.get(i);
            movieCollection.set(i, movieCollection.get(minIndex));
            movieCollection.set(minIndex, temp);

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
        if(results.isEmpty()){
            System.out.println("Title not found!!!");
            menu();
        }
        for(int i = 0; i < results.size(); i++) {
            System.out.println(i + 1 + ": " + results.get(i).getTitle());
        }

        System.out.println("Enter the number of the movie you want to go further into: ");
        int choice = scan.nextInt();
        scan.nextLine();
        results.get(choice-1).getInfo();
        menu();
    }

    private void searchCast(){
        //gets name and then puts in names list
        ArrayList<Movie> results = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        System.out.println("Enter a cast member (first or last name): ");
        String search = scan.nextLine().toLowerCase();
        search = search.substring(0, 1).toUpperCase() + search.substring(1);
        for(Movie movie: movieCollection){
            String[] movieCast = movie.getCast();
            for(int i = 0; i < movieCast.length; i++) {
                String person = movieCast[i];
                if ((person.contains(search) || person.contains(search.toLowerCase())) && (!names.contains(person)))  {
                    names.add(person);
                }
            }
        }
        if(names.isEmpty()){
            System.out.println("No results match your search");
            menu();
        }
        //sorts name list
        int count = 0;
        for (int i = 0; i < names.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < names.size(); j++) {
                count++;
                String first = names.get(j);
                String sec = names.get(minIndex);
                if (first.compareTo(sec) < 0) {
                    minIndex = j;
                }
            }
            String temp = names.get(i);
            names.set(i, names.get(minIndex));
            names.set(minIndex, temp);

        }
        //display names
        for(int i = 0; i < names.size(); i++) {
            System.out.println(i + 1 + ": " + names.get(i));
        }
        //gets movies with name and puts it in the results list
        System.out.println("Which would you like to see all  movies for?: ");
        int choice = scan.nextInt();
        choice--;
        scan.nextLine();
        for(Movie movie: movieCollection){
            for(String p : movie.getCast()){
                if(p.equals(names.get(choice))){
                    results.add(movie);
                }
            }
        }


        //sorts results list
        count = 0;
        for (int i = 0; i < results.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < results.size(); j++) {
                count++;
                String first = results.get(j).getTitle();
                String sec = results.get(minIndex).getTitle();
                if (first.compareTo(sec) < 0) {
                    minIndex = j;
                }
            }
            Movie temp = results.get(i);
            results.set(i, results.get(minIndex));
            results.set(minIndex, temp);

        }

        //prints options for movies
        for(int i = 0; i < results.size(); i++) {
            System.out.println(i + 1 + ": " + results.get(i).getTitle());
        }


        System.out.println("Which movie?: ");
        choice = scan.nextInt();
        scan.nextLine();


        results.get(choice-1).getInfo();
        menu();
    }

}
