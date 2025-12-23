package net.tazgirl.magicjson.statements.objects.memory.args;

import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

public class ArgSet extends ArgBase
{

    Base address;

    Base newValue;


    public ArgSet(StatementHolder holder)
    {
        super(holder);
    }

    // Will return newValue but is best use in sequence(ArgSet() ?())
    @Override
    public Object Resolve()
    {
        Object key = address.Resolve();
        if(key instanceof String string && holder.args.containsKey(string))
        {
            Object value = newValue.Resolve();
            holder.args.replace(string, value);
            return value;
        }

        return null;
    }

    @Override
    public @NotNull Boolean HandleBase(Base base)
    {

        if(address == null)
        {
            address = base;
            return true;
        }
        if(newValue == null)
        {
            newValue = base;
            return true;
        }

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
        return "ArgSet";
    }

    @Override
    public String toString()
    {
        return identifier + "( " + address + " " + newValue + " )";
    }

    @Override
    public void Replace(Base oldBase, Base newBase)
    {
        if(address == oldBase)
        {
            address = newBase;
        }
        else if(newValue == oldBase)
        {
            newValue = newBase;
        }
    }
}
