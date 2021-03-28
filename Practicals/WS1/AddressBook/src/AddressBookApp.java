import java.io.*;
import java.util.*;

/**
 * A simple address book application.
 * @author Dave and Lakshan Martin
 */
public class AddressBookApp
{
    /** Used to obtain user input. */
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args)
    {
        String fileName;
        
        System.out.print("Enter address book filename: ");
        fileName = input.nextLine();
        
        try
        {
            AddressBook addressBook = readAddressBook(fileName);
            showMenu(addressBook);
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
    
    /**
     * Show the main menu, offering the user options to (1) search entries by 
     * name, (2) search entries by email, or (3) quit.
     *
     * @param addressBook The AddressBook object to search.
     */
    private static void showMenu(AddressBook addressBook)
    {
        boolean done = false;
        Entry entry;
        while(!done)
        {
            System.out.println("(1) Search by name, (2) Search by email, (3) Quit");
            
            try
            {
                switch(Integer.parseInt(input.nextLine()))
                {
                    case 1:
                        System.out.print("Enter name: ");
                        String name = input.nextLine();
                        
                        // Insert your code here to find an entry by name and display it.
                        entry = addressBook.findByName(name);

                        if(entry != null)
                        {
                            entryOutput(entry);
                        }
                        else
                        {
                            errorOutput(name);
                        }

                        break;
                        
                    case 2:
                        System.out.print("Enter email address: ");
                        String email = input.nextLine();
                        
                        // Insert your code here to find an entry by email and display it.
                        entry = addressBook.findByEmail(email);
                        
                        if(entry != null)
                        {
                            entryOutput(entry);
                        }
                        else
                        {
                            errorOutput(email);
                        }

                        break;
                        
                    case 3:
                        done = true;
                        break;
                }
            }
            catch(NumberFormatException e)
            {
                // The user entered something non-numerical.
                System.out.println("Enter a number");
            }
        }
    }

    /**
     * Outputs to terminal the Name and Email of person found in address book
     * @param entry The Entry object found in the address book
     */
    private static void entryOutput(Entry entry)
    {
        List<String> emails = entry.getEmails();

        System.out.println("\nName: " + entry.getName());
        
        for(int i = 0; i < emails.size(); i++)
        {
            System.out.println("Email " + (i+1) + ": " + emails.get(i));
        }

        System.out.println("\n");
    }

    /**
     * Outputs to terminal an error message when input isn't found in address book
     * @param input String input by the user
     */
    private static void errorOutput(String input)
    {
        System.out.println("\n\"" + input + "\" doesn't exist in address book\n");
    }
}
