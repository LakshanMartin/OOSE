package org.lucky.electricityNetwork.model.DataGen;

import java.util.Random;

import org.lucky.electricityNetwork.model.CategoryDecoratorPatt.PowerCategory;
import org.lucky.electricityNetwork.model.CityNetworkCompositePatt.CityNode;
import org.lucky.electricityNetwork.model.CityNetworkCompositePatt.Node;
import org.lucky.electricityNetwork.model.CityNetworkCompositePatt.PowerUsage;
import org.lucky.electricityNetwork.model.CityNetworkCompositePatt.SubCityNode;

public class DataGeneration 
{
    private int treeDepth;
    private CityNode cityNetwork;
    private DataPool data;

    public DataGeneration()
    {
        this.treeDepth = 1;//genRandInt(1, 5); //Random tree depth [1-5]
        cityNetwork = new CityNode("Perth");
        data = new DataPool();
        buildTree();
    }  

    private void buildTree()
    {
        switch(treeDepth)
        {
            //Root node is also a leaf node
            case 1:
                buildDepthOne();               
            break;

            case 2:
                buildDepthTwo();
            break;

        }
    }

    //ACCESSORS
    public CityNode getCityNetwork()
    {
        return cityNetwork;
    }

    private int genRandInt(int min, int max)
    {
        Random rand = new Random();

        return rand.nextInt((max - min) + 1) + min;
    }

    private void buildDepthOne()
    {
        Node newNode;
        PowerCategory usage;

        //Generate power usage data
        data.genPowerCategories();
        usage = data.getPowerUsage();

        //Create leaf node
        newNode = new PowerUsage("Perth", "Perth", treeDepth, usage);

        //Add to tree
        cityNetwork.addNode(newNode); 
    }

    private void buildDepthTwo()
    {
        int numChild;

        numChild = genRandInt(2, 5); //Random number of child nodes [2-5]

        
    }
}
