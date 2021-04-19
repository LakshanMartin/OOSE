package org.lucky.electricityNetwork.controller.ArgsStratDesignPatt;

import org.lucky.electricityNetwork.controller.ArgsValidation.ArgsException;
import org.lucky.electricityNetwork.controller.ArgsValidation.ArgsValidation;

/**
 * Interface class to be implemented as part of the Strategy Design Pattern used
 * to validate command line arguments entered by the user.
 */
public interface ReadArgs 
{
    public void validateArgs(String[] args, ArgsValidation check) throws ArgsException;    
}
