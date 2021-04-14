package org.lucky.electricityNetwork;

import java.util.Random;

import org.lucky.electricityNetwork.controller.ArgsStratDesignPatt.FourArgs;
import org.lucky.electricityNetwork.controller.ArgsStratDesignPatt.ReadArgs;
import org.lucky.electricityNetwork.controller.ArgsStratDesignPatt.ThreeArgs;
import org.lucky.electricityNetwork.controller.ArgsStratDesignPatt.TwoArgs;
import org.lucky.electricityNetwork.controller.ArgsValidation.ArgsException;
import org.lucky.electricityNetwork.controller.ArgsValidation.ArgsValidation;
import org.lucky.electricityNetwork.model.CityNetworkCompositePatt.CityNode;
import org.lucky.electricityNetwork.model.DataGen.DataGeneration;

/**
 * An application that models a city's electricity usage.
 * @author Lakshan Martin   Student ID: 13983521
 */
public class MainApp 
{
    public static void main(String[] args)
    {
        ReadArgs readArgs;
        ArgsValidation check = new ArgsValidation();
        CityNode cityNetwork;

        switch(args.length)
        {
            case 0: 
                System.out.println(
                    "\nINSTRUCTIONS" +
                    "\nTo run the program please enter either of the following options:" +
                    "\n1. java -jar ElectricityNetwork.jar -g -d" +
                    "\n2. java -jar ElectricityNetwork.jar -g -w [outputFilename].csv" +
                    "\n3. java -jar ElectricityNetwork.jar -r [inputFilename].csv -d" +
                    "\n4. java -jar ElectricityNetwork.jar -r [inputFilename].csv -w [outputFilename].csv" +
                    "\n\nARGUMENT DEFINITIONS:" +
                    "\n -g = Generate random data" +
                    "\n -r = Read data from csv file" +
                    "\n -d = Display results to terminal" +
                    "\n -w = Output results to csv file\n");
            break;
            
            case 2:
                try 
                {
                    readArgs = new TwoArgs();
                    readArgs.validateArgs(args, check);
                    cityNetwork = generateData();
                    System.out.println(cityNetwork.getNetworkStr());
                } 
                catch(ArgsException e) 
                {
                    System.out.println(e.getMessage());    
                }
            break;

            case 3:
                try
                {
                    readArgs = new ThreeArgs();
                    readArgs.validateArgs(args, check);
                    System.out.println("Valid");
                    
                }
                catch(ArgsException e)
                {
                    System.out.println(e.getMessage());
                }
            break;

            case 4:
                try
                {
                    readArgs = new FourArgs();
                    readArgs.validateArgs(args, check);
                    System.out.println("Valid");
                }
                catch(ArgsException e)
                {
                    System.out.println(e.getMessage());
                }
            break;

            default:
                System.out.println(
                    "\nERROR: Invalid number of commandline arguments entered." +
                    "\nTIP: Execute program without arguments for instructions!\n");
        }

        
    }

    private static CityNode generateData()
    {
        DataGeneration dataGen;
        CityNode cityNetwork;

        dataGen = new DataGeneration();
        cityNetwork = dataGen.getCityNetwork();

        return cityNetwork;
    }
}
