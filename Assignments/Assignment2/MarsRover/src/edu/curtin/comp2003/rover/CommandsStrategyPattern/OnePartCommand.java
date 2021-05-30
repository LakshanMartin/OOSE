package edu.curtin.comp2003.rover.CommandsStrategyPattern;

import edu.curtin.comp2003.rover.CommandValidation.*;

/**
 * Strategy class that handles the validation of commands with one part.
 * EXAMPLE: "P", "E", "S"
 */
public class OnePartCommand implements ReadCommand
{
    @Override
    public void validateCommand(String[] command, CommandValidation check) throws CommandException 
    {
        try
        {
            check.validateOnePartCommand(command);
        }
        catch(CommandException e)
        {
            throw e;
        }
    }
}
