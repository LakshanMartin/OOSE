package edu.curtin.comp2003.rover;

public class EarthComm 
{
    private int commCount = -1;

    public EarthComm(){}

    public String pollCommand()
    {
        String[] commList = new String[]{
            "D 10.0", "D 5.0", "E", "S", "T 12.0", "D 20.5", "D 1.0"
        };

        commCount++;

        if(commCount >= commList.length)
        {
            return null;
        }
        else
        {
            return commList[commCount];
        }
    }

    public void sendMessage(String msg)
    {
        System.out.println(msg);
    }
}
