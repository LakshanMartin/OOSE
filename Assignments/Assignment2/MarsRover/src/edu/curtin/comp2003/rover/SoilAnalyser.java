package edu.curtin.comp2003.rover;

public class SoilAnalyser 
{
    private boolean started = false;

    public SoilAnalyser(){}

    public void startAnalysis()
    {
        started = true;
    }   
    
    public byte[] pollAnalysis()
    {
        byte[] result = new byte[]{
            10, 12, 21, 123, 124
        };

        if(!started)
        {
            return null;
        }
        else
        {
            started = false;

            return result;
        }
    }
}
