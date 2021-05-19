package edu.curtin.comp2003.rover.CommandValidation;

import java.math.BigDecimal;

public class CommandValidation 
{
    public CommandValidation(){}

    public void validateTwoPartCommand(String[] command) throws CommandException
    {
        double value;

        try
        {
            switch(command[0])
            {
                case "D":
                    validateDouble(command[1]);
                break;

                case "T":
                    value = validateDouble(command[1]);
                    validateAngle(value);
                break;

                default:
                    throw new CommandException("Invalid command");
            }
        }
        catch(NumberFormatException e)
        {
            throw new CommandException(e.getMessage());
        }
        catch(CommandException e)
        {
            throw e;
        }
    }
    
    public void validateOnePartCommand(String[] command) throws CommandException
    {

    }
    
    private double validateDouble(String toCheck) throws NumberFormatException
    {
        double value;

        try
        {
            value = Double.parseDouble(toCheck);
        }
        catch(NumberFormatException e)
        {
            throw e;
        }

        return value;
    }

    private void validateAngle(double angle) throws CommandException
    {
        BigDecimal min, max, toCheck;

        min = BigDecimal.valueOf(-180.0);
        max = BigDecimal.valueOf(180.0);
        toCheck = BigDecimal.valueOf(angle);

        if(toCheck.compareTo(min) < 0 || toCheck.compareTo(max) > 0)
        {
            throw new CommandException("Invalid Turn angle");
        }
    }
}
