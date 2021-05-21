package edu.curtin.comp2003.rover.CommandsStrategyPattern;

import edu.curtin.comp2003.rover.CommandValidation.CommandException;
import edu.curtin.comp2003.rover.CommandValidation.CommandValidation;

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
