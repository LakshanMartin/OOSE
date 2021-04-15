package org.lucky.electricityNetwork;

import java.io.IOException;

import org.lucky.electricityNetwork.controller.ArgsStratDesignPatt.*;
import org.lucky.electricityNetwork.controller.ArgsValidation.*;
import org.lucky.electricityNetwork.controller.FileIO.WriteFile;
import org.lucky.electricityNetwork.model.CityNetworkCompositePatt.CityNode;
import org.lucky.electricityNetwork.model.DataGen.DataGeneration;
import org.lucky.electricityNetwork.view.Display;


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
        String input;
        Display display;
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
                    //Validate arguments
                    readArgs = new TwoArgs();
                    readArgs.validateArgs(args, check);
                    
                    //Generate random data
                    cityNetwork = generateData();
                    
                    //Display tree and power consumption table
                    display = new Display(cityNetwork);
                    display.displayNetwork();
                    display.displayPowerConsumption();
                    display.displayData();
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
                    input = readArgs.validateArgs(args, check);
                    runThreeArgs(input, args);
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

        dataGen = new DataGeneration("Perth");
        dataGen.buildTree();
        cityNetwork = dataGen.getCityNetwork();

        return cityNetwork;
    }

    /**
     * This method identifies which arg contains the filenames based on 
     * expected order of validated args entered. This is done by having input
     * identify what was in arg[0].
     * EXAMPLE:
     *      if arg[0] = -g
     *          Then except valid args entered would be in the format:
     *              [-g -w outputdata.csv]
     *      Therefore, filename will be found in arg[2].
     * @param input Identify of first arg entered by user
     * @param args Full array of args entered
     */
    private static void runThreeArgs(String input, String[] args)
    {
        CityNode cityNetwork;

        switch(input)
        {
            case "g":
                cityNetwork = generateData();
                writeFile(cityNetwork, args[2]);
            break;

            case "w":
                cityNetwork = generateData();
                writeFile(cityNetwork, args[1]);
            break;
        }
    }

    private static void writeFile(CityNode network, String filename)
    {
        WriteFile wf;

        wf = new WriteFile();

        try
        {
            wf.writeFile(filename, network);
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
