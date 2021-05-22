package edu.curtin.comp2003.rover;

import java.util.Base64;

import edu.curtin.comp2003.rover.ApiObserverPattern.*;
import edu.curtin.comp2003.rover.CommandValidation.CommandException;
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
    private double temp, vis, light, totalDist, travelTarget;
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

    // STATE METHODS ----------------------------------------------------------
    public void setState(RoverState newState)
    {
        state = newState;
    }
    
    public void drive(double newDist) 
    {
        state.drive(this, newDist);  
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

    //MUTATORS
    public void setTravelTarget(double travelTarget)
    {
        this.travelTarget = travelTarget;
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

    public double getTravelTarget()
    {
        return travelTarget;
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
                drive(Double.parseDouble(commandID[1]));
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

    /**
     * Send return message to Earth
     * @param msg
     */
    public void sendMessage(String msg)
    {
        eComm.sendMessage(msg);
    }

    public void commandDrive()
    {
        engSys.startDriving();

        while(totalDist < travelTarget)
        {
            totalDist = engSys.getDistanceDriven();
        }

        engSys.stopDriving();
        setState(new Idle());
        sendMessage("D");
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
