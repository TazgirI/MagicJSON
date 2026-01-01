package net.tazgirl.magicjson.optionals.numbers;

import net.tazgirl.magicjson.PrivateCore;
import net.tazgirl.magicjson.optionals.IStatementOptional;
import org.jetbrains.annotations.NotNull;

public class FloatStatementOptional extends Number implements IStatementOptional<Float>, Comparable<Float>
{
    public Object value;
    public Float defaultValue;

    public FloatStatementOptional(Object value, @NotNull Float defaultValue)
    {
        this.value = value;
        this.defaultValue = defaultValue;
    }

    @Override
    public Float get()
    {
        Object tempValue = value;
        if(tempValue instanceof String string && PrivateCore.hasStatement(string))
        {
            tempValue = PrivateCore.runStatement(string);
        }

        if(tempValue instanceof Number number)
        {
            return number.floatValue();
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
        return get();
    }

    @Override
    public double doubleValue()
    {
        return get().doubleValue();
    }

    @Override
    public int compareTo(@NotNull Float o)
    {
        return get().compareTo(o);
    }
}
