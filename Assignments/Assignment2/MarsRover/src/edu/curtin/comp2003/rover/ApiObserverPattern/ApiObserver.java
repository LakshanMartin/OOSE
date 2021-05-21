package edu.curtin.comp2003.rover.ApiObserverPattern;

public interface ApiObserver 
{
    public void updateComm(String command);
    public void updateSensors(double temp, double vis, double light);
    public void updateDistance(double totalDist);
    public void updateSoilAnalysis(byte[] soilResults);   
}
