package net.tazgirl.magicjson.statements.objects.primitives;

import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;

public class BooleanObject extends Base
{
    Boolean value;

    public BooleanObject(StatementHolder holder)
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
        return "Boolean";
    }

    @Override
    public String toString()
    {
        return value.toString();
    }

    @Override
    public Class<?>[] SoftResolve()
    {
        return new Class[]{Boolean.class};
    }

}
