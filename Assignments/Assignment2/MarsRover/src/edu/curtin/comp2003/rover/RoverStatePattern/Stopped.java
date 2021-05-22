package edu.curtin.comp2003.rover.RoverStatePattern;

import edu.curtin.comp2003.rover.Rover;

public class Stopped implements RoverState
{
    //CONSTRUCTOR
    public Stopped(){}

    /**
     *  Set Rover's travel target. Command rover to Drive
     */
    @Override
    public void drive(Rover context, double newDist)
    {
        context.setTravelTarget(context.getTotalDist() + newDist);
        context.commandDrive();
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
     * Command Rover to start Soil Analysis
     */
    @Override
    public void analyseSoil(Rover context) 
    {
        context.commandSoilAnalysis();
    }
}
