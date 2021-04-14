package org.lucky.electricityNetwork.controller.ArgsValidation;

public class ArgsValidation
{
    private ArgsValidationOutput error = new ArgsValidationOutput();

    /**
     * Validation of two command line arguments entered
     * @param args
     * @throws ArgsException
     */
    public void validateTwoArgs(String[] args) throws ArgsException
    {
        switch(args[0].toLowerCase())
        {
            case "-g":
                if(!args[1].toLowerCase().equals("-d"))
                {
                    throw new ArgsException(error.getInvalidTwoArgsMsg());
                }
            break;

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
            case "-g":
                if(args[1].toLowerCase().equals("-w"))
                {
                    if(!args[2].endsWith(".csv"))
                    {
                        throw new ArgsException(error.getInvalidWriteThreeArgMsg());
                    }
                }
                else
                {
                    throw new ArgsException(error.getInvalidWriteThreeArgMsg());
                }
            break;

            case "-w": 
                if(args[1].endsWith(".csv"))
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

            case "-r":
                if(args[1].endsWith(".csv"))
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

            case "-d":
                if(args[1].toLowerCase().equals("-r"))
                {
                    if(!args[2].endsWith(".csv"))
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