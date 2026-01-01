package net.tazgirl.magicjson.optionals;

import net.tazgirl.magicjson.PrivateCore;
import org.jetbrains.annotations.NotNull;

public class IntegerStatementOptional extends Number implements IStatementOptional<Integer>
{
    public Object value;
    public Integer defaultValue;

    public IntegerStatementOptional(Object value, @NotNull Integer defaultValue)
    {
        this.value = value;
        this.defaultValue = defaultValue;
    }

    @Override
    public Integer get()
    {
        Object tempValue = value;
        if(tempValue instanceof String string && PrivateCore.hasStatement(string))
        {
            tempValue = PrivateCore.runStatement(string);
        }

        if(tempValue instanceof Number number)
        {
            return number.intValue();
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
        return get();
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
}
