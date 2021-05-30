package edu.curtin.comp2003.rover.RoverStatePattern;

import edu.curtin.comp2003.rover.Rover;

/**
 * This class implements the RoverState interface and represents the state the
 * Rover is in while Driving, handles state dependent methods. 
 */
public class Driving implements RoverState
{
	String defaultError;
    String customError;

    //CONSTRUCTOR
    public Driving()
	{
		defaultError = "\n! I'm sorry, Dave. I'm afraid I can't do that..."; 
        customError = "";
	}

	/**
	 * Adjust the Rover's travel target
	 */
	@Override
	public void startDriving(Rover context, double newDist)
	{
		double currDist, newTarget;

		currDist = context.getTotalDist(); 
		newTarget = currDist + newDist; //Calculate new travel target 

		context.setTravelTarget(newTarget); //Set new travel target
	}

	/**
	 * Command Rover to stop driving.
	 */
	@Override
    public void stopDriving(Rover context)
    {
		context.commandStopDriving(); //Action
		context.setRoverState(new Stopped()); //Update State
    }

	/**
	 * Command Rover to Turn
	 */
	@Override
	public void turn(Rover context, double newAngle) 
	{
		context.commandTurn(newAngle);	
	}

	/**
	 * Generate error message for invalid command to Start Analysing Soil
	 */
	@Override
	public void analyseSoil(Rover context) 
	{
		customError = "\n[Cannot start Soil Analysis while Driving]\n";
		context.sendMessage(defaultError + customError);	
	}
}
