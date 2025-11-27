package net.tazgirl.magicjson.statements.objects.primitives;

import net.tazgirl.magicjson.helpers.NumberHandling;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;

public abstract class NumberObject<T extends Number> extends Base
{
    T value;
    Class<? extends Number> typeClass;

    public NumberObject(StatementHolder holder)
    {
        super(holder);
        typeClass = setType();
    }

    protected abstract Class<? extends Number> setType();

    @Override
    public Object Resolve()
    {
        return null;
    }

    @Override
    public Class<?>[] SoftResolve()
    {
        return new Class[]{Number.class};
    }

    @Override
    public Boolean HandleValue(Object object)
    {
        if(object instanceof Number number)
        {
            value = (T) NumberHandling.getNumberAsType(typeClass, number);
        }
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
    public String toString()
    {
        return value.toString() + identifier.charAt(0);
    }
}
