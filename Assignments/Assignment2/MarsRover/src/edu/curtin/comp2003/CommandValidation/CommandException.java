package edu.curtin.comp2003.CommandValidation;

/**
 * Custom exception to handle invalid commands received.
 * 
 * REFERENCE: javaTpoint. Java Custom Exception. 
 *            "https://www.javatpoint.com/custom-exception". 
 *            Accessed 3/04/21.
 */
public class CommandException extends Exception
{
    public CommandException(String error)
    {
        super("! " + error + "\n");
    }    
}
