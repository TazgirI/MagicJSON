package net.tazgirl.magicjson.registration.registers.base;

import java.util.ArrayList;
import java.util.List;

public abstract class ListRegister<T> extends Register<List<T>>
{
    protected final List<T> register = new ArrayList<>();

    public void add(T value)
    {
        register.add(value);
    }

    public void add(int index, T value)
    {
        register.add(index, value);
    }

    public T get(int index)
    {
        return register.get(index);
    }

    public int size()
    {
        return register.size();
    }

    public boolean contains(T value)
    {
        return register.contains(value);
    }

    @Override
    public void putEventOutput(List<T> eventOutput)
    {
        register.addAll(eventOutput);
    }
}
