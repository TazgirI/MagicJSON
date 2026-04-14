package net.tazgirl.magicjson.optionals.numbers;

import net.tazgirl.magicjson.optionals.IStatementOptional;
import net.tazgirl.magicjson.optionals.OptionalValue;
import org.jetbrains.annotations.NotNull;

public class IntegerStatementOptional extends NumberStatementOptional<Integer>
{
    public IntegerStatementOptional(OptionalValue<Integer> optionalValue)
    {
        super(optionalValue);
    }

    public static IntegerStatementOptional from(int value)
    {
        return new IntegerStatementOptional(OptionalValue.from(value));
    }

    @Override
    public Integer get()
    {
        return super.get();
    }

    @Override
    public String identifier()
    {
        return "IntegerStatementOptional";
    }
}
