package edu.curtin.spaceprobe;

import java.util.*;

public class SensorManager implements Resource<T>
{
    private Set<Sensor> workingSensors;
    private int nSensors;

    public SensorManager(Set<Sensor> sensors)
    {
        workingSensors = new HashSet<>();
        workingSensors.addAll(sensors);
        nSensors = sensors.size();
    }

    @Override
    public void useUp(T amount)
    {
        for(Sensor sensor : amount)
        {
            workingSensors.remove(sensor);
        }
    }

    @Override
    public T getRemaining()
    {
        return Collections.unmodifiableSet(workingSensors);
    }

    @Override
    public long getTime(long elapsedTime)
    {
        double nWorking = (double)workingSensors.size();
        return (long)((double)elapsedTime / ((double)nSensors - nWorking) * nWorking);
    }
}
