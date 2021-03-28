package org.lucky.addressbook.model;

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
            emails.add(entry[i]); //Add emails to list
            emailList.put(entry[i], entry[0]); //Add to emailList HashMap
        }

        newEntry = new Entry(entry[0], emails); //Create new Entry object
        addressBook.put(entry[0], newEntry); //Add to addressbook HashMap
    }

    /**
     * Searches addressbook by name
     * @param name
     * @return String listing of emails 
     * @throws NoSuchElementException
     */
    public String findByName(String name) throws NoSuchElementException
    {
        Entry entry;
        List<String> emails;
        StringBuilder strEmail = new StringBuilder();

        //Retrieve Entry object 
        entry = addressBook.get(name);
        
        //Throw exception if name doesn't exist
        if(entry == null)
        {
            throw new NoSuchElementException(
                "\n\"" + name + "\" doesn't exist in address book\n");
        }

        //Retrieve emails associated with name
        emails = entry.getEmails();

        //Build string for used for output
        for(int i = 0; i < emails.size(); i++)
        {
            strEmail.append("- " + (i+1) + ": " + emails.get(i) + "\n");
        }

        return strEmail.toString();
    }

    /**
     * Searches addressbook by email
     * @param email
     * @return String name
     * @throws NoSuchElementException
     */
    public String findByEmail(String email) throws NoSuchElementException
    {
        //Retrieve name
        String name = emailList.get(email);

        //Throw exception if email doesn't exist
        if(name == null)
        {
            throw new NoSuchElementException(
                "\n\"" + email + "\" doesn't exist in address book\n");
        }

        return name;
    }
}
