/*
----------------------------------------------------------------------------------------------------------
    Name:		ManagmentSystem
    Developers:	Mitchell Jones, Ollie Peel, Alexander Brinkly, Rawan Alhachami, Jeffrey Saylor
    Language:	Java
    Date:		06-20-2024
----------------------------------------------------------------------------------------------------------
    Change Log
----------------------------------------------------------------------------------------------------------
    Who		Date		Change
    MTJ		06-20-2024	Original Version of Code. Created movie class, and the following methods: 
    initializeMovies, displayMainMenu, listSelectMenu, iterateAndPrintMovies, and waitForUser. Created 
    basic Main Menu that can be easily modified to fit other options.

    Running the code will display the Main Menu, giving the user three options; Display Movies, Add Movie, 
    and Exit. Selecting Display Movies promts the user to pick a list, and will then iterate through it.
    The Add Movie option needs to be finished, and will only output a string into the terminal.
----------------------------------------------------------------------------------------------------------
*/
import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class ManagmentSystem {

    private static Scanner scnKeybord = new Scanner(System.in); 

    public static void main(String[] args) throws Exception {
        LinkedList<Movie> ListShowing = new LinkedList<>();
        LinkedList<Movie> ListComing = new LinkedList<>();
        //ListIterator<Movie> IteratorShowing = ListShowing.listIterator();
        //ListIterator<Movie> IteratorComing = ListComing.listIterator();
        
        initializeMovies(ListShowing, ListComing); // creates initial lists

        boolean blnExit = false;

        //Main Menu Loop
        while (!blnExit) { // loops until user exits
            displayMainMenu();
            int choice = scnKeybord.nextInt();
            scnKeybord.nextLine(); // clears the scanner
            switch (choice) {
                case 1: //Display Movies
                    listSelectMenu(choice, ListShowing, ListComing);
                    break;
                case 2: //Add Movie
                    listSelectMenu(choice, ListShowing, ListComing);
                    break;
                case 3: //Exit
                blnExit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter again.");
            }
        }

        System.out.println("Exiting the program.");
        scnKeybord.close();
    }

    private static void initializeMovies(LinkedList<Movie> ListShowing, LinkedList<Movie> ListComing) {
        // Initialize showing movies
        // This will need to be replaced in section 5 to pull from a text file of showing movies
        for (int i = 1; i <= 3; i++) {
            String title = "Movie Showing " + i;
            String description = "Description of " + title;
            Date releaseDate = new Date();
            Date receiveDate = new Date();
            Movie.Status status = Movie.Status.RELEASED;

            Movie movie = new Movie(title, description, releaseDate, receiveDate, status);
            ListShowing.add(movie); //Adds movie at iterator position
        }

        // Initialize coming movies
        // This will need to be replaced in section 5 to pull from a text file for coming movies
        for (int i = 1; i <= 3; i++) {
            String title = "Movie Coming " + i;
            String description = "Description of " + title;
            Date releaseDate = new Date();
            Date receiveDate = new Date();
            Movie.Status status = Movie.Status.RECEIVED;

            Movie movie = new Movie(title, description, releaseDate, receiveDate, status);
            ListComing.add(movie); //Adds movie at iterator position
        }
    }

    //Called during main menu loop
    private static void displayMainMenu() {
        System.out.println("\n===== Main Menu =====");
        System.out.println("1. Display Movies");
        System.out.println("2. Add Movie");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    //Called when user enters Displays Movies, or Adds Movie into the main menu
    //Gives the user to the option of which list to diplay/manipulate
    private static void listSelectMenu(int choice, LinkedList<Movie> ListShowing, LinkedList<Movie> ListComing) {
        switch (choice){
            case 1://Display Movies
                System.out.println("\n===== Display Movies =====");
                System.out.println("1. Display Showing Movies");
                System.out.println("2. Display Movies Coming Soon");
                System.out.print("Enter your choice: ");
                int NextChoice = scnKeybord.nextInt();
                scnKeybord.nextLine(); //Clears scanner
                System.out.println();
                switch (NextChoice){
                    case 1://Display Movies Showing
                        System.out.println("\n===== Movies Showing =====");                      
                        iterateAndPrintMovies(ListShowing);
                        waitForUser();
                        break;
                    case 2://Display Movies Coming Soon
                        System.out.println("\n===== Movies Coming Soon =====");
                        iterateAndPrintMovies(ListComing);
                        waitForUser();
                        break;
                }
                break;
            case 2://Add Movie
                System.out.println("\n===== Add Movie =====");
                System.out.println("1. Add New Showing Movie");
                System.out.println("2. Add New Coming Soon");
                System.out.print("Enter your choice: ");
                NextChoice = scnKeybord.nextInt();
                scnKeybord.nextLine(); // clears scanner
                switch (NextChoice){
                    case 1://Add to Movies Showing                      
                        System.out.println("\nAdd movie to ListShowing");
                        waitForUser();
                        break;
                    case 2://Add to Movies Coming
                        System.out.println("\nAdd movie to ListComing");
                        waitForUser();
                        break;
                }
                break;
        }
    }

    // Iterates through chosen list of movies and outputs their toString
    public static void iterateAndPrintMovies(LinkedList<Movie> movieList) {
        ListIterator<Movie> iterator = movieList.listIterator();
        while (iterator.hasNext()) {
            Movie movie = iterator.next();
            System.out.println(movie.getTitle());
        }
    }

    // Iterates through chosen list of movies and outputs their toString
    // Moves the iterator the the end of the list
    public static void reverseIterateAndPrintMovies(LinkedList<Movie> movieList) {
        ListIterator<Movie> iterator = movieList.listIterator(movieList.size());
        while (iterator.hasPrevious()) {
            Movie movie = iterator.previous();
            System.out.println(movie.getTitle());
        }
    }

    //Allows the user time to see data before the main menu appears again
    public static void waitForUser(){
        System.out.println("-".repeat(45));
        System.out.println("Hit the [Enter] key when ready to continue");
        System.out.println("-".repeat(45));
        scnKeybord.nextLine();
    }
}
