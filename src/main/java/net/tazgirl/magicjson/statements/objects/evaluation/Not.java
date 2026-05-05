package net.tazgirl.magicjson.statements.objects.evaluation;

import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

public class Not extends Base
{
    Base base;

    public Not(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public Object resolve()
    {
        Object result = base.resolve();
        if(result instanceof Boolean bool)
        {
            return !bool;
        }

        return null;
    }

    @Override
    public @NotNull Boolean handleBase(Base base)
    {
        this.base = base;
        return true;
    }

    @Override
    public @NotNull Boolean handleUniqueArgument(String string)
    {
        return false;
    }

    @Override
    public @NotNull String setIdentifier()
    {
        return "!";
    }

    @Override
    public String toString()
    {
        return containerString(base);
    }

    @Override
    public void replace(Base oldBase, Base newBase)
    {
        if(base == oldBase)
        {
            base = newBase;
        }
    }
}
