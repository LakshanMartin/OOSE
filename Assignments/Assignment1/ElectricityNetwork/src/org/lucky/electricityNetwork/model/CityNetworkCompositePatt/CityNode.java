package org.lucky.electricityNetwork.model.CityNetworkCompositePatt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.lucky.electricityNetwork.controller.CityNodeController;

public class CityNode implements Node
{
    private String name;
    private List<Node> network;
    private CityNodeController controller;
    private String networkStr;
    private Double[] totalPower;

    public CityNode(String name)
    {
        this.name = name;
        network = new ArrayList<>();
        
        controller = new CityNodeController();
        totalPower = new Double[8];
        Arrays.fill(totalPower, 0.0);

        /*
        Initial totalPower array is setup as follows to represent power 
        consumption by category:
            totalPower[0] = 0.0     Weekday morning
            totalPower[1] = 0.0     Weekday afternoon
            totalPower[2] = 0.0     Weekday evening
            totalPower[3] = 0.0     Weekend morning
            totalPower[4] = 0.0     Weekend afternoon
            totalPower[5] = 0.0     Weekend evening
            totalPower[6] = 0.0     Heatwave
            totalPower[7] = 0.0     Special event
        */
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getParent()
    {
        return null;
    }

    @Override
    public int getDepth()
    {
        return 1;
    }

    @Override
    public String getNodeValues() 
    {
        return name;
    }  

    public void addNode(Node newNode)
    {
        network.add(newNode);
        totalPower = controller.updateTotalPower(newNode.getNodeValues(), totalPower);
    }

    public String getNetworkStr()
    {
        

        return networkStr;
    }

    public Double[] getTotalPower()
    {
        return totalPower;
    }
}
