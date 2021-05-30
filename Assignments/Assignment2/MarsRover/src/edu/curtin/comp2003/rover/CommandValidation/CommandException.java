package edu.curtin.comp2003.rover.CommandValidation;

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
        super("! I'm sorry, Dave. I'm afraid I can't do that..." + 
                "\n[" + error + "]\n");
    }    
}
