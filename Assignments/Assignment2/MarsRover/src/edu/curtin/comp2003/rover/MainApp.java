package edu.curtin.comp2003.rover;

import edu.curtin.comp2003.rover.RoverStatePattern.*;
import edu.curtin.comp2003.rover.VisibilityStatePattern.*;

public class MainApp 
{
    public static void main(String[] args)
    {
        EarthComm eComm;
        Sensors sens;
        EngineSystem engSys;
        SoilAnalyser soil;
        ApiData apiData;
        RoverState roverState;
        VisibilityState visState;
        Rover rover;
        
        eComm = new EarthComm();
        sens = new Sensors();
        engSys = new EngineSystem();
        soil = new SoilAnalyser();
        apiData = new ApiData(eComm, sens, engSys, soil); //Dependency injector
        
        roverState = new Stopped();
        visState = new NormalVisibility();
        rover = new Rover(eComm, sens, engSys, soil, apiData, roverState, visState); //Dependency injector

        apiData.updateApi();
    }    
}
