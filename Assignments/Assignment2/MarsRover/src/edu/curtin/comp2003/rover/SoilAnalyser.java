package edu.curtin.comp2003.rover;

import java.util.ArrayList;
import java.util.List;

public class SoilAnalyser 
{
    private int soilCount = -1;

    public SoilAnalyser(){}

    public void startAnalysis()
    {

    }   
    
    public byte[] pollAnalysis()
    {
        byte[] result = new byte[]{
            10, 12, 21
        };

        List<byte[]> list = new ArrayList<>();
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(result);
        list.add(null);

        if(soilCount == list.size() - 1)
        {
            return list.get(list.size() - 1);
        }
        else
        {
            soilCount++;
            return list.get(soilCount);
        }
    }
}
