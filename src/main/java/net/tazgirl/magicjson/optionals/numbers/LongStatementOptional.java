package net.tazgirl.magicjson.optionals.numbers;

import net.tazgirl.magicjson.PrivateCore;
import net.tazgirl.magicjson.optionals.IStatementOptional;
import net.tazgirl.magicjson.optionals.OptionalValue;
import org.jetbrains.annotations.NotNull;

public class LongStatementOptional extends NumberStatementOptional<Long>
{
    public LongStatementOptional(OptionalValue<Long> optionalValue, @NotNull Long defaultValue)
    {
        super(optionalValue, defaultValue);
    }

    @Override
    public Long get()
    {
        return super.get().longValue();
    }
}
