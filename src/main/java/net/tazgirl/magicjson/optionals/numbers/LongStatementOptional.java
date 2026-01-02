package net.tazgirl.magicjson.optionals.numbers;

import net.tazgirl.magicjson.PrivateCore;
import net.tazgirl.magicjson.optionals.IStatementOptional;
import org.jetbrains.annotations.NotNull;

public class LongStatementOptional extends Number implements IStatementOptional<Long>, Comparable<Long>
{
    public Object value;
    public Long defaultValue;

    public LongStatementOptional(Object value, @NotNull Long defaultValue)
    {
        this.value = value;
        this.defaultValue = defaultValue;
    }

    public static LongStatementOptional from(long value)
    {
        return new LongStatementOptional(value, 0L);
    }

    @Override
    public Long get()
    {
        Object tempValue = value;
        if(tempValue instanceof String string && PrivateCore.hasStatement(string))
        {
            tempValue = PrivateCore.runStatement(string);
        }

        if(tempValue instanceof Number number)
        {
            return number.longValue();
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
        return get();
    }

    @Override
    public float floatValue()
    {
        return get().floatValue();
    }

    @Override
    public double doubleValue()
    {
        return get().doubleValue();
    }

    @Override
    public int compareTo(@NotNull Long o)
    {
        return get().compareTo(o);
    }

    @Override
    public String toString()
    {
        return get().toString();
    }
}
