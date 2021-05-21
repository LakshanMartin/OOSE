package edu.curtin.comp2003.rover.CommandsStrategyPattern;

import edu.curtin.comp2003.rover.CommandValidation.CommandException;
import edu.curtin.comp2003.rover.CommandValidation.CommandValidation;

public interface ReadCommand 
{
    public void validateCommand(String[] command, CommandValidation check) throws CommandException;
}
