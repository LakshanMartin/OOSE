package org.lucky.electricityNetwork.controller;

import java.util.List;
import java.util.Map;

import org.lucky.electricityNetwork.model.CityNetworkCompositePatt.Node;

public class CityNodeController 
{
    //Empty Constructor
    public CityNodeController(){}

    public Double[] updateTotalPower(String nodeValues, Double[] totalPower)
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
                    case "dm":
                        totalPower[0] += Double.parseDouble(powerValues[1]);
                    break;

                    case "da":
                        totalPower[1] += Double.parseDouble(powerValues[1]);
                    break;

                    case "de":
                        totalPower[2] += Double.parseDouble(powerValues[1]);
                    break;

                    case "em":
                        totalPower[3] += Double.parseDouble(powerValues[1]);
                    break;

                    case "ea":
                        totalPower[4] += Double.parseDouble(powerValues[1]);
                    break;

                    case "ee":
                        totalPower[5] += Double.parseDouble(powerValues[1]);
                    break;

                    case "h":
                        totalPower[6] += Double.parseDouble(powerValues[1]);
                    break;

                    case "s":
                        totalPower[7] += Double.parseDouble(powerValues[1]);
                    break;
                }
            }
        }

        return totalPower;
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
                for(int j = 1; j < depth; j++)
                {
                    networkStr.append("\t");
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
                    networkStr.append("\t");
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
