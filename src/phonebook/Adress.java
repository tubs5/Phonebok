package phonebook;

import java.io.Serializable;

/**
 * @author Tobias Heidlund
 */
@SuppressWarnings({"CanBeFinal", "unused"})
public class Adress implements Serializable, SearchableAdress {

    private final String city;
    private final String adress;
    private final int postalAdress;
    private final int portNumber;

    protected Adress(String city, String adress, int postalAdress, int portNumber) {
        this.city = city;
        this.adress = adress;
        this.postalAdress = postalAdress;
        this.portNumber = portNumber;
    }

    @Override
    public boolean search(String o) {
        return this.toString().toLowerCase().contains(o.toLowerCase());
    }

    @Override
    public String toString() {
        return portNumber +
                " " +
                adress +
                ", " +
                city +
                ", " +
                postalAdress;
    }

    @Override
    public boolean searchAdress(String s) {
        return adress.equalsIgnoreCase(s);
    }


}
