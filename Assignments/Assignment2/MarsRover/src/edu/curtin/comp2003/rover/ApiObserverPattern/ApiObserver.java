package edu.curtin.comp2003.rover.ApiObserverPattern;

public interface ApiObserver 
{
    void updateComm(String command);
    void updateEnvironment(double temp, double vis, double light);
    void updateTotalDistance(double totalDist);
    void updateSoilResults(byte[] soilResults);
}
