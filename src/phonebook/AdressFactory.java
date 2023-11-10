package phonebook;

/**
 * @author Tobias Heidlund
 */
public class AdressFactory {
    private String city,adress;
    private int postalAdress, portNumber;

    public AdressFactory setCity(String city) {
        this.city = city;
        return this;
    }

    public AdressFactory setAdress(String adress) {
        this.adress = adress;
        return this;
    }

    public AdressFactory setPostalAdress(int postalAdress) {
        this.postalAdress = postalAdress;
        return this;
    }

    public AdressFactory setPortNumber(int portNumber) {
        this.portNumber = portNumber;
        return this;
    }

    public Adress build(){
        return new Adress(city,adress,postalAdress,portNumber);
    }
}
