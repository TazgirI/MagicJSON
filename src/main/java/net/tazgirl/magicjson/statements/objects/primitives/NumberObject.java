package net.tazgirl.magicjson.statements.objects.primitives;

import net.tazgirl.magicjson.helpers.NumberHandling;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import net.tazgirl.magicjson.statements.objects.numeric_evaluators.NumericEvaluatorBase;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

public abstract class NumberObject<T extends Number> extends Base
{
    T value;
    Class<? extends Number> typeClass;

    public NumberObject(StatementHolder holder)
    {
        super(holder);
        typeClass = setType();
    }

    protected abstract Class<? extends Number> setType();

    @Override
    public T Resolve()
    {
        return value;
    }

    @Override
    public Class<?>[] SoftResolve()
    {
        return new Class[]{typeClass};
    }

    @Override
    public @NotNull Boolean HandleValue(Object object)
    {
        if(object instanceof Number number)
        {
            value = (T) NumberHandling.getNumberAsType(typeClass, number);
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
    public String toString()
    {
        return value.toString() + identifier.toLowerCase().charAt(0);
    }
}
