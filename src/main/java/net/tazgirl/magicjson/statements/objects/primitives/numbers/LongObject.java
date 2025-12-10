package net.tazgirl.magicjson.statements.objects.primitives.numbers;

import net.tazgirl.magicjson.statements.objects.StatementHolder;
import net.tazgirl.magicjson.statements.objects.primitives.NumberObject;
import org.jetbrains.annotations.NotNull;

public class LongObject extends NumberObject<Long>
{
    public LongObject(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    protected Class<? extends Number> setType()
    {
        return Long.class;
    }

    @Override
    public @NotNull String setIdentifier()
    {
        return "Long";
    }
}
