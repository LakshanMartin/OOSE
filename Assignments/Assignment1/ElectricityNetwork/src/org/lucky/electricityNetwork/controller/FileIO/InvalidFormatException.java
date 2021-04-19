package org.lucky.electricityNetwork.controller.FileIO;

/**
 * Custom exception created to handle all errors relating to the validation of
 * data read in through csv file.
 * REFERENCE: javaTpoint. Java Custom Exception. 
 *            "https://www.javatpoint.com/custom-exception". 
 *            Accessed 3/04/21. 
 */
public class InvalidFormatException extends Exception
{
    private static final long serialVersionUID = 1L;

    public InvalidFormatException(String error)
    {
        super(error);
    }    
}
