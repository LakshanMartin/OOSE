package edu.curtin.comp2003.rover;

public class EarthComm 
{
    private int commCount = -1;

    public EarthComm(){}

    public String pollCommand()
    {
        String[] commList = new String[]{
            // TEST INVALID COMMANDS
            //"X", "D 0.0" 
            
            // TEST DRIVE COMMANDS --------------------------------------------
            // subsequent "D" commands
            //"D 1.5", "P", "E", "P", "D 5.2"

            // Test error states while Driving
            //"D 10.0", "S"

            // Test valid commands while Driving
            //"D 10.0", "T -120.0", "E", "P"

            // TEST SOIL ANALYSIS COMMANDS ------------------------------------
            // Test error states during Soil Analysis
            "S", "D 10.0", "S", "T -150.0", "S", "S"

            // Test valid commands during Soil Analysis
            //"S", "E", "S", "P"

            //"E"
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
