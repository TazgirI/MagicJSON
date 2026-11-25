package net.tazgirl.magicjson.statements.objects.complex_numeric;

import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

public class Absolute extends Base
{
    public Base base;

    public Absolute(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public Object resolve()
    {
        if(base.resolve() instanceof Number num)
        {
            return Math.abs(num.doubleValue());
        }

        return null;
    }

    @Override
    public @NotNull Boolean handleBase(Base base)
    {
        if(this.base == null)
        {
            this.base = base;
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
    public @NotNull String setIdentifier()
    {
        return "Absoloute";
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
