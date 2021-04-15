package org.lucky.electricityNetwork.controller.FileIO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.lucky.electricityNetwork.model.CityNetworkCompositePatt.CityNode;
import org.lucky.electricityNetwork.model.CityNetworkCompositePatt.Node;

public class WriteFile 
{
    public WriteFile(){}
    
    public void writeFile(String filename, CityNode network) throws IOException
    {
        File dir, file;
        FileWriter writer;
        List<Node> nodeList;

        dir = new File("../resources/"); //Speicfy directory to save file to
        file = new File(dir, filename);
        writer = new FileWriter(file);

        nodeList = network.getNetworkList(); //Retrieve list of tree nodes

        writer.write(network.getParent() + "\n");

        for(int i = 0; i < nodeList.size(); i++)
        {
            writer.write(nodeList.get(i).getNodeValues() + "\n");
        }

        writer.close();
    }
}
