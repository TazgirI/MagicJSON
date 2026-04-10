package net.tazgirl.magicjson.optionals.numbers;

import net.tazgirl.magicjson.PrivateCore;
import net.tazgirl.magicjson.optionals.IStatementOptional;
import net.tazgirl.magicjson.optionals.OptionalValue;
import org.jetbrains.annotations.NotNull;

public class DoubleStatementOptional extends NumberStatementOptional<Double>
{
    public DoubleStatementOptional(OptionalValue<Double> optionalValue)
    {
        super(optionalValue);
    }

    @Override
    public Double get()
    {
        return super.get();
    }
}
