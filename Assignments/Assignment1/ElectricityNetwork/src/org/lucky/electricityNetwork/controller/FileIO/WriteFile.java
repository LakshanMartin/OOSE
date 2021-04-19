package org.lucky.electricityNetwork.controller.FileIO;

import java.io.*;
import java.util.List;

import org.lucky.electricityNetwork.model.CityNetworkCompositePatt.*;

/**
 * This class is used to write Tree structure data to a csv file.
 */
public class WriteFile 
{
    //EMPTY CONSTRUCTOR
    public WriteFile(){}
    
    /**
     * Write data from @param network into a csv file. 
     * @param filename
     * @param network
     * @throws IOException
     */
    public void writeFile(String filename, CityNode network) throws IOException
    {
        File dir, file;
        FileWriter writer;
        List<Node> nodeList;

        dir = new File("../resources/"); //Specify directory to save file to
        file = new File(dir, filename);
        writer = new FileWriter(file);

        nodeList = network.getNetworkList(); //Retrieve list of tree nodes

        writer.write(network.getParent()); //First write root node name

        for(int i = 0; i < nodeList.size(); i++) //Loop through nodes
        {
            writer.write("\n" + nodeList.get(i).getNodeValues());
        }

        writer.close();
    }
}
