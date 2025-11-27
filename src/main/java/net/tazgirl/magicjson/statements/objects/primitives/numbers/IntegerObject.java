package net.tazgirl.magicjson.statements.objects.primitives.numbers;

import net.tazgirl.magicjson.statements.objects.StatementHolder;
import net.tazgirl.magicjson.statements.objects.interface_tags.ResolveNumber;
import net.tazgirl.magicjson.statements.objects.interface_tags.Will;
import net.tazgirl.magicjson.statements.objects.primitives.NumberObject;

public class IntegerObject extends NumberObject<Integer> implements Will, ResolveNumber
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
    public String setIdentifier()
    {
        return "Integer";
    }
}
