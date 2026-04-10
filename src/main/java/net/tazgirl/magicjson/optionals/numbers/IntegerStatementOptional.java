package net.tazgirl.magicjson.optionals.numbers;

import net.tazgirl.magicjson.optionals.IStatementOptional;
import net.tazgirl.magicjson.optionals.OptionalValue;
import org.jetbrains.annotations.NotNull;

public class IntegerStatementOptional extends NumberStatementOptional<Integer>
{
    public IntegerStatementOptional(OptionalValue<Integer> optionalValue, @NotNull Number defaultValue)
    {
        super(optionalValue, defaultValue);
    }

    @Override
    public Integer get()
    {
        return super.get().intValue();
    }
}
