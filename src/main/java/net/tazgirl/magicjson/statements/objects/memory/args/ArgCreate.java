package net.tazgirl.magicjson.statements.objects.memory.args;

import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

public class ArgCreate extends ArgBase
{

    Base address;

    Base initValue;

    public ArgCreate(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public Object Resolve()
    {
        if(address.Resolve() instanceof String string && !holder.args.containsKey(string))
        {
            Object value = initValue.Resolve();
            holder.args.put(string, value);
            return true;
        }

        return false;
    }

    @Override
    public @NotNull Boolean HandleBase(Base base)
    {
        if(address == null)
        {
            address = base;
            return true;
        }
        if(initValue == null)
        {
            initValue = base;
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
        return "Arg.Create";
    }

    @Override
    public String toString()
    {
        return identifier + "( " + address + " " + initValue + " )";
    }

    @Override
    public void Replace(Base oldBase, Base newBase)
    {

    }
}
