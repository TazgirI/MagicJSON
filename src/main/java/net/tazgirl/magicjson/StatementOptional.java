package net.tazgirl.magicjson;

import org.jetbrains.annotations.NotNull;

public class StatementOptional<T>
{
    Object value;
    T defaultValue;

    public StatementOptional(T value, @NotNull T defaultValue)
    {
        this.value = value;
        this.defaultValue = defaultValue;
    }

    public T get()
    {
        if(value instanceof String string && PrivateCore.hasStatement(string))
        {
            Object statementResult = PrivateCore.runStatement(string);
            try
            {
                return statementResult != null ? (T) statementResult : defaultValue;
            }
            catch (ClassCastException e)
            {
                return defaultValue;
            }

        }

        return value != null ? (T) value : defaultValue;
    }


}
