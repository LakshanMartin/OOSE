import java.io.*;
import java.util.*;

/**
 * A simple address book application.
 * @author Dave and Lakshan
 */
public class AddressBookApp 
{
    /** Used to obtain user input. */
    private static Scanner input = new Scanner(System.in);
    private static Map<Integer, Option> options;
    
    public static void main(String[] args)
    {
        String fileName;
        
        
        System.out.print("Enter address book filename: ");
        fileName = input.nextLine();
        
        try
        {
            AddressBook addressBook = readAddressBook(fileName);
            
            //Integer values representing menu option selection are the keys
            options = new HashMap<>();
            options.put(1, new SearchByName(addressBook));
            options.put(2, new SearchByEmail(addressBook));

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
        String result;
        boolean done = false;
        int selection;


        while(!done)
        {
            System.out.println("(1) Search by name, (2) Search by email, (3) Quit");
            
            try
            {
                //Utilise user input to select specific option
                switch(selection = Integer.parseInt(input.nextLine()))
                {
                    case 1:
                        System.out.print("Enter name: ");
                        String name = input.nextLine();
                        
                        // Insert your code here to find an entry by name and display it.
                        try
                        {
                            //This option object utilises SearchByName class
                            result = options.get(selection).doOption(name);

                            System.out.println(
                                "\nEmail(s) listed for " + name + ":\n" + result);
                        } 
                        catch (NoSuchElementException e) 
                        {
                            System.out.println(e.getMessage());
                        }

                        break;
                        
                    case 2:
                        System.out.print("Enter email address: ");
                        String email = input.nextLine();
                        
                        // Insert your code here to find an entry by email and display it.
                        try 
                        {
                            //This option object utilises SearchByEmail class
                            result = options.get(selection).doOption(email);

                            System.out.println("\nName: " + result + "\n");   
                        } 
                        catch (NoSuchElementException e) 
                        {
                            System.out.println(e.getMessage());
                        }

                        break;
                        
                    case 3:
                        done = true;
                        break;

                    default:
                        System.out.println("\nERROR: Enter an integer between 1-3\n");
                }
            }
            catch(NumberFormatException e)
            {
                // The user entered something non-numerical.
                System.out.println("\nERROR: Enter a number");
            }
        }
    }
}
