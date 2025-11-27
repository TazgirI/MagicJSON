package net.tazgirl.magicjson.statements.objects.primitives;

import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;

public class StringObject extends Base
{
    String value;

    public StringObject(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public Object Resolve()
    {
        return value;
    }

    @Override
    public Boolean HandleValue(Object object)
    {
        return null;
    }

    @Override
    public Boolean HandleUniqueArgument(Object object)
    {
        return null;
    }

    @Override
    public Base ImplicitChild()
    {
        return null;
    }

    @Override
    public String setIdentifier()
    {
        return "String";
    }

    @Override
    public String toString()
    {
        return "\"" + value + "\"";
    }

    @Override
    public Class<?>[] SoftResolve()
    {
        return new Class[]{String.class};
    }
}
