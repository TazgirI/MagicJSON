package net.tazgirl.magicjson.statements.hooks.base;

import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

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
    public Boolean HandleBase(Base base)
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


        return false;
    }

    @Override
    public @NotNull Boolean HandleUniqueArgument(String string)
    {
        return false;
    }

    @Override
    public Base ImplicitChild()
    {
        return null;
    }

    @Override
    public @NotNull String setIdentifier()
    {
        return "HookArgument";
    }

    @Override
    public String toString()
    {
        return identifier + "( " + name.toString() + "  " + value.toString() + " )";
    }

    @Override
    public void Replace(Base oldBase, Base newBase)
    {
        if(name == oldBase)
        {
            name = newBase;
        }
        else if(value == oldBase)
        {
            value = newBase;
        }
    }
}
