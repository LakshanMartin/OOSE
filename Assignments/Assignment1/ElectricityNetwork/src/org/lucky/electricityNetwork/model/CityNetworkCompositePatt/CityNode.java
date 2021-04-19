package org.lucky.electricityNetwork.model.CityNetworkCompositePatt;

import java.util.*;

/**
 * This class represents the City Power Network tree structure. It serves as
 * the Composite class as a part of the Composite Design Pattern.
 */
public class CityNode implements Node
{
    //CLASS FIELDS
    private String rootName;
    private List<Node> network;
    private double[] totalPower;

    /**
     * CONSTRUCTOR
     * REFERENCE: GeeksforGeeks. Arrays.fill() in Java Examples.
     *            https://www.geeksforgeeks.org/arrays-fill-java-examples/
     *            Accessed 10/04/2021.
     * @param name
     */
    public CityNode(String name)
    {
        rootName = name;
        network = new ArrayList<>();
        totalPower = new double[8];
        Arrays.fill(totalPower, 0.0); //See reference note
        /*
        Initially totalPower array is setup as follows to represent power 
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

    //MUTATORS
    /**
     * Updates the total power array
     * @param totalPower
     */
    public void updateTotalPower(double[] totalPower) 
    {
        this.totalPower = totalPower;
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

    // SUPPORT METHODS --------------------------------------------------------
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

    /**
     * Used as part of Node class implementation.
     */
    @Override
    public boolean isLeaf()
    {
        return false;
    }

    /**
     * Identifies if parent node exists in the list.
     * @param input
     * @return
     */
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
                //Retrieve node to check if it's name matches input
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

    /**
     * Add's a new node to the network list
     * @param newNode
     */
    public void addNode(Node newNode)
    {
        network.add(newNode);
    }    
}
