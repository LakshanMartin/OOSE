package org.lucky.electricityNetwork.model;

import java.util.ArrayList;
import java.util.List;

public class CityNetwork implements PowerNode
{
    private String name;
    private String parent;
    private List<PowerNode> network;

    public CityNetwork(String name)
    {
        this.name = name;
        parent = null;
        network = new ArrayList<>();
    }

    @Override
    public String getName() 
    {
        return null;
    }

    @Override
    public String getParent() 
    {
        return null;
    }

    @Override
    public String getPower() 
    {
        return null;
    }    

    public void addNode(PowerNode newNode)
    {
        network.add(newNode);
    }
}
