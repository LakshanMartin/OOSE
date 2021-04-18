package org.lucky.electricityNetwork.controller;

import java.util.List;

import org.lucky.electricityNetwork.model.CityNetworkCompositePatt.*;

public class CityNodeController 
{
    //Empty Constructor
    public CityNodeController(){}

    //public double[] updateTotalPower(String nodeValues, double[] totalPower)
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

        //return totalPower;
        network.updateTotalPower(totalPower);
    }

    public String buildNetworkStr(String root, List<Node> network)
    {
        Node buildFrom;
        String currName, currParent;
        int depth;
        StringBuilder networkStr = new StringBuilder(root + "\n");

        for(int i = 0; i < network.size(); i++)
        {
            buildFrom = network.get(i); 
            currName = buildFrom.getName(); 
            currParent = buildFrom.getParent(); 
            depth = buildFrom.getDepth(); 

            if(currParent.equals(root))
            {
                //Add tabs based on depth of node
                for(int j = 0; j < depth; j++)
                {
                    networkStr.append("    "); //Indentation to represent child/leaf node
                }

                networkStr.append(currName + "\n");
                networkStr.append(recursiveBuildStr(currName, network, i+1));
            }
        }

        return networkStr.toString();
    }

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

            if(currParent.equals(parent))
            {
                //Add tabs based on depth of node
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

    /*
        Outer Node tracker(city, network):
        node[0] = northside, city, 2 - MATCH
        node[1] = southside, city, 2 - MATCH

        Inner Node tracker 1(northside, network, 1):
        node[1] = southside, city, 2 - NO MATCH
        node[2] = building1, northside, 3 - MATCH

        Inner Node tracker 2(building1, network, 3):
        node[3] = building2, southside, 3 - NO MATCH
        node[4] = building3, southside, 3 - NO MATCH

        -------------------------------------
        Inner Node tracker 1(southside, network, 2):
        node[2] = building1, northside, 3 - NO MATCH
        node[3] = building2, southside, 3 - MATCH
        node[4] = building3, southside, 3 - MATCH

        Inner Node tracker 2(building2, network, 4):
        node[4] = building3, southside, 3 - NO MATCH 

        Output:
        city
            northside
                building1
            southside
                building2
                building3        
    */
}
