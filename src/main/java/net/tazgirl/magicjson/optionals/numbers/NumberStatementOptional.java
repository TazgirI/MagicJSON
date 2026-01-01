package net.tazgirl.magicjson.optionals.numbers;

import net.tazgirl.magicjson.PrivateCore;
import net.tazgirl.magicjson.optionals.IStatementOptional;
import org.jetbrains.annotations.NotNull;

public class NumberStatementOptional extends Number implements IStatementOptional<Number>, Comparable<Number>
{
    public Object value;
    public Number defaultValue;

    public NumberStatementOptional(Object value, @NotNull Number defaultValue)
    {
        this.value = value;
        this.defaultValue = defaultValue;
    }

    @Override
    public Number get()
    {
        Object tempValue = value;
        if(tempValue instanceof String string && PrivateCore.hasStatement(string))
        {
            tempValue = PrivateCore.runStatement(string);
        }

        if(tempValue instanceof Number number)
        {
            return number;
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
        return get().doubleValue();
    }

    // Probably useless but kind of funny
    @Override
    public int compareTo(@NotNull Number o)
    {
        return Double.compare(o.doubleValue(), o.doubleValue());
    }
}
