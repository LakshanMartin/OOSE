package org.lucky.electricityNetwork.controller.ArgsStratDesignPatt;

import org.lucky.electricityNetwork.controller.ArgsValidation.ArgsException;
import org.lucky.electricityNetwork.controller.ArgsValidation.ArgsValidation;

public class FourArgs implements ReadArgs
{
    @Override
    public void validateArgs(String[] args, ArgsValidation check) throws ArgsException 
    {
        try
        {
            check.validateFourArgs(args);
        }    
        catch(ArgsException exception)
        {
            throw exception;
        }
    }
        
}
