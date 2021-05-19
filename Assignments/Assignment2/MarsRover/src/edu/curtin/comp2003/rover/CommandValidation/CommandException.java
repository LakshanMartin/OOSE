package edu.curtin.comp2003.rover.CommandValidation;

public class CommandException extends Exception
{
    public CommandException(String error)
    {
        super("\n! I'm sorry, Dave. I'm afraid I can't do that..." + 
                "\n[" + error + "]\n");
    }    
}
