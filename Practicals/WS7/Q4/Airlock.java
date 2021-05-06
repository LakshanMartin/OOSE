/**
 * State Pattern - Context
 */
public class Airlock
{
    //CLASS FIELDS
    private double pressure;
    private AirlockState state;
    private Sensor sens;
    private Pump pump;
    private Door innerDoor;
    private Door outerDoor;

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

    //ACCESSORS
    public Sensor getSensor()
    {
        return sens;
    }

    public Pump getPump()
    {
        return pump;
    }

    public Door getInnerDoor()
    {
        return innerDoor;
    }

    public Door getOuterDoor()
    {
        return outerDoor;
    }

    public double getPressure()
    {
        return pressure;
    }

    //MUTATORS
    public void setState(AirlockState newState)
    {
        state = newState;
    }

    //METHODS
    /**
     * Pressurise Airlock based on State.
     */
    public void pressurise()
    {
        state.pressurise(this);       
    }

    /**
     * Depressurise Airlock based on State.
     */
    public void depressurise()
    {
        state.depressurise(this);
    }

    /**
     * Open inner door based on State.
     */
    public void openInnerDoor()
    {
        state.openInnerDoor(this);
    }

    /**
     * Open outer door based on State.
     */
    public void openOuterDoor()
    {
        state.openOuterDoor(this);
    }

    /**
     * This method is called from the Sensor class every second to update the 
     * current pressure of the Airlock
     * @param pressure
     */
    public void updatePressure(double pressure)
    {
        this.pressure = pressure;
    }
}