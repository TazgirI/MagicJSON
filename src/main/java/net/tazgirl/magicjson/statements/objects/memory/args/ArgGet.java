package net.tazgirl.magicjson.statements.objects.memory.args;

import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

public class ArgGet extends ArgBase
{
    public Base argumentName;

    public ArgGet(StatementHolder holder)
    {
        super(holder);
    }

    public ArgGet(StatementHolder holder, Base argumentName)
    {
        super(holder);
        this.argumentName = argumentName;
    }

    @Override
    public Object Resolve()
    {
        if(argumentName.Resolve() instanceof String string)
        {
            return holder.args.get(string);
        }
        return null;
    }

    @Override
    public @NotNull Boolean HandleBase(Base base)
    {
        if(argumentName == null)
        {
            argumentName = base;
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
        return "Arg";
    }

    @Override
    public String toString()
    {
        return identifier + "( " + argumentName.toString() + " )";
    }

    @Override
    public void Replace(Base oldBase, Base newBase)
    {
        if(argumentName == oldBase)
        {
            argumentName = newBase;
        }
    }
}
