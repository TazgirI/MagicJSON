package net.tazgirl.magicjson.optionals.numbers;

import net.tazgirl.magicjson.PrivateCore;
import net.tazgirl.magicjson.optionals.IStatementOptional;
import org.jetbrains.annotations.NotNull;

public class DoubleStatementOptional extends Number implements IStatementOptional<Double>, Comparable<Double>
{
    public Object value;
    public Double defaultValue;

    public DoubleStatementOptional(Object value, @NotNull Double defaultValue)
    {
        this.value = value;
        this.defaultValue = defaultValue;
    }

    @Override
    public Double get()
    {
        Object tempValue = value;
        if(tempValue instanceof String string && PrivateCore.hasStatement(string))
        {
            tempValue = PrivateCore.runStatement(string);
        }

        if(tempValue instanceof Number number)
        {
            return number.doubleValue();
        }
        else
        {
            return defaultValue;
        }
    }

    @Override
    public Object getRaw()
    {
        return value;
    }

    @Override
    public int intValue()
    {
        return get().intValue();
    }

    @Override
    public long longValue()
    {
        return get().longValue();
    }

    @Override
    public float floatValue()
    {
        return get().floatValue();
    }

    @Override
    public double doubleValue()
    {
        return get();
    }

    @Override
    public int compareTo(@NotNull Double o)
    {
        return get().compareTo(o);
    }

    @Override
    public String toString()
    {
        return get().toString();
    }
}
