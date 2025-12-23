package net.tazgirl.magicjson.statements.objects.flow;

import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

public class If extends Base
{

    Base check;

    Base trueBase;
    Base falseBase;

    public If(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public Object Resolve()
    {
        if(check.Resolve() instanceof Boolean bool)
        {
            if(bool && trueBase != null)
            {
                return trueBase.Resolve();
            }
            else if(falseBase != null)
            {
                return falseBase.Resolve();
            }

            return bool;
        }

        return null;
    }

    @Override
    public @NotNull Boolean HandleBase(Base base)
    {
        if(check == null)
        {
            check = base;
            return true;
        }
        if(trueBase == null)
        {
            trueBase = base;
            return true;
        }
        if(falseBase == null)
        {
            falseBase = base;
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
        return "If";
    }

    @Override
    public String toString()
    {
        return identifier + "( " + check + "true( " + trueBase + " ) " + "false( " + falseBase + " ))";
    }

    @Override
    public void Replace(Base oldBase, Base newBase)
    {
        if(check == oldBase)
        {
            check = newBase;
        }
        else if(trueBase == oldBase)
        {
            trueBase = newBase;
        }
        else if(falseBase == oldBase)
        {
            falseBase = newBase;
        }
    }
}
