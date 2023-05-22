import java.util.Scanner; //Importing the Scanner class
import java.io.File; //Importing the File class
import java.io.FileWriter; //Importing the FileWriter class
import java.io.IOException; //Importing the IOException class to handle errors
import java.util.ArrayList; //Importing the ArrayList class

//Creating the main class Theatre
public class Theatre {
    //Declaring a 2D ragged array with 3 rows
    static int [][] theatreSeats = new int [3][];
    //Defining and initializing the 2D ragged array with 3 rows and with their respective seat numbers
    static {
        theatreSeats[0] = new int [12];
        theatreSeats[1] = new int [16];
        theatreSeats[2] = new int [20];
    }

    //Creating an empty arraylist to store Ticket objects
    static ArrayList<Ticket>tickets_array = new ArrayList<Ticket>();

    public static void main(String[] args) {
        System.out.println("#----------Welcome to the New Theatre----------#");
        //Importing the Scanner class for Menu
        Scanner menuInput = new Scanner(System.in);
        int option; //Declaring a variable for selecting an option from the menu
        boolean quit = false;
        while (!quit) {
            //Displaying the Menu
            System.out.print("-".repeat(75)+"\n");
            System.out.println("Please select an option: ");
            System.out.println("    1) Buy a ticket ");
            System.out.println("    2) Print seating area ");
            System.out.println("    3) Cancel ticket ");
            System.out.println("    4) List available seats ");
            System.out.println("    5) Save to file ");
            System.out.println("    6) Load from file ");
            System.out.println("    7) Print ticket information and total price ");
            System.out.println("    8) Sort tickets by price ");
            System.out.println("    0) Quit ");
            System.out.print("-".repeat(50)+"\nEnter option: ");
            //Checking if the input for menu is an integer
            if (menuInput.hasNextInt()) {
                option = menuInput.nextInt();
                //Executing the task based on the input option given by the user from the menu
                switch (option) {
                    case 1:
                        buy_ticket();
                        break;
                    case 2:
                        print_seating_area();
                        break;
                    case 3:
                        cancel_ticket();
                        break;
                    case 4:
                        show_available();
                        break;
                    case 5:
                        save ();
                        break;
                    case 6:
                        load();
                        break;
                    case 7:
                        print_tickets_info ();
                        break;
                    case 8:
                        sort_tickets (tickets_array);
                        break;
                    case 0:
                        quit = true;
                        System.out.println("Thank you, exiting program.");
                        break;
                    default: //Displays an error message if the user input integer is out of range (not between 0-8)
                        System.out.print("The input integer is out of range!\n");
                }
            }
            //Displays an error message when the user input value is not an integer and asks the user to input the menu option again
            else {
                System.out.println("The input is not an integer!");
                menuInput.next();
            }
        }
    }

    //Option 1 - Method to buy tickets and reserve the seats
    private static void buy_ticket() {
        System.out.println("Enter the following details to buy a ticket");
        System.out.println("#"+"-".repeat(15)+"Available rows"+"-".repeat(15)+"#"+"\n(row 1, row 2, row 3)");
        //Importing the scanner class
        Scanner rowInput = new Scanner(System.in);
        while (true) {//Runs till the user inputs a valid input for row
            System.out.print("Enter the row number: ");
            if (rowInput.hasNextInt()) { //Checking if row number input is integer
                int rowNo = rowInput.nextInt() - 1; //if the row number is an integer
                if (rowNo >= 0 && rowNo < theatreSeats.length) { //Checking if the rowNo is between the range of 1 to 3
                    Scanner seatInput = new Scanner(System.in);
                    while (true) { //Runs till the user inputs a valid input for seat
                        System.out.print("Enter the seat number: ");
                        if (seatInput.hasNextInt()) { //Checking if the input seat number is an integer
                            int seatNo = seatInput.nextInt() - 1; //to make array indexing starts from 1 instead of 0.
                            if (seatNo >= 0 && seatNo < theatreSeats[rowNo].length && theatreSeats[rowNo][seatNo] == 0) {
                                theatreSeats[rowNo][seatNo] = 1; //if the seat number is 0 it becomes 1 when booked
                                Scanner input = new Scanner(System.in); //Importing Scanner class to read input
                                System.out.print("Enter the Person name: ");
                                String name = input.nextLine();
                                System.out.print("Enter the Person surname: ");
                                String surname = input.nextLine();
                                System.out.print("Enter the Person email: ");
                                String email = input.nextLine();
                                while (true) {//Runs till the user inputs a valid input for price
                                    System.out.print("Enter the price: ");
                                    if (input.hasNextDouble()) { //Checking if the input value for price is a double type
                                        double price = input.nextDouble();
                                        //Creating a new Person object using the name, surname, and email variables
                                        Person person = new Person(name, surname, email);
                                        System.out.println("Seat available, Successfully purchased a ticket");
                                        //Creating a new Ticket object using the rowNo, seatNo, price, and the Person object created earlier
                                        Ticket newTicket = new Ticket(rowNo+1,seatNo+1,price, person);
                                        //Adding the new Ticket object to the tickets_array array list
                                        tickets_array.add(newTicket);
                                        break;
                                    }
                                    else {
                                        //Displays an error message if the input value for price is not an integer and asks the user to input again
                                        System.out.println("Enter a valid input for price");
                                        input.next();
                                    }
                                }
                            }
                            //Displays an error message if the input value for seat is out of range
                            else if (seatNo < 0 || seatNo >= theatreSeats[rowNo].length) {
                                System.out.println("Enter a valid seat number range (1-"+theatreSeats[rowNo].length+")"+"!");
                            }
                            //Displays an error message if the seat is already booked
                            else {
                                System.out.println("This seat is already booked!. Please select another seat");
                            }
                            break;
                        }
                        //Displays an error message if the input value for seat in not an integer value and asks the user to input again
                        else {
                            System.out.println("Please enter an integer value for the seat number!");
                            seatInput.next();
                        }
                    }
                    break;
                }
                //Displays an error message if the inout row number is out of range and asks the user to input the row no. again
                else {
                    System.out.println("Enter a valid row number range (1-3)!");
                }
            }
            //Displays an error message if the input row number is not an integer value
            else {
                System.out.println("Please enter an integer value for the row number!");
                rowInput.next();
            }
        }
        Scanner continueInput = new Scanner(System.in); //Importing Scanner class to read input
        while (true) { //Runs till the user inputs a valid input for row
            System.out.println("Do you wish to book another ticket? (Yes-y/No-n)");
            //The next() method reads the user's input as a String and converts it to lowercase incase if the user enters a capital letter
            String book = continueInput.next().toLowerCase();
            if ((book.equals("y"))|| (book.equals("Y"))) {
                buy_ticket(); //If yes it will ask for the user to enter the details for another ticket
                break;
            }
            else if ((book.equals("n"))|| (book.equals("N"))) {
                System.out.println("Back to the Menu"); //If no back to menu with options
                break;
            }
            //Displays if user enters anything other than y/Y and n/N
            else {
                System.out.println("Please enter either 'y' or 'n'");
            }
        }
    }

    //Option 2 - Method to display the visual representation of the seats
    private static void print_seating_area () {
        System.out.println("Seating Area");
        //Looping to calculate the maximum row length
        //If the length is > the current maxRowLength, it updates maxRowLength to the new length
        int maxRowLength = 0;
        for (int[] row : theatreSeats) {
            if (row.length > maxRowLength) {
                maxRowLength = row.length;
            }
        }
        //Displaying the header with certain number of spaces accordingly.
        System.out.printf("%" + (maxRowLength +6) + "s\n", "***********");
        System.out.printf("%" + (maxRowLength +6) + "s\n", "*  STAGE  *");
        System.out.printf("%" + (maxRowLength +6) + "s\n", "***********");

        //Lopping through the 2D array and calculating the no.of spaces needed to center the rows.
        for (int[] row : theatreSeats) {
            int numSpaces = (maxRowLength - row.length) / 2;
            System.out.printf("%" + (numSpaces + 10) + "s", " ");
            if (row.length == 0) {
                System.out.print("");
            }
            else {
                for (int i = 0; i < row.length; i++) {
                    if (i == row.length / 2) {
                        System.out.print(" ");
                    }
                    if (row[i] == 0) {
                        System.out.print("O"); //Prints O for unoccupied seat
                    }
                    else {
                        System.out.print("X"); //Prints X for occupied seat
                    }
                }
            }
            System.out.println();
        }
    }

    //Option 3 - Method for cancelling the reserved seats
    private static void cancel_ticket () {
        System.out.println("Enter the details of the ticket you wish to cancel: ");
        Scanner rowInput = new Scanner(System.in);
        while (true) { //Runs till the user inputs a valid input for row
            System.out.print("Enter the row number: ");
            if (rowInput.hasNextInt()) { //Checking if row number input is integer
                int rowNo = rowInput.nextInt() - 1; //if the row number is an integer
                if (rowNo >= 0 && rowNo < theatreSeats.length) { //Checking if the rowNo is between the range of 1 to 3
                    Scanner seatInput = new Scanner(System.in);
                    while (true) { //Runs till the user inputs a valid input for row
                        System.out.print("Enter the seat number: ");
                        if (seatInput.hasNextInt()) { //Checking if the input seat number is an integer
                            int seatNo = seatInput.nextInt() - 1;
                            if (seatNo >= 0 && seatNo < theatreSeats[rowNo].length && theatreSeats[rowNo][seatNo] == 1) {
                                theatreSeats[rowNo][seatNo] = 0; //if the seat number is 1 it becomes 0 when booked
                                //Looping through the tickets_Array to get the no. of existing tickets in the array
                                for (int i = 0; i < tickets_array.size(); i++) {
                                    //Checking the current ticket object at the current index
                                    Ticket ticket = tickets_array.get(i);
                                    //If the checked ticket object matches the rowNo and SeatNo
                                    if (ticket.getRowNo() == rowNo+1 && ticket.getSeatNo() == seatNo+1) {
                                        tickets_array.remove(i); //Removes the ticket
                                    }
                                }
                                //Displays a message if the ticket is successfully cancelled
                                System.out.println("Successfully cancelled");
                                break;
                            }
                            //Displays an error message if the input value for seat is out of range
                            else if (seatNo < 0 || seatNo >= theatreSeats[rowNo].length) {
                                System.out.println("Enter a valid seat number range (1-"+theatreSeats[rowNo].length+"!");
                            }
                            //Displays an error message if the seat is not booked already
                            else {
                                System.out.println("Seat is not booked. Enter the valid seat details!");
                            }
                            break;
                        }
                        //Displays an error message if the input value for seat in not an integer value and asks the user to input again
                        else {
                            System.out.println("Please enter an integer value for the seat number!");
                            seatInput.next();
                        }
                    }
                    break;
                }
                //Displays an error message if the inout row number is out of range and asks the user to input the row no. again
                else {
                    System.out.println("Enter a valid row number range (1-3)!");
                }
            }
            //Displays an error message if the input row number is not an integer value
            else{
                System.out.println("Please enter an integer value for the row number!");
                rowInput.next();
            }
        }
        Scanner continueInput = new Scanner(System.in); //Importing Scanner class to read input
        while (true) { //Runs till the user inputs a valid input
            System.out.println("Do you wish to cancel another ticket? (Yes-y/No-n)");
            //The next() method reads the user's input as a String and converts it to lowercase incase if the user enters a capital letter
            String cancel = continueInput.next().toLowerCase();
            if ((cancel.equals("y"))|| (cancel.equals("Y"))) {
                cancel_ticket();
                break;
            }
            else if ((cancel.equals("n"))|| (cancel.equals("N"))) {
                System.out.println("Back to the Menu"); //If no back to menu with options
                break;
            }
            //Displays if user enters anything other than y/Y and n/N
            else {
                System.out.println("Please enter either 'y' or 'n'");
            }
        }
    }

    //Option 4 - Method to display the seats available in integer value instead of 0 and 1
    private static void show_available () {
        //Display the rows and seats of the theatre
        System.out.println("Available seats: ");
        //Loops through each row till the length the row of the row is et
        for (int rowNo = 0; rowNo < theatreSeats.length; rowNo++) {
            System.out.print("Seats available in Row " + (rowNo + 1) + ": "); //To display like this-Seats available in Row: 1
            boolean firstSeat = true; //To check whether the first available seat in a row is printed or not
            boolean hasSeats = false; //To check whether there are any available seats in a row or not
            for (int seatNo = 0; seatNo < theatreSeats[rowNo].length; seatNo++) {
                //Checking if the current seat is available
                if (theatreSeats[rowNo][seatNo] == 0) {
                    //Checks if we have printed the first available seat in the row. If not, it prints the seat number without a comma and sets firstSeat to false.
                    if (firstSeat) {
                        System.out.print(seatNo + 1);
                        firstSeat = false;
                    }
                    //Or else if we have printed the first available seat in the row already, this prints a comma after the seat number.
                    else {
                        System.out.print(", " + (seatNo + 1));
                    }
                    //Sets to true if at least one available seat in the current row found.
                    hasSeats = true;
                }
            }
            //Prints a full stop after if there are any available seats in the current row.
            if (hasSeats) {
                System.out.print(".");
            }
            System.out.println(); //newline after each row is complete
        }
    }

    //Option 5 - Method for saving the 3 arrays with the row’s information in a file.
    private static void save() {
        try {
            //Creating a new file
            File file = new File("ticketsArray.txt");
            //Attempting to create the file and storing the status of file creation in a Boolean variable
            boolean file_created = file.createNewFile();
            //If file is created, retrieving data from the file
            if (file_created) {
                System.out.println("File is created: "+file.getName());
            }
            //Creating a new file and Writing the data into a file
            FileWriter writer = new FileWriter("ticketsArray.txt");
            //Looping through the rows of the 2D array
            for (int rowNo = 0; rowNo < theatreSeats.length; rowNo++) {
                //Looping through the seat numbers of each row
                for (int seatNo = 0; seatNo < theatreSeats[rowNo].length; seatNo++) {
                    //Writing each seat detials to the text file
                    writer.write(theatreSeats[rowNo][seatNo] + " ");
                }
                writer.write(" \n"); //creates a new line after each row
            }
            //Closing the file and saving the data
            writer.close();
            System.out.println("Array data saved to file.");
        }
        catch (IOException e) { //Displaying an error message if an IO exception is caught
            System.out.println("Error saving the data to file.");
        }
    }

    //Option 6 - Method to load the file saved in open when Option 5 is selected and restores the 3 arrays with the row’s information.
    private static void load() {
        try { //Executes the block if the file is found
            File inputFile = new File("ticketsArray.txt");
            //Finding the file and Reading the data from the file
            Scanner reader = new Scanner(inputFile);
            int rowNo = 0;
            while (reader.hasNextLine() && rowNo < theatreSeats.length) {
                String line = reader.nextLine(); //Reads the next line of text from the file.
                //This creates a new String array, seatStrings that contains the individual values on the line that was just read
                String[] seatDetails = line.split(" ");
                for (int seatNo = 0; seatNo < seatDetails.length && seatNo < theatreSeats[rowNo].length; seatNo++) {
                    //Converts the current seatStrings value to an integer using the parseInt() method and stores it in the suitable position in the rowSeats array
                    theatreSeats[rowNo][seatNo] = Integer.parseInt(seatDetails[seatNo]);
                }
                rowNo++; //Incrementing the rowNo variable, to keep track of which row of the theatreSeats array
            }
            reader.close(); //This closes the Scanner and closes the file
            System.out.println("Array data loaded from the file successfully.");
        }
        //This catch block will execute if a FileNotFoundException is thrown. It catches the exception and displays an error message
        catch (IOException e) {
            System.out.println("Error loading the data from file.");
        }
    }

    //Option 7 - Method to display the information of a ticket and the total of the tickets
    private static void print_tickets_info() {
        double totalPrice = 0; //Declaring a variable to hold the total price of the tickets by initializing with 0.
        //If the tickets_array doesn't contain any ticket info
        if (tickets_array.size()==0) {
            System.out.println("No tickets found! Total is "+totalPrice);
        }
        else {
            int ticketCount=1;
            for (Ticket t: tickets_array) {
                System.out.println("\nTicket "+ticketCount+ " Info: ");
                t.print();
                totalPrice+=t.getPrice(); //Adding the price of the current ticket's price to the total price
                ticketCount++;
            }
            //Displaying the total price of all tickets
            System.out.println("\nThe price of all tickets: Rs."+totalPrice);
        }
    }

    //Option 8 - Method to sort the Ticket info according to the ticket price of a ticket in ascending order
    private static void sort_tickets(ArrayList<Ticket> ticketList) {
        int middle;
        ArrayList<Ticket> leftPart = new ArrayList<>();
        ArrayList<Ticket> rightPart = new ArrayList<>();

        //Checking if the size of the ticketList is greater than 1, because it makes no sense to sort a list with only one element
        if (ticketList.size() > 1) {
            middle = ticketList.size() / 2;
            //Creating left sublist
            for (int i = 0; i < middle; i++) {
                leftPart.add(ticketList.get(i));
            }
            //Creating right sublist
            for (int j = middle; j < ticketList.size(); j++) {
                rightPart.add(ticketList.get(j));
            }
            //Recursively calling sort_tickets method for both left and right sublists to sort them
            sort_tickets(leftPart);
            sort_tickets(rightPart);

            //Now merging both left and right sublists based on the price of the tickets
            int ticketIndex = 0;
            int leftIndex = 0;
            int rightIndex = 0;

            while (leftIndex < leftPart.size() && rightIndex < rightPart.size()) {
                //If price of the ticket in the left sublist is less than the price of the ticket in the right sublist,
                //Adding the ticket from left sublist into the ticketList at ticketIndex, and incrementing the leftIndex.
                if (leftPart.get(leftIndex).getPrice() < rightPart.get(rightIndex).getPrice()) {
                    ticketList.set(ticketIndex, leftPart.get(leftIndex));
                    leftIndex++;
                } else {
                    //Otherwise adding the ticket from right sublist into the ticketList at ticketIndex, and incrementing the rightIndex.
                    ticketList.set(ticketIndex, rightPart.get(rightIndex));
                    rightIndex++;
                }
                //Incrementing the ticketIndex.
                ticketIndex++;
            }
            //Copying the remaining elements from the left or right sublists to the ticketList, if there are any.
            int tempIndex = 0;
            ArrayList<Ticket> temp;
            if (leftIndex >= leftPart.size()) {
                temp = rightPart;
                tempIndex = rightIndex;
            } else {
                temp = leftPart;
                tempIndex = leftIndex;
            }

            for (int i = tempIndex; i < temp.size(); i++) {
                ticketList.set(ticketIndex, temp.get(i));
                ticketIndex++;
            }
        }
        //Printing the details of all the tickets in the sorted ticketList and calculating the total price of all the tickets.
        print_tickets_info();
    }
}