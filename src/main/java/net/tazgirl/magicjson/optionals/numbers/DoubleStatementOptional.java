package net.tazgirl.magicjson.optionals;

import net.tazgirl.magicjson.PrivateCore;
import org.jetbrains.annotations.NotNull;

public class LongStatementOptional extends Number implements IStatementOptional<Long>
{
    public Object value;
    public Long defaultValue;

    public LongStatementOptional(Object value, @NotNull Long defaultValue)
    {
        this.value = value;
        this.defaultValue = defaultValue;
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
}
