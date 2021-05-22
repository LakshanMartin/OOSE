package edu.curtin.comp2003.rover.RoverStatePattern;

import edu.curtin.comp2003.rover.EngineSystem;
import edu.curtin.comp2003.rover.Rover;

public class Idle implements RoverState
{
    //CONSTRUCTOR
    public Idle(){}

    @Override
    public void drive(Rover context) 
    {
        String[] command;
        double travelDist, totalDist;
        EngineSystem engSys;

        command = context.getCommand().split(" ");
        travelDist = Double.parseDouble(command[1]);
        totalDist = context.getTotalDist();
        engSys = context.apiData.getEngSys();

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
