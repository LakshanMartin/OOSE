# Control System for a Mars Rover

Object Oriented Software Engineering: Assignment 2

Semester 1, 2021

Author: Lakshan Martin

Submission: 1st June 2021



## Purpose

This application simulates the software system for controlling a Mars Rover. Once the application is run, it will wait for commands directing it's action. These commands are sent through an external API.



## How to compile

Compilation of this application requires the following:

- Apache Ant (https://ant.apache.org/)
- Java

From the project root directory, build the application using the following command:

```bash
ant
```



## Usage

This application requires the inclusion of four externally produced classes:

- EarthComm
- Sensors
- EngineSystem
- SoilAnalyser

These classes must be saved in the following directory in order to run the application: `/MarsRover/src/edu/curtin/comp2003/rover/`

The application can be executed with either of the commands listed below from the root directory -  `/MarsRover/`

### Ant

The application can be executed through Ant with the following command:

```bash
ant run
```

### Manual execution

Alternatively, the application can be executed from the `/dist` directory with the following commands:

```bash
cd dist/
java -jar MarsRover.jar
```



## Additional Notes

1. I have included examples of the four external classes used for testing purposes. These may be deleted and replaced with any version of such classes, so long as class names and method declarations remain the same. 

2. A UML diagram representation of the application can be found in the root directory

   `/MarsRover/UML_Diagram.pdf`

3. A document containing my responses to the marking criteria can be found in the root directors

   `/MarsRover/criteria.md`

4. PDF versions of the following documents have been created in the event that reading Markdown documents are not suitable:

   - README.md
   - criteria.md