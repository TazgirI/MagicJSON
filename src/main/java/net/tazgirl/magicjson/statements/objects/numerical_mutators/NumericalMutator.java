package net.tazgirl.magicjson.statements.objects.numerical_mutators;

import net.tazgirl.magicjson.helpers.NumberHandling;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import net.tazgirl.magicjson.statements.objects.interface_tags.ResolveNumber;
import net.tazgirl.magicjson.statements.objects.interface_tags.Will;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public abstract class NumericalMutator extends Base implements Will, ResolveNumber
{
    Class<? extends Number> prefferedReturnType;

    List<Base> values = new ArrayList<>();

    BiFunction<Number, Number, Number> function;

    public NumericalMutator(StatementHolder holder)
    {
        super(holder);
        function = initMyFunction();
    }

    abstract BiFunction<Number, Number, Number> initMyFunction();

    @Override
    public Number Resolve()
    {
        return null;
    }

    @Override
    public Boolean HandleValue(Object object)
    {
        if(object instanceof Base base)
        {
            values.add(base);
            return true;
        }
        return false;
    }

    @Override
    public Boolean HandleUniqueArgument(Object object)
    {
        if(object instanceof Class<?> objectClass && NumberHandling.requestedTypeToFunction.containsKey(objectClass))
        {
            prefferedReturnType = (Class<? extends Number>) objectClass;
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
    public String setIdentifier()
    {
        return "";
    }

    @Override
    public String toString()
    {
        return "";
    }

    @Override
    public Class<?>[] SoftResolve()
    {
        return new Class[0];
    }
}
