package edu.curtin.comp2003.rover;

import java.util.Base64;

import edu.curtin.comp2003.rover.ApiObserverPattern.*;
import edu.curtin.comp2003.rover.RoverStatePattern.Idle;
import edu.curtin.comp2003.rover.RoverStatePattern.RoverState;

public class Rover implements ApiObserver
{
    private String command;
    private double temp, vis, light, totalDist;
    private byte[] photo, soilResults;
    private ApiData apiData;
    private RoverState state;

    public Rover(ApiData apiData)
    {
        command = "";
        temp = 0.0;
        vis = 0.0;
        light = 0.0;
        photo = null;
        totalDist = 0.0;
        soilResults = null;
        this.apiData = apiData;
        apiData.addObserver(this);
        state = new Idle();
    }

    // OBSERVER METHODS -------------------------------------------------------
    @Override
    public void updateComm(String command) 
    {
        this.command = command;
        System.out.println("Current command: " + this.command);
        readCommand();
    }

    @Override
    public void updateVisibility(double vis) 
    {
        this.vis = vis;
    }

    @Override
    public void updateDistance(double totalDist) 
    {
        this.totalDist = totalDist;    
    }

    // STATE METHODS ----------------------------------------------------------
    public void setState(RoverState newState)
    {
        state = newState;
    }
    
    public void drive() 
    {
        state.drive(this);
    }

    public void turn() 
    {
        state.turn(this);
    }

    public void takePhoto() 
    {
        state.takePhoto(this);
    }

    public void reportEnvironment() 
    {
        state.reportEnvironment(this);
    }

    public void analyseSoil() 
    {
        state.analyseSoil(this);        
    }

    // SUPPORTING METHODS -----------------------------------------------------
    /**
     * Identify which command for the the Rover to action.
     */
    private void readCommand()
    {
        String[] commandID = command.split(" ");

        switch(commandID[0])
        {
            case "D":
                commandDrive();
            break;

            case "T":

            break;

            case "P":

            break;

            case "E":

            break;

            case "S":

            break;
        }
    }

    public void commandDrive()
    {
        String[] strTravel;
        double toTravel;

        strTravel = command.split(" ");
        toTravel = Double.parseDouble(strTravel[1]);

        while(totalDist - toTravel != 0.0)
        {
            //
        }
    }

    /**
     * Return Base64 encoded image data of photo taken
     * @return
     */
    public String getPhoto()
    {
        return Base64.getEncoder().encodeToString(photo);
    }

    
}
