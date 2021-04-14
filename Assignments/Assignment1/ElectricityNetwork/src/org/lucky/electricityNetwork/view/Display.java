package org.lucky.electricityNetwork.view;

import java.util.Map;

import org.lucky.electricityNetwork.model.CityNetworkCompositePatt.CityNode;

public class Display 
{
    //CLASS FIELDS
    private CityNode cityNetwork;
    //private Map<String,Double> totalPower;
    

    //CONSTRUCTOR
    public Display(CityNode cityNetwork) 
    {
        this.cityNetwork = cityNetwork;
        //totalPower = cityNetwork.getTotalPower();
    }
    
    public void displayNetwork()
    {
        System.out.println("\nTREE STRUCTURE OF CITY NETWORK");
        System.out.println("------------------------------");
        System.out.println(cityNetwork.getNetworkStr());
    }

    public void displayPowerConsumption()
    {
        /*System.out.println("\nTOTAL POWER CONSUMPTION:");       
        System.out.println("Weekday morning   : " + totalPower.get("Weekday morning"));
        System.out.println("Weekday afternoon : " + totalPower.get("Weekday afternoon"));
        System.out.println("Weekday evening   : " + totalPower.get("Weekday evening"));
        System.out.println("Weekend morning   : " + totalPower.get("Weekend morning"));
        System.out.println("Weekend afternoon : " + totalPower.get("Weekend afternoon"));
        System.out.println("Weekend evening   : " + totalPower.get("Weekend evening"));
        System.out.println("Heatwave          : " + totalPower.get("Heatwave"));
        System.out.println("Special event     : " + totalPower.get("Special event"));*/

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
