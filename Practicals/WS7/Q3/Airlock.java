/**
 * Basic State implementation of the Airlock.
 */
public class Airlock
{
    //CLASS FIELDS
    private double pressure;
    private int state;
    private Sensor sens;
    private Pump pump;
    private Door innerDoor;
    private Door outerDoor;
    private static final int IDLE = 0;
    private static final int PRESSURISING = 1;
    private static final int DEPRESSURISING = 2;

    //CONSTRUCTOR
    public Airlock()
    {
        pressure = 0.0;
        state = IDLE;
        sens = new Sensor();
        pump = new Pump();
        innerDoor = new Door();
        outerDoor = new Door();
    }

    //METHODS
    /**
     * Pressurise Airlock if State and conditions are met.
     */
    public void pressurise()
    {
        switch(state)
        {
            case IDLE: //Original state
                if(!innerDoor.isOpen() && !outerDoor.isOpen()) //Guard condition
                {
                    if(pressure < 110.0) //Guard condition
                    {
                        pump.beginReturn(); //Action
                        state = PRESSURISING; //New state 
                    }
                    else
                    {
                        System.out.println("Already at maximum pressure");
                    }
                }
                else
                {
                    System.out.println("Close a door");
                }
            break;

            case PRESSURISING:
                System.out.println("Already pressurising!");
            break;

            case DEPRESSURISING: //Original state
                pump.stop(); //Assuming depressuring needs to be stopped before pressurising  
                pump.beginReturn(); //Action
                state = PRESSURISING; //New state
            break;
        }        
    }

    /**
     * Depressurise Airlock if State and conditions are met.
     */
    public void depressurise()
    {
        switch(state)
        {
            case IDLE: //Original state
                if(!innerDoor.isOpen() && !outerDoor.isOpen()) //Guard condition
                {
                    if(pressure > 0.0) //Guard condition
                    {
                        pump.beginExtraction(); //Action
                        state = DEPRESSURISING; //New state
                    }
                    else
                    {
                        System.out.println("Already at minimum pressure");
                    }
                }
                else
                {
                    System.out.println("Close a door");
                }
            break;

            case PRESSURISING: //Original state
                pump.stop(); //Action
                pump.beginExtraction(); //Action
                state = DEPRESSURISING; //New state
            break;

            case DEPRESSURISING:
                System.out.println("Already depressurising!");
            break;
        }
    }

    /**
     * Open inner door if State and conditions are met.
     */
    public void openInnerDoor()
    {
        switch(state)
        {
            case IDLE:
                if(!innerDoor.isOpen() && !outerDoor.isOpen())
                {
                    if(pressure > 90)
                    {
                        innerDoor.open();
                    }
                    else
                    {
                        System.out.println("Pressure too low");
                    }
                }
            break;

            case PRESSURISING:
                if(!innerDoor.isOpen() && !outerDoor.isOpen())  
                {
                    if(pressure > 90)
                    {
                        pump.stop();
                        innerDoor.open();
                        state = IDLE;
                    }
                    else
                    {
                        System.out.println("Pressure too low");
                    }
                }
            break;

            case DEPRESSURISING:
                if(!innerDoor.isOpen() && !outerDoor.isOpen())
                {
                    if(pressure > 90)
                    {
                        pump.stop();
                        innerDoor.open();
                        state = IDLE;
                    }
                    else
                    {
                        System.out.println("Pressure too low");
                    }
                }
            break;
        }
    }

    /**
     * Open the outer door if State and conditions are met.
     */
    public void openOuterDoor()
    {
        switch(state)
        {
            case IDLE:
                if(!innerDoor.isOpen() && !outerDoor.isOpen())
                {
                    if(pressure < 5)
                    {
                        outerDoor.open();
                    }
                    else
                    {
                        System.out.println("Pressure too high");
                    }
                }
            break;

            case PRESSURISING:
                if(!innerDoor.isOpen() && !outerDoor.isOpen())
                {
                    if(pressure < 5)
                    {
                        pump.stop();
                        outerDoor.open();
                        state = IDLE;
                    }
                    else
                    {
                        System.out.println("Pressure too high");
                    }
                }
            break;

            case DEPRESSURISING:
                if(!innerDoor.isOpen() && !outerDoor.isOpen())
                {
                    if(pressure < 5)
                    {
                        pump.stop();
                        outerDoor.open();
                        state = IDLE;
                    }
                    else
                    {
                        System.out.println("Pressure too high");
                    }
                }
            break;
        }
    }

    /**
     * This method is called from the Sensor class every second to update the 
     * current pressure of the Airlock.
     * @param pressure
     */
    public void updatePressure(double pressure)
    {
        this.pressure = pressure;
    }
}