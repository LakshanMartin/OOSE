package edu.curtin.comp2003.CommandsStrategyPattern;

import edu.curtin.comp2003.CommandValidation.*;

/**
 * Interface class to be implemented as a part of the Strategy Design Pattern
 * used to validate commands received through the EarthComm.pollCommand() API.
 */
public interface ReadCommand 
{
    public void validateCommand(String[] command, CommandValidation check) throws CommandException;
}
