package net.tazgirl.magicjson.statements.objects.primitives.numbers;

import net.tazgirl.magicjson.statements.objects.StatementHolder;
import net.tazgirl.magicjson.statements.objects.interface_tags.ResolveNumber;
import net.tazgirl.magicjson.statements.objects.interface_tags.Will;
import net.tazgirl.magicjson.statements.objects.primitives.NumberObject;

public class DoubleObject extends NumberObject<Double> implements Will, ResolveNumber
{
    public DoubleObject(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public Boolean HandleUniqueArgument(Object object)
    {
        return null;
    }

    @Override
    protected Class<? extends Number> setType()
    {
        return Double.class;
    }

    @Override
    public String setIdentifier()
    {
        return "Double";
    }
}
