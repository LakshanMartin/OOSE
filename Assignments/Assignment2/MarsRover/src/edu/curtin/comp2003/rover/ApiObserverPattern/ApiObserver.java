package edu.curtin.comp2003.rover.ApiObserverPattern;

public interface ApiObserver 
{
    public void updateComm(String command);
    public void updateVisibility(double vis);
    public void updateDistance(double totalDist);
}
