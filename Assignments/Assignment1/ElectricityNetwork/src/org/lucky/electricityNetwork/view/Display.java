package org.lucky.electricityNetwork.view;

import java.util.List;

import org.lucky.electricityNetwork.controller.CityNodeController;
import org.lucky.electricityNetwork.model.CityNetworkCompositePatt.*;

/**
 * This class contains all the methods used to display data to the user through
 * the command line interface terminal.
 */
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
    
    /**
     * Output to the terminal the full City Network tree structure.
     */
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

    /**
     * Output to the terminal the total power consumption of the City Network.
     */
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
}
