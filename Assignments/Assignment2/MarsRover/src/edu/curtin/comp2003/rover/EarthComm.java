package edu.curtin.comp2003.rover;

public class EarthComm 
{
    private int commCount = -1;

    public EarthComm(){}

    public String pollCommand()
    {
        String[] commList = new String[]{
            //"D 10.0", "D 5.0", "P", "S", "E"
            //"E", "D 10.0", "E", "D 5.0"
            //"D 10.0", "S", "T 150.0", "D 5.0"
            "D 10.0", "T 10.0"
        };

        if(commCount == commList.length - 1)
        {
            return null;
        }
        else
        {
            commCount++;
        
            return commList[commCount];
        }
    }

    public void sendMessage(String msg)
    {
        System.out.println(msg);
    }
}
