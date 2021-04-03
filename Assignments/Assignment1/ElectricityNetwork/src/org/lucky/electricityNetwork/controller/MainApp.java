package org.lucky.electricityNetwork.controller;

import org.lucky.electricityNetwork.view.ValidationOutput;

/**
 * An application that models a city's electricity usage.
 * @author Lakshan Martin   Student ID: 13983521
 */
public class MainApp
{
    public static void main(String[] args)
    {
        //Validation of command line arguments
        if(args.length == 0)
        {
            ValidationOutput.instructionsMsg();
        }
        else if(args.length == 2)
        {
            if(validateTwoArgs(args))
            {
                System.out.println("Valid");
            }
            else
            {
                ValidationOutput.invalidArgsMsg();
            }            
        }
        else if(args.length == 3)
        {
            if(validateThreeArgs(args))
            {
                System.out.println("Valid");
            }
            else
            {
                ValidationOutput.invalidArgsMsg();
            }
        }
        else if(args.length == 4)
        {
            if(validateFourArgs(args))
            {
                System.out.println("Valid");
            }
            else
            {
                ValidationOutput.invalidArgsMsg();
            }
        }
        else
        {
            ValidationOutput.invalidNumArgsMsg();
        }
    }

    /**
     * Validation of two command line arguments entered
     * @param args
     * @return boolean based on validity of args entered
     */
    private static boolean validateTwoArgs(String[] args)
    {
        boolean isValid = false;

        switch(args[0].toLowerCase())
        {
            case "-g":
                if(args[1].toLowerCase().equals("-d"))
                {
                    isValid = true;
                }
            break;

            case "-d":
                if(args[1].toLowerCase().equals("-g"))
                {
                    isValid = true;
                }
            break;

            default:
                isValid = false;
        }
        
        return isValid;
    }

    /**
     * Validation of three command line arguments entered
     * @param args
     * @return boolean based on validity of args entered
     */
    private static boolean validateThreeArgs(String[] args)
    {
        boolean isValid = false;

        switch(args[0].toLowerCase())
        {
            case "-g":
                if(args[1].toLowerCase().equals("-w") && args[2].endsWith(".csv"))
                {
                    isValid = true;
                }
            break;

            case "-w": 
                if(args[1].endsWith(".csv") && args[2].toLowerCase().equals("-g"))
                {
                    isValid = true;
                }
            break;

            case "-r":
                if(args[1].endsWith(".csv") && args[2].toLowerCase().equals("-d"))
                {
                    isValid = true;
                }
            break;

            case "-d":
                if(args[1].toLowerCase().equals("-r") && args[2].endsWith(".csv"))
                {
                    isValid = true;
                }
            break;

            default:
                isValid = false;
        }

        return isValid;
    }

    /**
     * Validation of four command line arguments entered
     * @param args
     * @return boolean based on validity of args entered
     */
    private static boolean validateFourArgs(String[] args)
    {
        boolean isValid = false;

        switch(args[0].toLowerCase())
        {
            case "-r":
                if(args[1].endsWith(".csv"))
                {
                    if(args[2].toLowerCase().equals("-w") && args[3].endsWith(".csv"))
                    {
                        isValid = true;
                    }
                }
            break;

            case "-w":
                if(args[1].endsWith(".csv"))
                {
                    if(args[2].toLowerCase().equals("-r") && args[3].endsWith(".csv"))
                    {
                        isValid = true;
                    }
                }
            break;

            default:
                isValid = false;
        }

        return isValid;
    }
}