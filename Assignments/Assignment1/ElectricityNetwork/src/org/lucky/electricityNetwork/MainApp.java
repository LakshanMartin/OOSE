package org.lucky.electricityNetwork;

import java.io.IOException;

import org.lucky.electricityNetwork.controller.DataGeneration;
import org.lucky.electricityNetwork.controller.ArgsStratDesignPatt.*;
import org.lucky.electricityNetwork.controller.ArgsValidation.*;
import org.lucky.electricityNetwork.controller.FileIO.*;
import org.lucky.electricityNetwork.model.CityNetworkCompositePatt.CityNode;
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
        Display display;
        CityNode cityNetwork;

        switch(args.length)
        {
            case 0: 
                System.out.println(
                    "\nINSTRUCTIONS" +
                    "\nTo run the program please enter either of the following commands:" +
                    "\n   java -jar ElectricityNetwork.jar -g -d" +
                    "\n   java -jar ElectricityNetwork.jar -g -w [outputFilename].csv" +
                    "\n   java -jar ElectricityNetwork.jar -r [inputFilename].csv -d" +
                    "\n   java -jar ElectricityNetwork.jar -r [inputFilename].csv -w [outputFilename].csv" +
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
                } 
                catch(ArgsException e) 
                {
                    System.out.println(e.getMessage());    
                }
            break;

            case 3:
                try
                {
                    //Validate arguments
                    readArgs = new ThreeArgs();
                    readArgs.validateArgs(args, check);

                    runThreeArgs(args);
                }
                catch(ArgsException e)
                {
                    System.out.println(e.getMessage());
                }
            break;

            case 4:
                try
                {
                    //Validate arguments
                    readArgs = new FourArgs();
                    readArgs.validateArgs(args, check);
                    
                    runFourArgs(args);
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

    /**
     * Creates a DataGeneration object used to generate data and build the City
     * Network tree.
     * @return Generated tree
     */
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
     * expected order of validated args entered, and performs the relevant 
     * function. This is done by identifyng what was value is in arg[0].
     * EXAMPLE:
     *      if arg[0] = -g
     *          Then except valid args entered would be in the format:
     *              [-g -w outputdata.csv]
     *      Therefore, filename will be found in arg[2].
     * @param args Full array of args entered
     */
    private static void runThreeArgs(String[] args)
    {
        CityNode cityNetwork;
        Display display;

        switch(args[0])
        {
            //Expected format: -g -w outputdata.csv
            case "-g":
                cityNetwork = generateData();
                writeFile(cityNetwork, args[2]);
            break;

            //Expected format: -w outputdata.csv -g
            case "-w":
                cityNetwork = generateData();
                writeFile(cityNetwork, args[1]);
            break;

            //Expected format: -r inputdata.csv -d
            case "-r":
                cityNetwork = readFile(args[1]);

                if(cityNetwork != null)
                {
                    display = new Display(cityNetwork);
                    display.displayNetwork();
                    display.displayPowerConsumption();
                }
            break;

            //Expected format: -d -r inputdata.csv
            case "-d":
                cityNetwork = readFile(args[2]);

                if(cityNetwork != null)
                {
                    display = new Display(cityNetwork);
                    display.displayNetwork();
                    display.displayPowerConsumption();
                }
            break;
        }
    }

    /**
     * This method performs the same function as runThreeArgs(), except that it
     * works for four arguments. Please see comment for runThreeArgs() for
     * further details. 
     * @param args
     */
    private static void runFourArgs(String[] args)
    {
        CityNode cityNetwork;

        switch(args[0])
        {
            //Expected format: -r inputdata.csv -w outputdata.csv
            case "-r":
                cityNetwork = readFile(args[1]);

                if(cityNetwork != null)
                {
                    writeFile(cityNetwork, args[3]);
                }
            break;

            //Expected format: -w outputdata.csv -r inputdata.csv
            case "-w":
                cityNetwork = readFile(args[3]);

                if(cityNetwork != null)
                {
                    writeFile(cityNetwork, args[1]);
                }
            break;
        }
    }

    /**
     * The method creates a WriteFile object used to write the City Network
     * tree structure and total power consumption to file.
     * @param network Tree structure
     * @param filename output filename
     */
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

    /**
     * This method creates a ReadFile object used to read data in from a file
     * and build a City network tree structure.
     * @param filename
     * @return Tree structure
     */
    private static CityNode readFile(String filename)
    {
        ReadFile rf;
        CityNode cityNetwork;

        rf = new ReadFile();
        cityNetwork = null;

        try
        {
            rf.readFile(filename);
            cityNetwork = rf.getCityNetwork();
        }
        catch(IOException e)
        {
            System.out.println("\nERROR: Problem reading input file." + 
                                "\n       [" + e.getMessage() + "]\n");
        }
        catch(InvalidFormatException e)
        {
            System.out.println(e.getMessage());
        }

        return cityNetwork;
    }
}
