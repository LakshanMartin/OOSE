package edu.curtin.comp2003.rover;

import java.util.Base64;

import edu.curtin.comp2003.rover.ApiObserverPattern.*;
import edu.curtin.comp2003.rover.RoverStatePattern.Idle;
import edu.curtin.comp2003.rover.RoverStatePattern.RoverState;

public class Rover implements ApiObserver
{
    private EarthComm eComm;
    private Sensors sens;
    private EngineSystem engSys;
    private SoilAnalyser soil;
    private ApiData apiData;
    private String command;
    private double temp, vis, light, totalDist;
    private byte[] photo, soilResults;
    
    private RoverState state;

    public Rover(EarthComm eComm, Sensors sens, EngineSystem engSys, SoilAnalyser soil, ApiData apiData)
    {
        this. eComm = eComm;
        this.sens = sens;
        this.engSys = engSys;
        this.soil = soil;
        this.apiData = apiData;

        command = "";
        temp = 0.0;
        vis = 0.0;
        light = 0.0;
        photo = null;
        totalDist = 0.0;
        soilResults = null;

        apiData.addObserver(this); //Add Rover as an Observer
        state = new Idle(); //Initial State of Rover
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

    // ACESSORS ---------------------------------------------------------------
    public String getCommand()
    {
        return command;
    }

    public double getTotalDist()
    {
        return totalDist;
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
                drive();
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

    public void commandDrive(String[] command)
    {
        double toTravel;

        toTravel = Double.parseDouble(command[1]);

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
