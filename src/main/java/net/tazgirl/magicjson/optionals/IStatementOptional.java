package net.tazgirl.magicjson.optionals;

import net.tazgirl.magicjson.MJLogging;

import java.util.Map;

public interface IStatementOptional<T>
{
    OptionalValue<T> getOptional();

    default T get()
    {
        T value = getOptional().get();

        if(value == null)
        {
            MJLogging.Debug("StatementOptional returned null, using default value   " + getOptional().toString());
            value = getDefault();
        }

        return value;
    }

    default T getWithArgs(Object[] args)
    {
        T value = getOptional().getWithArgs(args);

        if(value == null)
        {
            MJLogging.Debug("StatementOptional returned null, using default value   " + getOptional().toString());
            value = getDefault();
        }

        return value;
    }

    default T getWithArgs(Map<String, Object> args)
    {
        T value = getOptional().getWithArgs(args);

        if(value == null)
        {
            MJLogging.Debug("StatementOptional returned null, using default value   " + getOptional().toString());
            value = getDefault();
        }

        return value;
    }

    default Object getRaw()
    {
        return getOptional().getRaw();
    }

    T getDefault();
}
