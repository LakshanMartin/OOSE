package edu.curtin.comp2003.rover.ApiObserverPattern;

public interface Subject 
{
    public void addObserver(ApiObserver ob);
    public void removeObserver(ApiObserver ob);
    public void notifyObservers();
}
