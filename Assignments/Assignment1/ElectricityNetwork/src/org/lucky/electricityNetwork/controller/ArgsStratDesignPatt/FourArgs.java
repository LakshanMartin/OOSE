package org.lucky.electricityNetwork.controller.ArgsStratDesignPatt;

import org.lucky.electricityNetwork.controller.ArgsValidation.ArgsException;
import org.lucky.electricityNetwork.controller.ArgsValidation.ArgsValidation;

public class FourArgs implements ReadArgs
{
    @Override
    public String validateArgs(String[] args, ArgsValidation check) throws ArgsException 
    {
        String input;

        try
        {
            input = check.validateFourArgs(args);
        }    
        catch(ArgsException exception)
        {
            throw exception;
        }

        return input;
    }
        
}
