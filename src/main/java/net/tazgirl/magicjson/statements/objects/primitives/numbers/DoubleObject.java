package net.tazgirl.magicjson.statements.objects.primitives.numbers;

import net.tazgirl.magicjson.statements.objects.StatementHolder;
import net.tazgirl.magicjson.statements.objects.primitives.NumberObject;
import org.jetbrains.annotations.NotNull;

public class DoubleObject extends NumberObject<Double>
{
    public DoubleObject(StatementHolder holder)
    {
        super(holder);
    }

    public DoubleObject(StatementHolder holder, Double value)
    {
        super(holder, value);
    }

    @Override
    public @NotNull Boolean HandleUniqueArgument(String string)
    {
        return false;
    }

    @Override
    protected Class<? extends Number> setType()
    {
        return Double.class;
    }

    @Override
    public @NotNull String setIdentifier()
    {
        return "Double";
    }
}
