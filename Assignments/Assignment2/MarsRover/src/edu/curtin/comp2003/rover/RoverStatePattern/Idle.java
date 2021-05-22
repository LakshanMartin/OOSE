package edu.curtin.comp2003.rover.RoverStatePattern;

import edu.curtin.comp2003.rover.Rover;

public class Idle implements RoverState
{
    //CONSTRUCTOR
    public Idle(){}

    @Override
    public void drive(Rover context) 
    {
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
