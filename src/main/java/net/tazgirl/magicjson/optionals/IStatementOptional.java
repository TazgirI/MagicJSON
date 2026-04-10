package net.tazgirl.magicjson.optionals;

import java.util.Map;

public interface IStatementOptional<T>
{
    OptionalValue<T> getOptional();

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
}
