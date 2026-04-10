package net.tazgirl.magicjson.optionals.numbers;

import net.tazgirl.magicjson.PrivateCore;
import net.tazgirl.magicjson.optionals.IStatementOptional;
import net.tazgirl.magicjson.optionals.OptionalValue;
import org.jetbrains.annotations.NotNull;

public class DoubleStatementOptional extends NumberStatementOptional<Double>
{
    public DoubleStatementOptional(OptionalValue<Double> optionalValue, @NotNull Number defaultValue)
    {
        super(optionalValue, defaultValue);
    }

    @Override
    public Double get()
    {
        return super.get().doubleValue();
    }
}
