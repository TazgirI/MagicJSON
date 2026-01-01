package net.tazgirl.magicjson.optionals;

import net.tazgirl.magicjson.PrivateCore;
import org.jetbrains.annotations.NotNull;

public class StatementOptional<T>
{
    public Object value;
    public T defaultValue;

    public StatementOptional(Object value, @NotNull T defaultValue)
    {
        this.value = value;
        this.defaultValue = defaultValue;
    }

    public T get()
    {
        Object tempValue = value;
        if(tempValue instanceof String string && PrivateCore.hasStatement(string))
        {
            tempValue = PrivateCore.runStatement(string);
        }

        try
        {
            return (T) tempValue;
        }
        catch (ClassCastException e)
        {
            return defaultValue;
        }
    }

    public Object getRaw()
    {
        return value;
    }
}
