package phonebook;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Tobias Heidlund
 */
public class Person implements Serializable,SearchableNames {

    private String firstName,surname;
    private int age;
    private final List<String> phonenumber;
    private Adress adress;

    protected Person(String firstName, String surname, int age, List<String> phonenumber,Adress adress) {
        this.firstName = firstName;
        this.surname = surname;
        this.age = age;
        this.phonenumber = phonenumber;
        this.adress = adress;
    }

    @Override
    public boolean search(String o) {
        String searchString =
           firstName +
           surname +
           age +
           Arrays.toString(phonenumber.toArray()) +
           adress.toString();

        return searchString.contains(o.toLowerCase());
    }

    @Override
    public boolean searchFirstName(String s) {
        return s.equalsIgnoreCase(firstName);
    }

    @Override
    public boolean searchSurName(String s) {
        return s.equalsIgnoreCase(surname);
    }

    @Override
    public String toString() {
        return surname +
                ", " +
                firstName +
                " " +
                age +
                " years old " +
                Arrays.toString(phonenumber.toArray()) +
                " Lives at" +
                adress.toString();
    }

    public void updateFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void updateSurname(String surname) {
        this.surname = surname;
    }

    public void updateAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(surname, person.surname) &&
                Objects.equals(phonenumber, person.phonenumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, surname, age, phonenumber);
    }

    public Adress getAdress() {
        return adress;
    }
    public void setAdress(Adress adress){
        this.adress = adress;
    }

    public List<String> getPhoneNumbers() {
        return phonenumber;
    }

    public void addPhonenumber(String string) {
        phonenumber.add(string);
    }
}
