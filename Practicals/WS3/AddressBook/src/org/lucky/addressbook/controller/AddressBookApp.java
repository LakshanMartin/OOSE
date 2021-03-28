package org.lucky.addressbook.controller;

import java.io.*;
import java.util.*;
import org.lucky.addressbook.model.AddressBook;
import org.lucky.addressbook.view.Display;

/**
 * A simple address book application.
 * @author Dave and Lakshan
 */
public class AddressBookApp 
{
    /** Used to obtain user input. */
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args)
    {
        String fileName;
        OptionManager optMan;
        AddressBook addressBook;   
        
        System.out.print("Enter address book filename: ");
        fileName = input.nextLine();
        
        try
        {
            addressBook = readAddressBook(fileName);
            
            optMan = new OptionManager(addressBook);
            optMan.addOption(1, new SearchByName(addressBook));
            optMan.addOption(2, new SearchByEmail(addressBook));

            Display.showMenu(optMan);
        }
        catch(IOException e)
        {
            System.out.println("Could not read from " + fileName + ": " + e.getMessage());
        }
    }
    
    /**
     * Read the address book file, containing all the names and email addresses.
     *
     * @param fileName The name of the address book file.
     * @return A new AddressBook object containing all the information.
     * @throws IOException If the file cannot be read.
     */
    private static AddressBook readAddressBook(String fileName) throws IOException
    {
        AddressBook addressBook = new AddressBook();
        
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        while(line != null)
        {
            String[] parts = line.split(":");
            
            // Insert your code here to add a new address book entry.
            // Note: 
            // parts[0] contains the person's name.
            // parts[1], parts[2], etc. contain the person's email address(es).
            addressBook.addEntry(parts);
            
            line = reader.readLine();
        }
        reader.close();
        
        return addressBook;
    }
}
