package edu.curtin.spaceprobe;

public class Battery implements Resource<T>
{
    private double charge = 100.0;

    public void recharge()
    {
        charge = 100.0;
    }

    @Override
    public void useUp(T amount)
    {
        charge -= amount;
    }

    @Override
    public T getRemaining()
    {
        return charge;
    }

    @Override
    public long getTime(long elapsedTime)
    {
        return (long)((double)elapsedTime / (100.0 - charge) * charge);
    }
}
