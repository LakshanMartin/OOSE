package org.lucky.electricityNetwork.controller;

import java.util.List;

import org.lucky.electricityNetwork.model.CityNetworkCompositePatt.*;

/**
 * This class contains any supporting methods used to manipulate data within
 * the CityNode object.
 */
public class CityNodeController 
{
    //Empty Constructor
    public CityNodeController(){}

    /**
     * Utilise the CityNode methods to retrieve and update the total power data
     * of the network.
     * @param nodeValues data used to update the total power
     * @param network
     */
    public void updateTotalPower(String nodeValues, CityNode network)
    {
        String[] line, powerValues;
        double[] totalPower;
        Double addPower;

        //Retrieve network's current Total Power values
        totalPower = network.getTotalPower();

        line = nodeValues.split(",");

        if(line.length > 2)
        {
            for(int i = 2; i < line.length; i++)
            {
                powerValues = line[i].split("=");                
                addPower = Double.parseDouble(powerValues[1]);

                switch(powerValues[0])
                {
                    case "dm":
                        totalPower[0] += addPower;

                        //Attempt to fix precision errors with double arithmetic
                        totalPower[0] = Math.round(totalPower[0] * 100.0) / 100.0;
                    break;

                    case "da":
                        totalPower[1] += addPower;
                        totalPower[1] = Math.round(totalPower[1] * 100.0) / 100.0;
                    break;

                    case "de":
                        totalPower[2] += addPower;
                        totalPower[2] = Math.round(totalPower[2] * 100.0) / 100.0;
                    break;

                    case "em":
                        totalPower[3] += addPower;
                        totalPower[3] = Math.round(totalPower[3] * 100.0) / 100.0;
                    break;

                    case "ea":
                        totalPower[4] += addPower;
                        totalPower[4] = Math.round(totalPower[4] * 100.0) / 100.0;
                    break;

                    case "ee":
                        totalPower[5] += addPower;
                        totalPower[5] = Math.round(totalPower[5] * 100.0) / 100.0;
                    break;

                    case "h":
                        totalPower[6] += addPower;
                        totalPower[6] = Math.round(totalPower[6] * 100.0) / 100.0;
                    break;

                    case "s":
                        totalPower[7] += addPower;
                        totalPower[7] = Math.round(totalPower[7] * 100.0) / 100.0;
                    break;
                }
            }
        }

        network.updateTotalPower(totalPower);
    }

    /**
     * Recursive wrapper method used to build the string representation of the 
     * Tree structure with required indentations.
     * @param root
     * @param network
     * @return
     */
    public String buildNetworkStr(String root, List<Node> network)
    {
        Node buildFrom;
        String currName, currParent;
        int depth;
        StringBuilder networkStr = new StringBuilder(root + "\n");

        //Loop through list to add node names to string
        for(int i = 0; i < network.size(); i++)
        {
            buildFrom = network.get(i); //Retrieve node
            currName = buildFrom.getName(); //Retrieve node name
            currParent = buildFrom.getParent(); //Retrieve node's parent name
            depth = buildFrom.getDepth(); //Retrieve node depth

            if(currParent.equals(root))
            {
                //Add indentation based on depth of node
                for(int j = 0; j < depth; j++)
                {
                    networkStr.append("    "); //Indentation to represent child/leaf node
                }

                networkStr.append(currName + "\n"); //Add node name to string
                networkStr.append(recursiveBuildStr(currName, network, i+1));
            }
        }

        return networkStr.toString(); //Completed string representing tree structure
    }

    /**
     * Recursive method that adds indentations based on node depth as it calls
     * itself through each node's parent. 
     * @param parent 
     * @param network
     * @param index
     * @return
     */
    private String recursiveBuildStr(String parent, List<Node> network, int index)
    {
        Node buildFrom;
        String currName, currParent;
        int depth;
        StringBuilder networkStr = new StringBuilder("");

        for(int i = index; i < network.size(); i++)
        {
            buildFrom = network.get(i); 
            currName = buildFrom.getName(); 
            currParent = buildFrom.getParent(); 
            depth = buildFrom.getDepth(); 

            if(currParent.equals(parent)) //Base case
            {
                //Add indentation based on depth of node
                for(int j = 1; j < depth; j++)
                {
                    networkStr.append("    ");  //Indentation to represent child/leaf node
                }

                networkStr.append(currName + "\n");
                networkStr.append(recursiveBuildStr(currName, network, i+1));
            }
        }

        return networkStr.toString();
    }
}
