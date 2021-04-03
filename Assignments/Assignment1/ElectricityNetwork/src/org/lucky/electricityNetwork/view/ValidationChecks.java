package org.lucky.electricityNetwork.view;

/**
 * Collection of error handling related methods
 */
public class ValidationChecks 
{
    public static String getInstructionsMsg()
    {
        return "\nINSTRUCTIONS" +
                "\nTo run the program please enter either of the following options:" +
                "\n1. java -jar ElectricityNetwork.jar -g -d" +
                "\n2. java -jar ElectricityNetwork.jar -g -w [outputFilename].csv" +
                "\n3. java -jar ElectricityNetwork.jar -r [inputFilename].csv -d" +
                "\n4. java -jar ElectricityNetwork.jar -r [inputFilename].csv -w [outputFilename].csv" +
                "\n\nARGUMENT DEFINITIONS:" +
                "\n -g = Generate random data" +
                "\n -r = Read data from csv file" +
                "\n -d = Display results to terminal" +
                "\n -w = Output results to csv file";
    }

    public static String getInvalidNumArgsMsg()
    {
        return "\nERROR: Invalid number of commandline arguments entered." +
                "\nTIP: Execute program without arguments for instructions!\n";
    }

    public static String getInvalidArgsMsg()
    {
        return "\nERROR: Invalid arguments entered." +
                "\nTIP: Execute program without arguments for instructions!\n";
    }
}
