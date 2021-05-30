package edu.curtin.comp2003.rover;

import java.util.HashSet;
import java.util.Set;

import edu.curtin.comp2003.rover.ApiObserverPattern.*;
import edu.curtin.comp2003.rover.CommandValidation.*;
import edu.curtin.comp2003.rover.CommandsStrategyPattern.*;

/**
 * This class is used for constant polling of the Rover API.
 */
public class ApiData implements Subject
{
    private EarthComm eComm;
    private Sensors sens;
    private EngineSystem engSys;
    private SoilAnalyser soil;
    private Set<ApiObserver> obs;
    private String command;
    private double temp, vis, light, totalDist;
    private byte[] soilResults;

    //CONSTRUCTOR
    public ApiData(EarthComm eComm, Sensors sens, EngineSystem engSys, SoilAnalyser soil)
    {
        this.eComm = eComm;
        this.sens = sens;
        this.engSys = engSys;
        this.soil = soil;

        obs = new HashSet<>();
        command = "";
        temp = 0.0;
        vis = 0.0;
        light = 0.0;
        totalDist = 0.0;
    }

    // OBSERVER METHODS -------------------------------------------------------
    @Override
    public void addObserver(ApiObserver ob) 
    {
        obs.add(ob);
    }

    @Override
    public void removeObserver(ApiObserver ob) 
    {
        obs.remove(ob);    
    }

    /**
     * Notify observers with updated values
     *  
     * REFERENCES: Stopak, J. "How to delay code execution in Java". 
     *             https://www.baeldung.com/java-delay-code-execution
     *             (accessed 21 May 2021).
     */
    @Override
    public void notifyObservers() 
    {
        for(ApiObserver ob : obs)
        {
            if(command != null) //Ignored if no command received
            {
                ob.updateComm(command);
            }

            ob.updateEnvironment(temp, vis, light);
            ob.updateTotalDistance(totalDist);

            if(soilResults != null) //Ignored if there are no results
            {
                ob.updateSoilResults(soilResults);
            }
        }

        //Sleep after notifying observers - REFERENCED CODE.
        try
        {
            Thread.sleep(500);
        }
        catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }

    // SUPPORTING METHODS -----------------------------------------------------
    /**
     * Method used to constantly poll the API for required information.
     */
    public void updateApi()
    {
        String inCommand;

        while(true)
        {
            try
            {
                //Retrieve & validate updated command values
                inCommand = eComm.pollCommand();

                //No need to validate command if it is NULL
                if(inCommand == null)
                {
                    this.command = null;
                }
                else
                {
                    validateCommand(inCommand); //Validation process   
                    this.command = inCommand; //Set new command value
                }

                //Retrieve updated environment values
                this.temp = sens.readTemperature();
                this.vis = sens.readVisibility();
                this.light = sens.readLightLevel();

                //Retrieve total distance travelled
                this.totalDist = engSys.getDistanceDriven();

                //Retrieve soil analysis results
                this.soilResults = soil.pollAnalysis();

                //Update Observers
                notifyObservers();
            }
            catch(CommandException e)
            {
                eComm.sendMessage(e.getMessage());
            }
        }
    } 
    
    /**
     * Identifies and validates command based on whether it has one part 
     * ("S", "P", "E") or two parts ("D 10.5", "T 150.0").
     * @param command
     * @throws CommandException
     */
    private void validateCommand(String command) throws CommandException
    {
        ReadCommand readComm;
        CommandValidation check;
        String[] toValidate;

        check = new CommandValidation();
        toValidate = command.split(" ");

        try
        {
            switch(toValidate.length)
            {
                case 1:
                    readComm = new OnePartCommand();
                    readComm.validateCommand(toValidate, check);
                break;

                case 2:
                    readComm = new TwoPartCommand();
                    readComm.validateCommand(toValidate, check);
                break;

                default:
                    throw new CommandException("Invalid command");
            }
        }
        catch(CommandException e)
        {
            throw e;
        }
    }
}
