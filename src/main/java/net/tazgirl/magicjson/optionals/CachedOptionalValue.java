package net.tazgirl.magicjson.optionals;

import net.tazgirl.magicjson.optionals.tests.ResultTest;

import java.util.List;

public class CachedOptionalValue<T> extends OptionalValue<T>
{
    List<String> argValues

    protected CachedOptionalValue(String stringValue, ResultTest<T> test)
    {
        super(stringValue, test);
    }

    protected CachedOptionalValue(T plainValue)
    {
        super(plainValue);
    }
}
