package net.tazgirl.magicjson.optionals.numbers;

import net.tazgirl.magicjson.optionals.IStatementOptional;
import net.tazgirl.magicjson.optionals.OptionalValue;
import org.jetbrains.annotations.NotNull;

public class NumberStatementOptional<T extends Number> extends Number implements IStatementOptional<T>, Comparable<T>
{
    public OptionalValue<T> optionalValue;

    public NumberStatementOptional(OptionalValue<T> optionalValue)
    {
        this.optionalValue = optionalValue;
    }

    public static <T extends Number> NumberStatementOptional<T> from(T value)
    {
        return new NumberStatementOptional<T>(OptionalValue.from(value));
    }

    @Override
    public OptionalValue<T> getOptional()
    {
        return optionalValue;
    }

    @Override
    public Object getRaw()
    {
        return optionalValue;
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

    @Override
    public int compareTo(@NotNull T o)
    {
        return Double.compare(o.doubleValue(), get().doubleValue());
    }

    @Override
    public String toString()
    {
        return get().toString();
    }
}
