package org.lucky.addressbook.view;

import java.util.*;
import org.lucky.addressbook.controller.OptionManager;

public class Display 
{
    /**
     * Show the main menu, offering the user options to (1) search entries by 
     * name, (2) search entries by email, or (3) quit.
     * @param optMan
     */
    public static void showMenu(OptionManager optMan)
    {
        String result;
        boolean done = false;
        int selection;
        Scanner input = new Scanner(System.in);


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
                        
                        try
                        {
                            //This option will utilise SearchByName class
                            result = optMan.doOption(selection, name);

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
                        
                        try 
                        {
                            //This option will utilise SearchByEmail class
                            result = optMan.doOption(selection, email);

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

                    default: //Input error checking
                        System.out.println("\nERROR: Enter an integer between 1-3\n");
                }
            }
            catch(NumberFormatException e)
            {
                // The user entered something non-numerical.
                System.out.println("\nERROR: Enter a number");
            }
        }

        input.close();
    }
}
