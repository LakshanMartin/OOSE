package org.lucky.electricityNetwork.view;

import java.util.List;

import org.lucky.electricityNetwork.controller.CityNodeController;
import org.lucky.electricityNetwork.model.CityNetworkCompositePatt.CityNode;
import org.lucky.electricityNetwork.model.CityNetworkCompositePatt.Node;

public class Display 
{
    //CLASS FIELDS
    private CityNode cityNetwork;
    private CityNodeController cont;

    //CONSTRUCTOR
    public Display(CityNode cityNetwork) 
    {
        this.cityNetwork = cityNetwork;
        cont = new CityNodeController();
    }
    
    public void displayNetwork()
    {
        String output, root;
        List<Node> network;
        
        root = cityNetwork.getParent();
        network = cityNetwork.getNetworkList();
        output = cont.buildNetworkStr(root, network);

        System.out.println("\nTREE STRUCTURE OF CITY NETWORK");
        System.out.println("------------------------------");
        System.out.println(output);
    }

    public void displayPowerConsumption()
    {
        double[] totalPower;

        totalPower = cityNetwork.getTotalPower();
        System.out.println("\nTOTAL POWER CONSUMPTION");
        System.out.println("-----------------------");       
        System.out.println("Weekday morning   : " + totalPower[0]);
        System.out.println("Weekday afternoon : " + totalPower[1]);
        System.out.println("Weekday evening   : " + totalPower[2]);
        System.out.println("Weekend morning   : " + totalPower[3]);
        System.out.println("Weekend afternoon : " + totalPower[4]);
        System.out.println("Weekend evening   : " + totalPower[5]);
        System.out.println("Heatwave          : " + totalPower[6]);
        System.out.println("Special event     : " + totalPower[7] + "\n");

    }

    public void displayData()
    {
        List<Node> network = cityNetwork.getNetworkList();

        System.out.println(cityNetwork.getParent());

        for(int i = 0; i < network.size(); i++)
        {
            System.out.println(network.get(i).getNodeValues());
        }        
    }
}
