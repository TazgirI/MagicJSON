package net.tazgirl.magicjson.optionals.numbers;

import net.tazgirl.magicjson.optionals.IStatementOptional;
import net.tazgirl.magicjson.optionals.OptionalValue;
import org.jetbrains.annotations.NotNull;

public class FloatStatementOptional extends NumberStatementOptional<Float>
{
    public FloatStatementOptional(OptionalValue<Float> optionalValue)
    {
        super(optionalValue);
    }

    public static FloatStatementOptional from(float value)
    {
        return new FloatStatementOptional(OptionalValue.from(value));
    }

    @Override
    public Float get()
    {
        return super.get();
    }

    @Override
    public String identifier()
    {
        return "FloatStatementOptional";
    }
}
