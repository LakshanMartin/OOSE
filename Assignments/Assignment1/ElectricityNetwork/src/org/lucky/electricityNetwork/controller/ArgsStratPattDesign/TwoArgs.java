package org.lucky.electricityNetwork.controller.ArgsStratPattDesign;

import org.lucky.electricityNetwork.controller.ArgsValidation.ArgsException;
import org.lucky.electricityNetwork.controller.ArgsValidation.ArgsValidation;

public class TwoArgs implements ReadArgs
{
    @Override
    public void validateArgs(String[] args, ArgsValidation check) throws ArgsException
    {
        try
        {
            check.validateTwoArgs(args);
        }
        catch(ArgsException exception)
        {
            throw exception;
        }
    }
}
