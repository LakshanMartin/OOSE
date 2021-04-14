package org.lucky.electricityNetwork.controller.FileIO;

public class InvalidFormatException extends Exception
{
    private static final long serialVersionUID = 1L;

    public InvalidFormatException(String error)
    {
        super(error);
    }    
}
