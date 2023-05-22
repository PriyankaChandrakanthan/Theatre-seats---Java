//Creating the Ticket class
public class Ticket {
    //Variable declaration
    private int rowNo;
    private int seatNo;
    private double price;
    private Person person;

    //Constructor for Ticket with the following attributes
    public Ticket (int rowNo, int seatNo, double price, Person person) {
        this.rowNo = rowNo;
        this.seatNo = seatNo;
        this.price = price;
        this.person = person;
    }

    //Defining the Getters and Setters
    public int getRowNo() {
        return rowNo;
    }
    public void setRowNo(int rowNo) {
        this.rowNo = rowNo;
    }
    public int getSeatNo(){
        return seatNo;
    }
    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public Person getPerson() {
        return person;
    }
    public void setPerson (Person person) {
        this.person = person;
    }

    //Method for printing all the information of a Ticket
    public void print() {
        System.out.println("Person name: " + person.getName());
        System.out.println("Person surname: " + person.getSurname());
        System.out.println("Person email: " + person.getEmail());
        System.out.println("Row number: " + (rowNo));
        System.out.println("Seat number: " + (seatNo));
        System.out.println("Price: " + price);
    }
}

