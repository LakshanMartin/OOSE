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
    private Set<ApiObserver> obs;
    private String command;
    private double temp, vis, light;

    public ApiData(EarthComm eComm, Sensors sens)
    {
        this.eComm = eComm;
        this.sens = sens;

        obs = new HashSet<>();
        command = "";
        temp = 0.0;
        vis = 0.0;
        light = 0.0;
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
            ob.updateEnvironment(temp, vis, light);
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

                //Retrieve updated enviroment values
                this.temp = sens.readTemperature();
                this.vis = sens.readVisibility();
                this.light = sens.readLightLevel();

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
