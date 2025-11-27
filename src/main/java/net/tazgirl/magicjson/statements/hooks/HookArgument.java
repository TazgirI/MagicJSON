package net.tazgirl.magicjson.statements.hooks;

import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;

import java.util.Map;

public class HookArgument extends Base
{
    Base name;

    Base value;

    public HookArgument(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public Map.Entry<String, Base> Resolve()
    {
        return name.Resolve() instanceof String string ? Map.entry(string,value) : null;
    }

    @Override
    public Boolean HandleValue(Object object)
    {
        if(object instanceof Base base)
        {
            if(name == null)
            {
                name = base;
                return true;
            }
            if(value == null)
            {
                value = base;
                return true;
            }
        }

        return false;
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
        return "HookArgument";
    }

    @Override
    public String toString()
    {
        return identifier + "( " + name.toString() + "  " + value.toString() + " )";
    }

    @Override
    public Class<?>[] SoftResolve()
    {
        return null;
    }
}
