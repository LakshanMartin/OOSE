package org.lucky.electricityNetwork.controller;

import java.util.List;

import org.lucky.electricityNetwork.model.CityNetworkCompositePatt.Node;

public class CityNodeController 
{
    //Empty Constructor
    public CityNodeController(){}

    public double[] updateTotalPower(String nodeValues, double[] totalPower)
    {
        String[] line, powerValues;
        Double addPower;

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
                        //currCons = totalPower.get("Weekday morning");
                        //totalPower.replace("Weekday morning", currCons + addCons);
                        System.out.println(powerValues[0] + ": " + powerValues[1]);
                        totalPower[0] += addPower;
                        
                        //Attempt to fix precision errors with double arithmetic
                        totalPower[0] = Math.round(totalPower[0] * 100.0) / 100.0;
                    break;

                    case "da":
                        //currCons = totalPower.get("Weekday afternoon");
                        //totalPower.replace("Weekday afternoon", currCons + addCons);
                        System.out.println(powerValues[0] + ": " + powerValues[1]);
                        totalPower[1] += addPower;
                        totalPower[1] = Math.round(totalPower[1] * 100.0) / 100.0;
                    break;

                    case "de":
                        //currCons = totalPower.get("Weekday evening");
                        //totalPower.replace("Weekday evening", currCons + addCons);   
                        System.out.println(powerValues[0] + ": " + powerValues[1]);
                        totalPower[2] += addPower;
                        totalPower[2] = Math.round(totalPower[2] * 100.0) / 100.0;
                    break;

                    case "em":
                        //currCons = totalPower.get("Weekend morning");
                        //totalPower.replace("Weekend morning", currCons + addCons);    
                        System.out.println(powerValues[0] + ": " + powerValues[1]);
                        totalPower[3] += addPower;
                        totalPower[3] = Math.round(totalPower[3] * 100.0) / 100.0;
                    break;

                    case "ea":
                        //currCons = totalPower.get("Weekend afternoon");
                        //totalPower.replace("Weekend afternoon", currCons + addCons);
                        System.out.println(powerValues[0] + ": " + powerValues[1]);
                        totalPower[4] += addPower;
                        totalPower[4] = Math.round(totalPower[4] * 100.0) / 100.0;
                    break;

                    case "ee":
                        //currCons = totalPower.get("Weekend evening");
                        //totalPower.replace("Weekend evening", currCons + addCons);    
                        System.out.println(powerValues[0] + ": " + powerValues[1]);
                        totalPower[5] += addPower;
                        totalPower[5] = Math.round(totalPower[5] * 100.0) / 100.0;
                    break;

                    case "h":
                        //currCons = totalPower.get("Heatwave");
                        //totalPower.replace("Heatwave", currCons + addCons);    
                        System.out.println(powerValues[0] + ": " + powerValues[1]);
                        totalPower[6] += addPower;
                        totalPower[6] = Math.round(totalPower[6] * 100.0) / 100.0;
                    break;

                    case "s":
                        //currCons = totalPower.get("Special event");
                        //totalPower.replace("Special event", currCons + addCons);    
                        System.out.println(powerValues[0] + ": " + powerValues[1]);
                        totalPower[7] += addPower;
                        totalPower[7] = Math.round(totalPower[7] * 100.0) / 100.0;
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
