//Creating the Person class
public class Person {
    //Variable declaration
    private String name;
    private String surname;
    private String email;

    //Defining the constructor that takes the following attributes
    public Person (String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    //Defining the Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
