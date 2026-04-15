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
    public Map.Entry<String, Base> resolve()
    {
        return name.resolve() instanceof String string ? Map.entry(string,value) : null;
    }

    @Override
    public @NotNull Boolean handleBase(Base base)
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
    public @NotNull Boolean handleUniqueArgument(String string)
    {
        return false;
    }

    @Override
    public Base implicitChild()
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
        String nameString = name == null ? "null" : name.toString();
        String valueString = value == null ? "null" : value.toString();
        return identifier + "( " + nameString + "  " + valueString + " )";
    }

    @Override
    public void replace(Base oldBase, Base newBase)
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
