package net.tazgirl.magicjson.statements.objects.memory.args;

import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

public class ArgGet extends ArgBase
{

    public ArgGet(StatementHolder holder)
    {
        super(holder);
    }

    public ArgGet(StatementHolder holder, Base address)
    {
        super(holder);
        this.address = address;
    }

    @Override
    public Object resolve()
    {
        if(address.resolve() instanceof String string)
        {
            return holder.args.get(string);
        }
        return null;
    }

    @Override
    public @NotNull Boolean handleBase(Base base)
    {
        if(address == null)
        {
            address = base;
            return true;
        }

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
        return "Arg";
    }

    @Override
    public String toString()
    {
        return identifier + "( " + address.toString() + " )";
    }

    @Override
    public void replace(Base oldBase, Base newBase)
    {
        if(address == oldBase)
        {
            address = newBase;
        }
    }
}
