package edu.curtin.comp2003.rover.RoverStatePattern;

import edu.curtin.comp2003.rover.Rover;

/**
 * This class implements the RoverState interface and represents the state the
 * Rover is in while Analysing Soil, and handles state dependent methods. 
 */
public class AnalysingSoil implements RoverState
{
    //CONSTRUCTOR
    public AnalysingSoil() {}

    /**
     * Generate error message for invalid command to Drive
     */
    @Override
    public void startDriving(Rover context, double newDist) 
    {
        context.sendMessage("! D " + newDist);
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
        context.sendMessage("! T " + newAngle);        
    }

    /**
     * Generate error message for invalid command to Start Analysing Soil
     */
    @Override
    public void analyseSoil(Rover context) 
    {
        context.sendMessage("! S");
    }
}
