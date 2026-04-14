package net.tazgirl.magicjson.optionals;

import net.tazgirl.magicjson.MJLogging;

import java.util.Map;

public interface IStatementOptional<T>
{
    OptionalValue<T> getOptional();

    String identifier();

    default T get()
    {
        return getOptional().get();
    }

    default T getWithArgs(Object[] args)
    {
        return getOptional().getWithArgs(args);
    }

    default T getWithArgs(Map<String, Object> args)
    {
        return getOptional().getWithArgs(args);
    }

    default Object getRaw()
    {
        return getOptional().getRaw();
    }

    default String getString()
    {
        return identifier() + "( " + getOptional().toString() + " )";
    }
}
