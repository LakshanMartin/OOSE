package edu.curtin.comp2003.rover.CommandValidation;

import java.math.BigDecimal;

/**
 * Class containing methods to validate commands received through EartComm API.
 */
public class CommandValidation 
{
    //EMPTY CONSTRUCTOR
    public CommandValidation(){}

    /**
     * Validate commands that consists of two parts. 
     * EXAMPLE:
     *      - "D 10.0" 
     *      - "T 150.0"
     * @param command
     * @throws CommandException
     */
    public void validateTwoPartCommand(String[] command) throws CommandException
    {
        double value;

        try
        {
            switch(command[0])
            {
                case "D":
                    value = validateDouble(command[1]);
                    validateDistance(value);
                break;

                case "T":
                    value = validateDouble(command[1]);
                    validateAngle(value);
                break;

                default:
                    throw new CommandException("Invalid command");
            }
        }
        catch(NumberFormatException e) //Catch non-number values
        {
            throw new CommandException(e.getMessage());
        }
        catch(CommandException e)
        {
            throw e;
        }
    }

    /**
     * Validate commands that consists of a single part.
     * EXAMPLE:
     *      - "S"
     *      - "E"
     *      - "P"
     * @param command
     * @throws CommandException
     */
    public void validateOnePartCommand(String[] command) throws CommandException
    {
        //Using switch because i think syntax looks nicer than an if-else statement
        //with multiple conditions.
        switch(command[0])
        {
            case "P": case "E": case "S":
                //Just checking...
            break;

            default:
                throw new CommandException("Invalid command");
        }
    }

    /**
     * Check that string value can be parsed to a double
     * @param toCheck
     * @return double value
     * @throws NumberFormatException
     */
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

    /**
     * Check that travel distance requested is a positive number
     * @param distance
     * @throws CommandException
     */
    private void validateDistance(double distance) throws CommandException
    {
        if(distance <= 0.0)
        {
            throw new CommandException("Invalid Distance");
        }
    }

    /**
     * Check that angle requested is within the range of -180.0 and 180.0
     * degrees.
     * @param angle
     * @throws CommandException
     */
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
