package edu.curtin.comp2003.rover;

import edu.curtin.comp2003.rover.CommandValidation.CommandException;
import edu.curtin.comp2003.rover.CommandValidation.CommandValidation;

public class CommsHandler 
{
    public CommsHandler(){}

    public void processCommand(String command) throws CommandException
    {
        try
        {
            validateCommand(command);
            identifyCommand(command);
        }
        catch(CommandException e)
        {
            throw e;
        }    
    }

    private void validateCommand(String command) throws CommandException
    {
        CommandValidation commValid;
        String[] toValidate;

        commValid = new CommandValidation();
        toValidate = command.split(" ");

        try
        {
            switch(toValidate.length)
            {
                case 1:
                    commValid.validateOnePartCommand(toValidate);
                break;

                case 2:
                    commValid.validateTwoPartCommand(toValidate);
                break;

                default:
                    throw new CommandException("Invalid command");
            }
        }
        catch(CommandException e)
        {
            throw e;
        }
    }
    
    private void identifyCommand(String command)
    {
        
    }
}
