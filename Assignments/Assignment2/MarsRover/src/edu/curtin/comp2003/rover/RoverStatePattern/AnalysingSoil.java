package edu.curtin.comp2003.rover.RoverStatePattern;

import edu.curtin.comp2003.rover.Rover;

/**
 * This class implements the RoverState interface and represents the state the
 * Rover is in while Analysing Soil, and handles state dependent methods. 
 */
public class AnalysingSoil implements RoverState
{
    String defaultError;
    String customError;

    //CONSTRUCTOR
    public AnalysingSoil()
    {
        defaultError = "\n! I'm sorry, Dave. I'm afraid I can't do that..."; 
        customError = "";
    }

    /**
     * Generate error message for invalid command to Drive
     */
    @Override
    public void startDriving(Rover context, double newDist) 
    {
        customError = "\n[Cannot start Driving while performing Soil Analysis]\n";
        context.sendMessage(defaultError + customError);
    }

    /**
     * This method does nothing in this State, as the Rover wouldn't be driving
     * in the first place if it was Analysing Soil.
     */
    @Override
    public void stopDriving(Rover context)
    {
        //Do nothing. Wouldn't be Driving.     
    }

    /**
     * Generate error message for invalid command to Turn
     */
    @Override
    public void turn(Rover context, double newAngle) 
    {
        customError = "\n[Cannot turn while performing Soil Analysis]\n";
        context.sendMessage(defaultError + customError);        
    }

    /**
     * Generate error message for invalid command to Start Analysing Soil
     */
    @Override
    public void analyseSoil(Rover context) 
    {
        customError = "\n[Cannot start Soil Analysis while already performing Soil Analysis]\n";
        context.sendMessage(defaultError + customError);
    }
}
