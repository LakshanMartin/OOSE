package edu.curtin.comp2003.rover;

public class EarthComm 
{
    public EarthComm(){}

    public String pollCommand()
    {
        return "D 10.0";
    }

    public void sendMessage(String msg)
    {
        System.out.println(msg);
    }
}
