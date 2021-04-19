package org.lucky.electricityNetwork.controller.ArgsValidation;

/**
 * Custom exception created to handle errors relating to command line arguments
 * entered by the user.
 * REFERENCE: javaTpoint. Java Custom Exception. 
 *            "https://www.javatpoint.com/custom-exception". 
 *            Accessed 3/04/21.
 */
public class ArgsException extends Exception 
{
    private static final long serialVersionUID = 1L;

    public ArgsException(String error)
    {
        super(error);
    }    
}
