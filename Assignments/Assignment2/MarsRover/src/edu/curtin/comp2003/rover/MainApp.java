package edu.curtin.comp2003.rover;

public class MainApp 
{
    public static void main(String[] args)
    {
        EarthComm eComm;
        Sensors sens;
        EngineSystem engSys;
        SoilAnalyser soil;
        ApiData apiData;
        Rover rover;
        
        eComm = new EarthComm();
        sens = new Sensors();
        engSys = new EngineSystem();
        soil = new SoilAnalyser();
        
        apiData = new ApiData(eComm, sens, engSys);
        rover = new Rover(eComm, sens, engSys, soil, apiData);

        apiData.updateApi();
    }    
}
