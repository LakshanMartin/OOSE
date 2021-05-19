package edu.curtin.comp2003.rover;

import edu.curtin.comp2003.rover.CommandValidation.CommandException;

public class MainApp 
{
    public static void main(String[] args)
    {
        EarthComm earthComm;
        EngineSystem engineSystem;
        Sensors sensors;
        SoilAnalyser soilAnalyser;
        CommsHandler commsHandler;
        
        earthComm = new EarthComm();
        commsHandler = new CommsHandler();

        while(true)
        {
            try
            {
               commsHandler.processCommand(earthComm.pollCommand());
            }
            catch(CommandException e)
            {
                earthComm.sendMessage(e.getMessage());
            }
        }
    }    
}
