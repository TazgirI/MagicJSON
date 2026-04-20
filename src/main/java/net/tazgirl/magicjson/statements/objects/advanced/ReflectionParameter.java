package net.tazgirl.magicjson.statements.objects.advanced;

import net.tazgirl.magicjson.MJLogging;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

public class ReflectionParameter extends Base
{
    Base object;
    Base type;

    public ReflectionParameter(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public Object resolve()
    {
        Object typeObj = type.resolve();
        if(typeObj instanceof Class<?> typeClass)
        {
            return new Reflection.ReflectionParamaterRecord(object.resolve(), typeClass);
        }

        MJLogging.debug("ReflectionParameter failed to build record: " + this);
        return null;
    }

    @Override
    public @NotNull Boolean handleBase(Base base)
    {
        if(object == null)
        {
            object = base;
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
        return "ReflectionParamater";
    }

    @Override
    public String toString()
    {
        return identifier + "( " + object + ", " + type + " )";
    }

    @Override
    public void replace(Base oldBase, Base newBase)
    {
        if(object == oldBase)
        {
            object = newBase;
        }
        else if(type == oldBase)
        {
            type = newBase;
        }
    }
}
