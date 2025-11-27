package net.tazgirl.magicjson.statements.objects.primitives.numbers;

import net.tazgirl.magicjson.statements.objects.StatementHolder;
import net.tazgirl.magicjson.statements.objects.interface_tags.ResolveNumber;
import net.tazgirl.magicjson.statements.objects.interface_tags.Will;
import net.tazgirl.magicjson.statements.objects.primitives.NumberObject;

public class LongObject extends NumberObject<Long> implements Will, ResolveNumber
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
    public String setIdentifier()
    {
        return "Long";
    }
}
