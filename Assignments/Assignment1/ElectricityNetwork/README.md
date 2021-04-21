# City Electricity Network Application

Objected Oriented Software Engineering: Assignment 1

Semester 1, 2021

Author: Lakshan Martin

Submission: 21st April 2021



## Purpose

This application is a model simulation of a city's electricity usage. It either reads in or randomly generates data to be processed and represented as a Tree like structure of the network. 



## How to compile

Compilation of this application requires the following:

- Apache Ant (https://ant.apache.org/)
- Java

From the project root directory, build the application using the following command:

```bash
ant
```



## Usage

This application can be run with four different settings that determine the source and output of data. They are as follows:

- Generate random data to be displayed to the terminal. `-g -d`. 
- Generate random data to be written to a csv file. `-g -w outputdata.csv`.
- Read in data from a csv file to be displayed to the terminal. `-r inputdata.csv -d`.
- Read in data from a csv file to be written to another csv file. `-r inputdata.csv -w outputdata.csv`

**NOTE:** All files to be read into the application must be stored in the `/resources` directory found in the project root directory. Any output files will also be saved in the `/resources` directory.

Once any data to be read in has been appropriately stored, move to the `/dist` directory:

```bash
cd dist/
```

Enter the commands below to execute the application for each given setting.

### Generate random data to be displayed to the terminal

```bash
java -jar ElectricityNetwork.jar -g -d
```

### Generate random data to write to csv file

```bash
java -jar ElectricityNetwork.java -g -w outputdata.csv
```

### Read in data from a csv file to be displayed to the terminal

```bash
java -jar ElectricityNetwork.java -r inputdata.csv -d
```

### Read in data from a csv file to be written to another csv file

```bash
java -jar ElectricityNetwork.java -r inputdata.csv -w outputdata.csv
```



**NOTE:** Any invalid input within the csv file, to be read in, will display an error relevant to the invalid input to the terminal. 



## Additional Notes

1. Given that the assignment specification did not explicitly describe how 'Root-Leaf Nodes' should be represented, I have made an assumption to represent it in the following format for the input and output csv files:

   ```
   city 
   city, city, em=123,h=4242,... 
   ```

   The above format is only applicable to Root-Leaf Nodes. Any other input, where the node name and node parent name are the same, will result in an error output.

   Display of a Root-Leaf Node is represented as follows:

   ```bash
   city
   	city
   ```

2. To reiterate, all input csv files must be stored in the `/resources` directory prior to executing the application with the `-r` tag.

3. To reiterate, all output csv files will be saved to the `/resources` directory.

