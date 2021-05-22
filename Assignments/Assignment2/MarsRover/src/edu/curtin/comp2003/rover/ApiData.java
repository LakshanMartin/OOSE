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
    private Set<ApiObserver> obs;
    private String command;
    private double vis, totalDist;

    public ApiData(EarthComm eComm, Sensors sens, EngineSystem engSys)
    {
        this.eComm = eComm;
        this.sens = sens;
        this.engSys = engSys;
        
        obs = new HashSet<>();
        command = "";
        vis = 0.0;
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
            ob.updateComm(command);
            ob.updateVisibility(vis);
            ob.updateDistance(totalDist);
        }

        //Sleep 5 seconds after notifying observers - REFERENCED CODE.
        try
        {
            Thread.sleep(1000 * 5);
        }
        catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }

    // SUPPORTING METHODS -----------------------------------------------------
    public void updateApi()
    {
        String inCommand;

        //while(true)
        //{
            try
            {
                //Retrieve & validate updated command values
                inCommand = eComm.pollCommand();
                validateCommand(inCommand);    
                this.command = inCommand;    

                //Retrieve updated visibility value
                this.vis = sens.readVisibility();

                //Retrieve updated distance values
                this.totalDist = engSys.getDistanceDriven();

                //Update Observers
                notifyObservers();
            }
            catch(CommandException e)
            {
                eComm.sendMessage(e.getMessage());
            }
        //}
    } 
    
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
