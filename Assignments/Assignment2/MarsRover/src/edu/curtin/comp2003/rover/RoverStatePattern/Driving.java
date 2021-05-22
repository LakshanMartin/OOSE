package edu.curtin.comp2003.rover.RoverStatePattern;

import edu.curtin.comp2003.rover.Rover;

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
	public void drive(Rover context, double newDist)
	{
		double currDist, newTarget;

		currDist = context.getTotalDist(); 
		newTarget = currDist + newDist; 

		context.setTravelTarget(newTarget);
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
