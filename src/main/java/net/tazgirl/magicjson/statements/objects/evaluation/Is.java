package net.tazgirl.magicjson.statements.objects.evaluation;

import net.tazgirl.magicjson.statements.StatementInputVarianceHandler;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Is extends Base
{
    Base target;
    Base type;

    boolean exact = false;

    public Is(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public Object resolve()
    {
        Class<?> classToCheck = type.resolve().getClass();

        if(exact)
        {
            return classToCheck == target.resolve().getClass();
        }

        return classToCheck.isInstance(target.resolve());
    }

    @Override
    public @NotNull Boolean handleBase(Base base)
    {
        if(target == null)
        {
            target = base;
            return true;
        }
        if(type == null)
        {
            type = base;
            return true;
        }

        return false;
    }

    @Override
    public @NotNull Boolean handleUniqueArgument(String string)
    {
        if(string.equals("exact"))
        {
            exact = true;
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
        return "Is";
    }

    @Override
    public String toString()
    {
        return identifier + "( " + target + ", " + type + " )";
    }

    @Override
    public void replace(Base oldBase, Base newBase)
    {
        if(target == oldBase)
        {
            target = newBase;
        }
        else if(type == oldBase)
        {
            type = newBase;
        }
    }
}
