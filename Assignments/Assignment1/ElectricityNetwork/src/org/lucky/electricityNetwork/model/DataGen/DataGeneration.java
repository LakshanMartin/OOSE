package org.lucky.electricityNetwork.model.DataGen;

import java.util.List;
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
        this.treeDepth = 2;//genRandInt(1, 5); //Random tree depth [1-5]
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

    //SUPPORTING METHODS
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
        List<String> dTwoNames;
        List<String> dThreeNames;
        String name;
        Node newNode;
        String[] parents;
        PowerCategory usage;

        numChild = genRandInt(2, 5); //Random number of child nodes [2-5]
        dTwoNames = data.getDepthTwo(); //Get list of node names        
        dThreeNames = data.getDepthThree(); //Get list of leaf node names
        parents = new String[numChild]; //Track parent names to be used for leaf nodes

        //Loop to add nodes to tree
        for(int i = 0; i < numChild; i++)
        {
            name = dTwoNames.get(i); //Retrieve name           
            parents[i] = name; //Add to list of parents

            //Create non-root node
            newNode = new SubCityNode(name, "Perth", 1);            
        
            //Add node to tree
            cityNetwork.addNode(newNode);
        }

        //Loop to add leaf nodes to tree
        for(int i = 0; i < numChild; i++)
        {
            //Get name
            name = dThreeNames.get(i);

            //Generate power usage data
            data.genPowerCategories();
            usage = data.getPowerUsage();

            //Create leaf node
            newNode = new PowerUsage(name, parents[i], 3, usage);

            //Add to tree
            cityNetwork.addNode(newNode);
        }
    }
}
