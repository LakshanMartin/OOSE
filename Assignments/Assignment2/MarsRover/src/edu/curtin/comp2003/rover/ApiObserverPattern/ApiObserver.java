package edu.curtin.comp2003.rover.ApiObserverPattern;

public interface ApiObserver 
{
    public void updateComm(String command);
    public void updateEnvironment(double temp, double vis, double light);
}