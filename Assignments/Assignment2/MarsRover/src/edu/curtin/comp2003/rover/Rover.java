package edu.curtin.comp2003.rover;

import java.util.Base64;

import edu.curtin.comp2003.rover.ApiObserverPattern.ApiObserver;
import edu.curtin.comp2003.rover.RoverStatePattern.*;
import edu.curtin.comp2003.rover.VisibilityStatePattern.*;

public class Rover implements ApiObserver
{
    private EarthComm eComm;
    private Sensors sens;
    private EngineSystem engSys;
    private SoilAnalyser soil;
    private ApiData apiData;
    private String command;
    private double temp, vis, light, totalDist, travelTarget;
    private byte[] photo;
    private RoverState roverState;
    private VisibilityState visState;

    public Rover(EarthComm eComm, Sensors sens, EngineSystem engSys, 
    SoilAnalyser soil, ApiData apiData, Stopped stopped, NormalVisibility normal)
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

        this.apiData.addObserver(this); //Add Rover as an Observer
        roverState = stopped; //Initial State of Rover
        visState = normal; //Initial Visibility State
    }

    // OBSERVER METHODS -------------------------------------------------------
    /**
     * Update Commands for next set of instructions from Earth
     */
    @Override
    public void updateComm(String command) 
    {
        this.command = command;
        readCommand();
    }

    /**
     * Update Environment Status report to check if Visibility is within set 
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

    /**
     * Update Total Distance travelled 
     */
    @Override
    public void updateTotalDistance(double totalDist)
    {
        this.totalDist = totalDist;

        checkTravelTarget();
    }

    /**
     * Update soil analysis results
     */
    @Override
    public void updateSoilResults(byte[] soilResults)
    {
        String encodedSoilResults;
        
        encodedSoilResults = Base64.getEncoder().encodeToString(soilResults);
        sendMessage("S " + encodedSoilResults + "\n"); //Send encoded results
        setRoverState(new Stopped()); //Update Rover State
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
     * Identify which command the Rover should action.
     */
    private void readCommand()
    {
        String[] commandID = command.split(" ");
        double value;
        
        switch(commandID[0])
        {
            case "D":
                value = Double.parseDouble(commandID[1]); //Convert to double
                roverState.startDriving(this, value);
            break;

            case "T":
                value = Double.parseDouble(commandID[1]); //Convert to double
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
     * Check whether the Rover has travelled the requested distance
     */
    private void checkTravelTarget()
    {
        if(totalDist >= travelTarget)
        {
            roverState.stopDriving(this);
        }
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
        
        sendMessage("P " + encodedPhoto + "\n"); //Send encoded photo data
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
    
        reassessVisibilityState(); 
    }

    /**
     * Set visibility state based on latest visibility status.
     */
    private void reassessVisibilityState()
    {
        if(vis < 4.0)
        {
            setVisibilityState(new BelowFourVisibility());
        }
        else if(vis > 5.0)
        {
            setVisibilityState(new AboveFiveVisibility());
        }
        else
        {
            setVisibilityState(new NormalVisibility());
        }
    }

    /**
     * Rover starts analysing soil.
     */
    public void commandSoilAnalysis()
    {
        soil.startAnalysis(); 
    }
}
