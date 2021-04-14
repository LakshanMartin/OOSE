package org.lucky.electricityNetwork.model.CityNetworkCompositePatt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lucky.electricityNetwork.controller.CityNodeController;

public class CityNode implements Node
{
    private String rootName;
    private List<Node> network;
    //private Map<String,Node> network;
    private CityNodeController controller;
    private String networkStr;
    private Double[] totalPower;

    public CityNode(String name)
    {
        rootName = name;
        network = new ArrayList<>();
        //network = new HashMap<>();
        
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

    //ACCESSORS
    @Override
    public String getName()
    {
        return rootName;
    }

    @Override
    public String getParent()
    {
        return null;
    }

    @Override
    public int getDepth()
    {
        return 0;
    }

    @Override
    public String getNodeValues() 
    {
        return rootName;
    }  

    /**
     * Find and return specific node by name
     * REFERENCE: Cooper, David. Lecture 4: Object Relationships. Slide 43.
     */
    @Override
    public Node findNode(String name)
    {
        for(Node node : network)
        {
            Node found = node.findNode(name);

            if(found != null)
            {
                return found;
            }
        }

        return null;
    }

    public List<Node> getNetworkList()
    {
        return network;
    }

    public boolean checkParentNodes(String input)
    {
        Node toCheck;
        boolean exists = false;

        if(input.equals(rootName))
        {
            exists = true;
        }
        else
        {
            for(int i = 0; i < network.size(); i++)
            {
                toCheck = network.get(i);
                
                if(toCheck.getName().equals(input))
                {
                    exists = true;
                    break;
                }
            }
        }        

        return exists;
    }

    //MUTATORS
    public void addNode(Node newNode)
    {
        network.add(newNode);
        totalPower = controller.updateTotalPower(newNode.getNodeValues(), totalPower);
    }

    //Remove from Model 
    public String getNetworkStr()
    {
        networkStr = controller.buildNetworkStr(rootName, network);

        return networkStr;
    }

    //Remove from Model
    public Double[] getTotalPower()
    {
        return totalPower;
    }
}
