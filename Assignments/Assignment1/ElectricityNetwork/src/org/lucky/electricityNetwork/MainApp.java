package org.lucky.electricityNetwork;

import org.lucky.electricityNetwork.controller.ArgsValidation;
import org.lucky.electricityNetwork.view.ValidationOutput;

/**
 * An application that models a city's electricity usage.
 * @author Lakshan Martin   Student ID: 13983521
 */
public class MainApp 
{
    public static void main(String[] args)
    {
        //Validates the correct number of arguments have been entered
        switch(args.length)
        {
            case 0:
                ValidationOutput.instructionsMsg();
            break;

            case 2: case 3: case 4:
                ArgsValidation.readCLArgs(args);
            break;

            default:
                ValidationOutput.invalidNumArgsMsg();
        }
    }
}
