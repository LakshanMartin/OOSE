package edu.curtin.comp2003.rover;

import java.util.Base64;

import edu.curtin.comp2003.rover.ApiObserverPattern.ApiObserver;
import edu.curtin.comp2003.rover.RoverStatePattern.*;
import edu.curtin.comp2003.rover.VisibilityStatePattern.NormalVisibility;
import edu.curtin.comp2003.rover.VisibilityStatePattern.VisibilityState;

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
    private RoverState roverState;
    private VisibilityState visState;

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

        this.apiData.addObserver(this); //Add Rover as an Observer
        roverState = new Stopped(); //Initial State of Rover
        visState = new NormalVisibility(); //Initial Visibility State
    }

    // OBSERVER METHODS -------------------------------------------------------
    /**
     * Updating Commands for next set of instructions from Earth
     */
    @Override
    public void updateComm(String command) 
    {
        this.command = command;
        System.out.println("\nNew command: " + this.command);
        readCommand();
    }

    /**
     * Updating Environment Status report to check if Visibility is within set 
     * thresholds.  
     */
    @Override
    public void updateEnvironment(double temp, double vis, double light) 
    {
        this.temp = temp;
        this.vis = vis;
        this.light = light;

        if(vis < 4.0)
        {
            visState.belowFourKM(this, temp, vis, light);
        }
        else if(vis > 5.0)
        {
            visState.aboveFiveKM(this, temp, vis, light);
        }
        else
        {
            visState.normal(this, temp, vis, light);
        }
    }

    @Override
    public void updateTotalDistance(double totalDist)
    {
        this.totalDist = totalDist;

        if(totalDist >= travelTarget)
        {
            roverState.stopDriving(this);
        }
    }

    @Override
    public void updateSoilResults(byte[] soilResults)
    {
        String encodedSoilResults;

        this.soilResults = soilResults;

        if(this.soilResults != null)
        {
            encodedSoilResults = Base64.getEncoder().encodeToString(soilResults);
            this.soilResults = null; //reset back to null
            sendMessage("S " + encodedSoilResults + "\n");
            setRoverState(new Stopped()); //Update Rover State
        }
    }

    // STATE METHODS ----------------------------------------------------------
    public void setVisibilityState(VisibilityState newState)
    {
        visState = newState;
    }

    public void setRoverState(RoverState newState)
    {
        roverState = newState;
    }

    //MUTATORS ----------------------------------------------------------------
    public void setTravelTarget(double travelTarget)
    {
        this.travelTarget = travelTarget;
    }

    // ACESSORS ---------------------------------------------------------------
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
        double value;
        
        switch(commandID[0])
        {
            case "D":
                value = Double.parseDouble(commandID[1]);
                roverState.startDriving(this, value);
            break;

            case "T":
                value = Double.parseDouble(commandID[1]);
                roverState.turn(this, value);
            break;

            case "P":
                commandTakePhoto();
            break;

            case "E":
                commandReportEnvironment();
            break;

            case "S":
                roverState.analyseSoil(this);
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

    /**
     * Command the Rover to start driving
     */
    public void commandStartDriving()
    {
        engSys.startDriving();
    }

    /**
     * Command the Rover to stop driving and send return message
     */
    public void commandStopDriving()
    {
        engSys.stopDriving();
        sendMessage("D\n");
    }

    /**
     * Rover turns based on newAngle
     * @param newAngle
     */
    public void commandTurn(double newAngle)
    {
        engSys.turn(newAngle);
    }

    /**
     * Rover takes a photo. Photo is encoded in Base64. Encoded photo is sent
     * back to Earth through return message.
     */
    private void commandTakePhoto()
    {
        String encodedPhoto;

        photo = sens.takePhoto();
        encodedPhoto = Base64.getEncoder().encodeToString(photo);
        
        sendMessage("P " + encodedPhoto + "\n");
    }

    /**
     * Rover reads current environment status and returns report message back
     * to Earth.
     */
    private void commandReportEnvironment()
    {
        temp = sens.readTemperature();
        vis = sens.readVisibility();
        light = sens.readLightLevel();

        sendMessage("E " + temp + " " + vis + " " + light + "\n");
    }

    /**
     * Rover starts analysing soil.
     */
    public void commandSoilAnalysis()
    {
        soil.startAnalysis(); 
    }
}
