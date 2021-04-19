package org.lucky.electricityNetwork.controller.ArgsStratDesignPatt;

import org.lucky.electricityNetwork.controller.ArgsValidation.ArgsException;
import org.lucky.electricityNetwork.controller.ArgsValidation.ArgsValidation;

/**
 * Strategy class that handles the validation of three command line arguments.
 */
public class ThreeArgs implements ReadArgs
{
    /**
     * Validate command line arguments entered.
     * @param args array of arguments entered
     * @param check ArgsValidation object used to validate input
     * @throws ArgsException Custom exception
     */
    @Override
    public void validateArgs(String[] args, ArgsValidation check) throws ArgsException 
    {
        try
        {
            check.validateThreeArgs(args);
        }
        catch(ArgsException exception)
        {
            throw exception;
        }
    }        
}
