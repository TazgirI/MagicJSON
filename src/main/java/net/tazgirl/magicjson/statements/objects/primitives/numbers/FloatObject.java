package net.tazgirl.magicjson.statements.objects.primitives.numbers;

import net.tazgirl.magicjson.statements.objects.StatementHolder;
import net.tazgirl.magicjson.statements.objects.interface_tags.ResolveNumber;
import net.tazgirl.magicjson.statements.objects.interface_tags.Will;
import net.tazgirl.magicjson.statements.objects.primitives.NumberObject;

public class FloatObject extends NumberObject<Float> implements Will, ResolveNumber
{
    public FloatObject(StatementHolder holder)
    {
        super(holder);
    }



    @Override
    protected Class<? extends Number> setType()
    {
        return Float.class;
    }

    @Override
    public String setIdentifier()
    {
        return "Float";
    }
}
