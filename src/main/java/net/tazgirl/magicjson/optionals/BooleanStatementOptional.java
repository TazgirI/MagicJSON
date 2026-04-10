package net.tazgirl.magicjson.optionals;

import net.tazgirl.magicjson.statements.objects.primitives.BooleanObject;
import org.jetbrains.annotations.NotNull;

public class BooleanStatementOptional extends StatementOptional<Boolean> implements IStatementOptional<Boolean>, Comparable<Boolean>
{
    public BooleanStatementOptional(OptionalValue<Boolean> optionalValue, @NotNull Boolean defaultValue)
    {
        super(optionalValue, defaultValue);
    }

    @Override
    public int compareTo(@NotNull Boolean o)
    {
        return get().compareTo(o);
    }

    @Override
    public OptionalValue<Boolean> getOptional()
    {
        return optionalValue;
    }
}
