package org.lucky.electricityNetwork.controller.ArgsStratDesignPatt;

import org.lucky.electricityNetwork.controller.ArgsValidation.ArgsException;
import org.lucky.electricityNetwork.controller.ArgsValidation.ArgsValidation;

public interface ReadArgs 
{
    public void validateArgs(String[] args, ArgsValidation check) throws ArgsException;    
}
