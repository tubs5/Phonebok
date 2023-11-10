package phonebook;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tobias Heidlund
 */
public class PersonFactory {

    private String firstName,surname;
    private int age;
    private final List<String> phoneNumbers = new ArrayList<>();
    private Adress adress;

    public PersonFactory(){}

    public PersonFactory setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonFactory setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public PersonFactory setAge(int age) {
        this.age = age;
        return this;
    }

    public PersonFactory addPhonenumber(String phonenumber) {
        phoneNumbers.add(phonenumber);
        return this;
    }
    public PersonFactory setAdress(Adress adress){
        this.adress = adress;
        return this;
    }

    //A person can only be built in the Phonebook class to avoid persons being built but not assigned to the database
    protected Person build(){
        return new Person(firstName,surname,age, phoneNumbers,adress);
    }


}
