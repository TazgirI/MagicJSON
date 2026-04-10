package net.tazgirl.magicjson.optionals;

import net.tazgirl.magicjson.statements.objects.primitives.BooleanObject;
import org.jetbrains.annotations.NotNull;

public class BooleanStatementOptional implements IStatementOptional<Boolean>, Comparable<Boolean>
{
    public OptionalValue<Boolean> value;
    public Boolean defaultValue;

    public BooleanStatementOptional(OptionalValue<Boolean> value, Boolean defaultValue)
    {
        this.value = value;
        this.defaultValue = defaultValue;
    }

    @Override
    public int compareTo(@NotNull Boolean o)
    {
        return 0;
    }

    @Override
    public Object getRaw()
    {
        return null;
    }
}
