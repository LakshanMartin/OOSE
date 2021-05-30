package edu.curtin.comp2003.rover.CommandsStrategyPattern;

import edu.curtin.comp2003.rover.CommandValidation.CommandException;
import edu.curtin.comp2003.rover.CommandValidation.CommandValidation;

/**
 * Strategy class that handles the validation of commands with tw parts.
 * EXAMPLE: "D 10.0", "T 150.0"
 */
public class TwoPartCommand implements ReadCommand
{
    @Override
    public void validateCommand(String[] command, CommandValidation check) throws CommandException 
    {
        try
        {
            check.validateTwoPartCommand(command);
        }
        catch(CommandException e)
        {
            throw e;
        }
    }
}
