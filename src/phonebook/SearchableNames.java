package phonebook;

@SuppressWarnings("unused")
public interface SearchableNames extends Searchable{

    boolean searchFirstName(String s);
    boolean searchSurName(String s);
}
