package edu.curtin.comp2003.rover.ApiObserverPattern;

/**
 * This class is implemented by the Subject/Event-Source class, which is the 
 * ApiData class. 
 */
public interface Subject 
{
    public void addObserver(ApiObserver ob);
    public void removeObserver(ApiObserver ob);
    public void notifyObservers();
}
