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
        visState = new NormalVisibility(); //Initial Visibility State of Rover
    }

    // OBSERVER METHODS -------------------------------------------------------
    /**
     * Updating Commands for next set of instructions from Earth
     */
    @Override
    public void updateComm(String command) 
    {
        this.command = command;
        System.out.println("New command: " + this.command);
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

    public void setTotalDist(double updated)
    {
        this.totalDist = updated;
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
        double value;
        
        switch(commandID[0])
        {
            case "D":
                //drive(Double.parseDouble(commandID[1]));
                value = Double.parseDouble(commandID[1]);
                roverState.drive(this, value);
            break;

            case "T":
                //turn(Double.parseDouble(commandID[1]));
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
                //analyseSoil();
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
     * Rover keeps driving until totalDist >= travelTarget then:
     *  - Stops driving
     *  - Rover State set to Idle
     *  - Sends return message to Earth
     */
    public void commandDrive()
    {
        engSys.startDriving(); //Action
        setRoverState(new Driving()); //Update State

        while(totalDist < travelTarget)
        {
            totalDist = engSys.getDistanceDriven();

            //Sleep for 3 seconds to simulate time taken to travel.
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }
        
        engSys.stopDriving();
        setRoverState(new Stopped()); //Update State
        sendMessage("D\n");
    }

    /**
     * Rover turns based on newAngle
     * @param newAngle
     */
    public void commandTurn(double newAngle)
    {
        engSys.turn(newAngle);
        sendMessage("T\n");
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
     * Rover starts analysing soil and returns report message back to Earth
     *  
     * REFERENCES: Stopak, J. "How to delay code execution in Java". 
     *             https://www.baeldung.com/java-delay-code-execution
     *             (accessed 21 May 2021).
     */
    public void commandSoilAnalysis()
    {
        String encodedSoilResults;

        soil.startAnalysis(); //Action
        setRoverState(new AnalysingSoil()); //Update State

        while(soilResults == null)
        {
            soilResults = soil.pollAnalysis(); //Poll until results returned

            //Sleep 2 seconds after polling - REFERENCED CODE.
            try
            {
                Thread.sleep(1000 * 2);
            }
            catch(InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }

        setRoverState(new Stopped()); //Update State 
        encodedSoilResults = Base64.getEncoder().encodeToString(soilResults);
        soilResults = null; //Reset back to null for next analysis

        sendMessage("S " + encodedSoilResults + "\n");
    }
}
