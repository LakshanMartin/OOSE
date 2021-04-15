package org.lucky.electricityNetwork.model.CityNetworkCompositePatt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.lucky.electricityNetwork.controller.CityNodeController;

public class CityNode implements Node
{
    private String rootName;
    private List<Node> network;
    private CityNodeController controller;
    private double[] totalPower;

    public CityNode(String name)
    {
        rootName = name;
        network = new ArrayList<>();
        
        controller = new CityNodeController();
        totalPower = new double[8];
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
        return rootName;
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

    public List<Node> getNetworkList()
    {
        return network;
    }

    public double[] getTotalPower()
    {
        return totalPower;
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

    public void addNode(Node newNode)
    {
        network.add(newNode);
        totalPower = controller.updateTotalPower(newNode.getNodeValues(), totalPower);
    }    
}
