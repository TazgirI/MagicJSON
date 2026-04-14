package net.tazgirl.magicjson.optionals;

import net.tazgirl.magicjson.PrivateCore;
import net.tazgirl.magicjson.optionals.tests.ResultTest;
import org.jetbrains.annotations.NotNull;

public class StatementOptional<T> implements IStatementOptional<T>
{
    public OptionalValue<T> optionalValue;

    public StatementOptional(OptionalValue<T> optionalValue)
    {
        this.optionalValue = optionalValue;
    }

    public StatementOptional(T value)
    {
        this.optionalValue = OptionalValue.from(value);
    }

    public StatementOptional(String string, ResultTest<T> test)
    {
        this.optionalValue = OptionalValue.from(string, test);
    }

    @Override
    public OptionalValue<T> getOptional()
    {
        return optionalValue;
    }

    public T get()
    {
        return optionalValue.get();
    }

    public Object getRaw()
    {
        return optionalValue.getRaw();
    }

    @Override
    public String identifier()
    {
        return "TypeTStatementOptional";
    }

    @Override
    public String toString()
    {
        return getString();
    }
}
