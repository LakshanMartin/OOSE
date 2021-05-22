package edu.curtin.comp2003.rover.RoverStatePattern;

import edu.curtin.comp2003.rover.Rover;

public class AnalysingSoil implements RoverState
{
    String errorMsg;

    //CONSTRUCTOR
    public AnalysingSoil()
    {
        errorMsg = "\n! I'm sorry, Dave. I'm afraid I can't do that..."; 
    }

    /**
     * Generate error message for invalid command to Drive
     */
    @Override
    public void drive(Rover context, double newDist) 
    {
        String error;
        
        error = "\n[Cannot start Driving while performing soil analysis]\n";
        context.sendMessage(errorMsg + error);
    }

    @Override
    public void turn(Rover context) {
        // TODO Auto-generated method stub
        
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
