import java.util.*;

/**
 * Contains all the address book entries.
 * 
 * @author Lakshan Martin
 */
public class AddressBook
{
    //CLASS FIELDS
    private Map<String, Entry> addressBook;
    private Map<String, String> emailList;

    //CONSTRUCTOR
    public AddressBook()
    {
        //HashMaps used for quick/convenient searching
        addressBook = new HashMap<>(); //KEY:name, VALUE:Entry object 
        emailList = new HashMap<>(); //KEY:email, VALUE:name
    }

    //METHODS
    /**
     * Creates an Entry object to be added to the addressBook HashMap and
     * adds emails to another hash map used for quick search by email.
     * @param entry Array with entry details.
     *              [0] = name
     *              [1..] = emails
     */
    public void addEntry(String[] entry)
    {
        //ArrayList used to record emails associated to new entry
        List<String> emails = new ArrayList<>();
        Entry newEntry;    

        for(int i = 1; i < entry.length; i++)
        {
            emails.add(entry[i]); //Add emails to lists
            emailList.put(entry[i], entry[0]); //Add to emailList HashMap 
        }

        newEntry = new Entry(entry[0], emails); //Create new Entry object 
        addressBook.put(entry[0], newEntry); //Add to addressbook HashMap
    }

    /**
     * Searches addressbook by name.
     * @param name 
     * @return Entry object
     */
    public Entry findByName(String name)
    {
        return addressBook.get(name);
    }

    /**
     * Identifies name through the emailList hash map, then uses that name
     * to call findByName().
     * @param email
     * @return Entry object
     */
    public Entry findByEmail(String email)
    {
        String name;

        name = emailList.get(email);

        return findByName(name);
    }
}
