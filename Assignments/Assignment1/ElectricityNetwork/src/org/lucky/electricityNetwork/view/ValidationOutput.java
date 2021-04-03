package org.lucky.electricityNetwork.view;

/**
 * Collection of error handling related methods
 */
public class ValidationOutput
{
    public static void instructionsMsg()
    {
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
    }

    public static void invalidNumArgsMsg()
    {
        System.out.println(
            "\nERROR: Invalid number of commandline arguments entered." +
            "\nTIP: Execute program without arguments for instructions!\n");
    }

    public static void invalidArgsMsg()
    {
        System.out.println(
            "\nERROR: Invalid arguments entered." +
            "\nTIP: Execute program without arguments for instructions!\n");
    }
}
