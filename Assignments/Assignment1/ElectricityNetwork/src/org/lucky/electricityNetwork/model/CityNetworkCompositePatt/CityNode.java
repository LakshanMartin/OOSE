package org.lucky.electricityNetwork.model.CityNetworkCompositePatt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CityNode implements Node
{
    private String name;
    private List<Node> network;
    private Double[] totalPower;

    public CityNode(String name)
    {
        this.name = name;
        network = new ArrayList<>();
        totalPower = new Double[8];
        Arrays.fill(totalPower, 0.0);
    }

    @Override
    public String getNodeValues() 
    {
        return name;
    }

    public String getPower() 
    {
        return null;
    }    

    public void addNode(Node newNode)
    {
        network.add(newNode);
        updateTotalPower(newNode.getNodeValues());
    }

    private void updateTotalPower(String nodeValues)
    {
        String[] line, powerValues;

        line = nodeValues.split(",");

        if(line.length > 2)
        {
            for(int i = 2; i < line.length; i++)
            {
                powerValues = line[i].split("=");

                switch(powerValues[0])
                {
                    //List cases of categories
                }
            }
        }
    }

    public String displayNetwork()
    {
        StringBuilder network;

        network = new StringBuilder();

        //Loop through network list to build string

        return network.toString();
    }
}
