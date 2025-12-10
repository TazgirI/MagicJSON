package net.tazgirl.magicjson.statements.objects.primitives.numbers;

import net.tazgirl.magicjson.statements.objects.StatementHolder;
import net.tazgirl.magicjson.statements.objects.primitives.NumberObject;
import org.jetbrains.annotations.NotNull;


public class IntegerObject extends NumberObject<Integer>
{
    public IntegerObject(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    protected Class<? extends Number> setType()
    {
        return Integer.class;
    }

    @Override
    public @NotNull String setIdentifier()
    {
        return "Integer";
    }
}
