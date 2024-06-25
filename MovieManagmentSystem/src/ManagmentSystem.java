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

    OSP     06/21/2024  Completed the Add Movie option previously mentioned as well as added the Start
    Showing Movies option. Created the stringToDate method which converts a passed-in string into a date
    that can be added to a Movie object. Created the compareMovies method which compares a passed-in movie
    list with a passed-in movie. Created the comingToShowing method which finds movies in the "coming"
    list with a specific passed in release date and adds them to the "showing" list.
    Removed the "Add New Showing Movie" and "Add New Coming Soon" options and replaced them with a single
    "Add Movie" option to more closely follow the project requirements.

    Jeffrey S. 06/23/2024 Added a new "editComingMovie" function that allows a movie inside a list, the
    coming list, to have it's description and date edited. It passes in 1 LinkedList param and 3 String
    params. It also is a void function since nothing is needed to be returned. I also adjusted some of the
    code so Eclipse can function properly.

    JQS 06/24/2024  Added a new "orderComingList" method that will sort the "coming" list by non-descending
    order. This method is called underneath the ListSelectMenu method to ensure the list is ordered before
    the GUI displays the list.


    RTA 06/24/2024  Added a new "countDatesBefore" method to count the "coming" movies before a user-specified date
----------------------------------------------------------------------------------------------------------
*/

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
                case 3: // Show Movies
                    listSelectMenu(choice, ListShowing, ListComing);
                    break;
		case 4: // user enters a specified date
		  System.out.println("Please enter a specified date.");  //asks user to enter their date of choice
		  String dateString = scnKeybord.nextLine(); // clears scanner   
		  Date date = stringToDate(dateString);  //converts string entered into object in Date
   		  int count = countdatesBefore(ListComing, date);  //reads as the number counted
	          System.out.println(count + " movies are coming before this date.");	
		  break;
                case 5: //Exit
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
        System.out.println("3. Start Showing Movies");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    //Called when user enters Displays Movies, or Adds Movie into the main menu
    //Gives the user to the option of which list to diplay/manipulate
    private static void listSelectMenu(int choice, LinkedList<Movie> ListShowing, LinkedList<Movie> ListComing) {
    	orderComingList(ListComing); // Orders the "coming" list by non-descending order.
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
                // Adds movie to the "coming" list, can be changed to "showing" list later
                System.out.println("\n===== Add Movie =====");
                Movie newMovie = new Movie(null,null,null,null,null);

                // Sets user input as movie title
                System.out.print("\nEnter your movie's name: ");
                newMovie.setTitle(scnKeybord.nextLine());

                // Sets user input as movie description
                System.out.print("Enter your movie's description: ");
                newMovie.setDescription(scnKeybord.nextLine());

                // Sets user input as movie received date
                System.out.print("Enter your movie's received date (MM/DD/YYYY): ");
                Date dateRecieve = stringToDate(scnKeybord.nextLine());
                if (dateRecieve == null){break;} // Won't add movie if user enters invalid date format
                newMovie.setReceiveDate(dateRecieve);

                // Sets user input as movie release date
                System.out.print("Enter your movie's release date (MM/DD/YYYY): ");
                Date dateRelease = stringToDate(scnKeybord.nextLine());
                if (dateRelease == null){break;} // Won't add movie if user enters invalid date format
                newMovie.setReleaseDate(dateRelease);

                // Automatically sets the movie's status as received
                newMovie.setReleaseStatus(Movie.Status.RECEIVED);

                // Won't add movie to coming list if release date is before received date
                if (newMovie.getReleaseDate().compareTo(newMovie.getReceiveDate()) < 1){
                    System.out.println("\nYour movie's release date is before the received date.");
                    break;
                }

                // Won't add movie to coming list if it already exists
                if (compareMovies(ListComing, newMovie)){
                    System.out.println("\nYour movie already exists.");
                    break;
                } else {
                    ListComing.add(newMovie);
                    System.out.println("\nYour movie " + newMovie.getTitle() + " was successfully added!\n");
                }
                waitForUser();
                break;
            case 3: // Start Showing Movies
                System.out.println("\n===== Start Showing Movies =====");
                System.out.print("\nEnter the release date of movies you want to start showing (MM/DD/YYYY): ");
                Date dateShow = stringToDate(scnKeybord.nextLine());
                if (dateShow == null){break;} // Will exit if user enters invalid date format
                // Adds movie to "showing" list
                int intMovieCount = comingToShowing(ListComing, ListShowing, dateShow);
                System.out.print("\n" + intMovieCount);
                System.out.println((intMovieCount == 1) ?
                " movie is now being shown." :" movies are now being shown.\n");
                waitForUser();
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

    // Converts a passed in string into a date object, returns date object 
    public static Date stringToDate(String strDate){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        try { // Attempts to convert the passed in string into a Date object using the formatter's format
            Date dateNew = formatter.parse(strDate);
            return dateNew;
        } catch (ParseException p) { // Catches if the passed in string doesn't match the date format
            System.out.println("\nERROR: The date you entered was not in a valid format.");
            return null;
        }
    }

    // Compares a passed in movie to the movies in a passed in list
    // Returns true if passed in movie exists in list, false if it doesn't
    public static boolean compareMovies(LinkedList<Movie> movieList, Movie newMovie){
        ListIterator<Movie> iterator = movieList.listIterator();
        while (iterator.hasNext()){
            Movie listMovie = iterator.next();
            if (listMovie.getTitle().equals(newMovie.getTitle())&&
            listMovie.getDescription().equals(newMovie.getDescription())&&
            listMovie.getReceiveDate().equals(newMovie.getReceiveDate())&&
            listMovie.getReleaseDate().equals(newMovie.getReleaseDate())){
                return true;
            }
        }
        return false;
    }

    // Finds movies in the "coming" list with a passed in release date and adds them to the "showing" list
    public static int comingToShowing(LinkedList<Movie> comingList, LinkedList<Movie> showingList,Date releaseDate){
        ListIterator<Movie> iterator = comingList.listIterator();
        int movieCount = 0; // Counts how many movies were moved to the "showing" list
        while (iterator.hasNext()){
            Movie listMovie = iterator.next();
            if (listMovie.getReleaseDate().equals(releaseDate)){
                if (!compareMovies(showingList, listMovie)){
                    /* Movie gets moved if it has the specified release date and
                       doesn't already exist in the "showing" list */
                    listMovie.setReleaseStatus(Movie.Status.RELEASED);
                    showingList.add(listMovie);
                    comingList.remove(listMovie);
                    movieCount++;
                }
            }
        }
        return movieCount;
    }
    
    // Edits a movie inside the "coming" list with either a new date, description or both.
    public static void editComingMovie(LinkedList<Movie> ListComing, String movieToFind, String newReleaseDate, String newDescription) {
        ListIterator<Movie> iterator = ListComing.listIterator();
        while (iterator.hasNext()) {
            Movie movie = iterator.next();
            String movieTitle = movie.getTitle();
            Date newDate = stringToDate(newReleaseDate); // Changes the String object of newReleaseDate into a Date object
            
            if (movieTitle.toLowerCase().equals(movieToFind.toLowerCase())) { // Comparing the current movie in the loop to the String given
            	if (newReleaseDate != null) { movie.setReleaseDate(newDate); } // If any of the params needed aren't null, change the movie date/desc to it
                if (newDescription != null) { movie.setDescription(newDescription); }
            }
        }
    }
    
    // Orders the "coming" list by non-descending order using the sort method
	public static void orderComingList(LinkedList<Movie> ListComing) {
        // Sorts via comparing the current movie's date with the next movie's date in the list
		ListComing.sort((thisMovie, nextMovie) -> thisMovie.getReleaseDate().compareTo(nextMovie.getReleaseDate()));
    }

    //Counts the number of "coming" movies earlier than a specified date
    public static  int countdatesBefore(LinkedList<Movie> ListComing, Date date) throws IllegalArgumentException {
        if (date == null) {
            throw new IllegalArgumentException ("Invalid date. Try again."); 
    }
    int count = 0;
    ListIterator<Movie> iterator = ListComing.listIterator();
    while (iterator.hasNext()) {
	Movie movie = iterator.next();
        if (movie.getReleaseDate().before(date)) {
            count++;
	
        }
    }
    return count;
  }
}
