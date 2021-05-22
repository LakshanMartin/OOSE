package edu.curtin.comp2003.rover.RoverStatePattern;

import edu.curtin.comp2003.rover.Rover;

public class Idle implements RoverState
{
    //CONSTRUCTOR
    public Idle(){}

    /**
     * Three steps performed:
     *  1. Set Rover's travel target
     *  2. Command rover to drive
     *  3. Set Rover State to Driving
     */
    @Override
    public void drive(Rover context, double newDist)
    {
        double totalDist;

        totalDist = context.getTotalDist();
        context.setTravelTarget(totalDist + newDist);

        context.commandDrive(); //Action
        context.setState(new Driving()); //Update State
    }

    @Override
    public void turn(Rover context) 
    {
        
    }

    @Override
    public void takePhoto(Rover context) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void reportEnvironment(Rover context) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void analyseSoil(Rover context) {
        // TODO Auto-generated method stub
        
    }

    
    
}
