package edu.curtin.spaceprobe;
/**
 * <T> represents a FuelAmount type
 */
public class FuelTank implements Resource<T>
{
    private double oxygen = 100.0;
    private double hydrogen = 100.0;

    @Override
    public void useUp(T amount)
    {
        FuelAmount fuelUsage = amount;
        this.oxygen -= fuelUsage.getOxygen();
        this.hydrogen -= fuelUsage.getHydrogen();
    }

    @Override
    public T getRemaining()
    {
        return new FuelAmount(hydrogen, oxygen);
    }

    @Override
    public long getTime(long elapsedTime)
    {
        return (long)Math.min(
            (double)elapsedTime / (100.0 - oxygen) * oxygen,
            (double)elapsedTime / (100.0 - hydrogen) * hydrogen);
    }
}
