package edu.curtin.comp2003.rover.RoverStatePattern;

import edu.curtin.comp2003.rover.Rover;

public class Driving implements RoverState
{
    //CONSTRUCTOR
    public Driving(){}

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

	@Override
	public void turn(Rover context) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void takePhoto(Rover context) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reportEnvironment(Rover context) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void analyseSoil(Rover context) 
	{
		// TODO Auto-generated method stub
		
	}
}
