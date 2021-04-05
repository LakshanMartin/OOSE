package org.lucky.electricityNetwork.controller.ArgsValidation;

public class ArgsException extends Exception 
{
    private static final long serialVersionUID = 1L;

    public ArgsException(String error)
    {
        super(error);
    }    
}
