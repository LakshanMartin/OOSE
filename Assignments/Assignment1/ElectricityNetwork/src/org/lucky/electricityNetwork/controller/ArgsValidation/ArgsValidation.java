package org.lucky.electricityNetwork.controller.ArgsValidation;

/**
 * This class handles all validation relating to the command line arguments
 * entered by the user.
 */
public class ArgsValidation
{
    private ArgsValidationOutput error = new ArgsValidationOutput();

    /**
     * Validation of two command line arguments entered
     * @param args command line args
     * @throws ArgsException
     */
    public void validateTwoArgs(String[] args) throws ArgsException
    {
        switch(args[0].toLowerCase())
        {
            //Expected format: -g -d
            case "-g":
                if(!args[1].toLowerCase().equals("-d"))
                {
                    throw new ArgsException(error.getInvalidTwoArgsMsg());
                }
            break;

            //Expected format: -d -g
            case "-d":
                if(!args[1].toLowerCase().equals("-g"))
                {
                    throw new ArgsException(error.getInvalidTwoArgsMsg());
                }
            break;

            default:
                throw new ArgsException(error.getInvalidTwoArgsMsg());
        }
    }

    /**
     * Validation of three command line arguments entered
     * @param args
     * @throws ArgsException
     */
    public void validateThreeArgs(String[] args) throws ArgsException
    {
        switch(args[0].toLowerCase())
        {
            //Expected format: -g -w outputdata.csv
            case "-g":
                if(args[1].toLowerCase().equals("-w"))
                {
                    if(!args[2].endsWith(".csv")) //Output file must be .csv
                    {
                        throw new ArgsException(error.getInvalidWriteThreeArgMsg());
                    }
                }
                else
                {
                    throw new ArgsException(error.getInvalidWriteThreeArgMsg());
                }
            break;

            //Expected format: -w outputdata.csv -g
            case "-w": 
                if(args[1].endsWith(".csv")) //Output file must be .csv
                {
                    if(!args[2].toLowerCase().equals("-g"))
                    {
                        throw new ArgsException(error.getInvalidWriteThreeArgMsg());
                    }
                }
                else
                {
                    throw new ArgsException(error.getInvalidWriteThreeArgMsg());
                }
            break;

            //Expected format: -r inputdata.csv -d
            case "-r":
                if(args[1].endsWith(".csv")) //Input file must be .csv
                {
                    if(!args[2].toLowerCase().equals("-d"))
                    {
                        throw new ArgsException(error.getInvalidReadThreeArgMsg());
                    }
                }
                else
                {
                    throw new ArgsException(error.getInvalidReadThreeArgMsg());
                }
            break;

            //Expected format: -d -r inputdata.csv
            case "-d":
                if(args[1].toLowerCase().equals("-r"))
                {
                    if(!args[2].endsWith(".csv")) //Input file must be .csv
                    {
                        throw new ArgsException(error.getInvalidReadThreeArgMsg());
                    }
                }
                else
                {
                    throw new ArgsException(error.getInvalidReadThreeArgMsg());
                }
            break;

            default:
                throw new ArgsException(error.getInvalidThreeArgMsg());                
        }
    }

    /**
     * Validation of four command line arguments entered
     * @param args
     * @throws ArgsException
     */
    public void validateFourArgs(String[] args) throws ArgsException
    {
        switch(args[0].toLowerCase())
        {
            //Expected format: -r inputdata.csv -w outputdata.csv
            case "-r":
                if(args[1].endsWith(".csv"))
                {
                    if(args[2].toLowerCase().equals("-w"))
                    {
                        if(!args[3].endsWith(".csv"))
                        {
                            throw new ArgsException(error.getInvalidFourArgMsg());
                        }
                    }
                    else
                    {
                        throw new ArgsException(error.getInvalidFourArgMsg());
                    }
                }
                else
                {
                    throw new ArgsException(error.getInvalidFourArgMsg());
                }
            break;

            //Expected format: -w outputdata.csv -r inputdata.csv
            case "-w":
                if(args[1].endsWith(".csv"))
                {
                    if(args[2].toLowerCase().equals("-r"))
                    {
                        if(!args[3].endsWith(".csv"))
                        {
                            throw new ArgsException(error.getInvalidFourArgMsg());
                        }
                    }
                    else
                    {
                        throw new ArgsException(error.getInvalidFourArgMsg());
                    }
                }
                else
                {
                    throw new ArgsException(error.getInvalidFourArgMsg());
                }
            break;

            default:
                throw new ArgsException(error.getInvalidFourArgMsg());
        }
    }
}