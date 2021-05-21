package edu.curtin.comp2003.rover;

import edu.curtin.comp2003.rover.ApiObserverPattern.*;

public class Rover implements ApiObserver
{
    private String command;
    private double temp, vis, light, totalDist;
    private byte[] soilResults;
    private Subject apiData;

    public Rover(Subject apiData)
    {
        command = "";
        temp = 0.0;
        vis = 0.0;
        light = 0.0;
        totalDist = 0.0;
        soilResults = null;
        this.apiData = apiData;
        apiData.addObserver(this);
    }

    @Override
    public void updateComm(String command) 
    {
        this.command = command;
    }

    @Override
    public void updateSensors(double temp, double vis, double light) 
    {
        this.temp = temp;
        this.vis = vis;
        this.light = light;    
    }

    @Override
    public void updateDistance(double totalDist) 
    {
        this.totalDist = totalDist;    
    }

    @Override
    public void updateSoilAnalysis(byte[] soilResults) 
    {
        this.soilResults = soilResults;    
    }
}
