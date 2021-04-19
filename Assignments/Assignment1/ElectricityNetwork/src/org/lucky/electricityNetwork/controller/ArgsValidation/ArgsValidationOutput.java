package org.lucky.electricityNetwork.controller.ArgsValidation;

/**
 * Collection of error messages relating command line argument validations to 
 * be output to the user.
 */
public class ArgsValidationOutput
{
    //CLASS FIELDS
    private String tip;
    private String generalMsg;
    private String invalidTwoArgsMsg;
    private String invalidWriteThreeArgMsg;
    private String invalidReadThreeArgMsg;
    private String invalidThreeArgMsg;
    private String invalidFourArgMsg;

    //CONSTRUCTOR
    public ArgsValidationOutput()
    {
        tip = "\nTIP: Execute program without arguments for instructions!\n";
        generalMsg = "\nERROR: Invalid arguments entered.";

        //Error msgs for Two args entered scenario
        invalidTwoArgsMsg = generalMsg + "\nTRY: [-g -d]" + tip;

        //Error msgs for Three args entered scenario
        invalidWriteThreeArgMsg = generalMsg + "\nTRY: [-g -w filename.csv]" + tip;
        invalidReadThreeArgMsg = generalMsg + "\nTRY: [-r filename.csv -d]" + tip;
        invalidThreeArgMsg = generalMsg + "\nTRY: [-g -w filename.csv] OR [-r filename.csv -d]" + tip;

        //Error msgs for Four args entered scenario
        invalidFourArgMsg = generalMsg + "\nTRY: [-r inFilename.csv -w outFilename.csv]" + tip;
    }

    //ACCESSORS
    public String getInvalidTwoArgsMsg()
    {
        return invalidTwoArgsMsg;
    }

    public String getInvalidWriteThreeArgMsg()
    {
        return invalidWriteThreeArgMsg;
    }

    public String getInvalidReadThreeArgMsg()
    {
        return invalidReadThreeArgMsg;
    }    

    public String getInvalidThreeArgMsg()
    {
        return invalidThreeArgMsg;
    }

    public String getInvalidFourArgMsg()
    {
        return invalidFourArgMsg;
    }
}
