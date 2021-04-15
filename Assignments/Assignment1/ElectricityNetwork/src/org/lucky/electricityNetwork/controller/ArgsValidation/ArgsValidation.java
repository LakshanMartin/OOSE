package org.lucky.electricityNetwork.controller.ArgsValidation;

public class ArgsValidation
{
    private ArgsValidationOutput error = new ArgsValidationOutput();

    /**
     * Validation of two command line arguments entered
     * @param args
     * @throws ArgsException
     */
    public String validateTwoArgs(String[] args) throws ArgsException
    {
        String input;

        switch(args[0].toLowerCase())
        {
            case "-g":
                if(!args[1].toLowerCase().equals("-d"))
                {
                    throw new ArgsException(error.getInvalidTwoArgsMsg());
                }

                input = "g";
            break;

            case "-d":
                if(!args[1].toLowerCase().equals("-g"))
                {
                    throw new ArgsException(error.getInvalidTwoArgsMsg());
                }

                input = "d";
            break;

            default:
                throw new ArgsException(error.getInvalidTwoArgsMsg());
        }

        return input;
    }

    /**
     * Validation of three command line arguments entered
     * @param args
     * @throws ArgsException
     */
    public String validateThreeArgs(String[] args) throws ArgsException
    {
        String input;

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

                input = "g";
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

                input = "w";
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

                input = "r";
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

                input = "d";
            break;

            default:
                throw new ArgsException(error.getInvalidThreeArgMsg());                
        }

        return input;
    }

    /**
     * Validation of four command line arguments entered
     * @param args
     * @throws ArgsException
     */
    public String validateFourArgs(String[] args) throws ArgsException
    {
        String input;

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

                input = "r";
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

                input = "w";
            break;

            default:
                throw new ArgsException(error.getInvalidFourArgMsg());
        }

        return input;
    }
}