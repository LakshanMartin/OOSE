package edu.curtin.spaceprobe;

public interface Resource<T>
{
    void useUp(T amount);
    T getRemaining();
    long getTime(long elapsedTime);
}
