package net.tazgirl.magicjson.statements.objects.advanced;

import net.tazgirl.magicjson.MJLogging;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reflection extends Base
{
    Base target;
    Base method;

    List<ReflectionParameter> parameters = null;

    public Reflection(StatementHolder holder)
    {
        super(holder);
    }

    // WARN: Wonky, apparently cannot find a method that wants a primitive but fuck it this isn't designed for general use
    // UPDATE: Added the stringToClass registry and now you can manually declare the types, including primitives
    @Override
    public Object resolve()
    {
        try
        {
            Object targetObj = target.resolve();
            String methodName = (String) method.resolve();
            if(parameters == null)
            {
                return targetObj.getClass().getMethod(methodName).invoke(targetObj);
            }
            else
            {
                ReflectionParamaterRecord[] records = parameters.stream().map(Base::resolve).toArray(ReflectionParamaterRecord[]::new);
                Object[] paramaterObjects = Arrays.stream(records).map(ReflectionParamaterRecord::object).toArray();
                Class<?>[] classes = Arrays.stream(records).map(ReflectionParamaterRecord::type).toArray(Class<?>[]::new);
                return targetObj.getClass().getMethod(methodName, classes).invoke(targetObj, paramaterObjects);
            }

        }
        catch (NoSuchMethodException | ClassCastException | InvocationTargetException | IllegalAccessException e)
        {
            MJLogging.debug("Reflection has failed with exception type \"" + e.getClass() + "\". Reflection: " + this.toString());
            return null;
        }
    }

    @Override
    public @NotNull Boolean handleBase(Base base)
    {
        if(target == null)
        {
            target = base;
        }
        else if(method == null)
        {
            method = base;
        }
        else if(base instanceof ReflectionParameter reflectionParameter)
        {
            if(parameters == null)
            {
                parameters = new ArrayList<>();
            }
            parameters.add(reflectionParameter);
        }

        return true;
    }

    @Override
    public @NotNull Boolean handleUniqueArgument(String string)
    {
        return false;
    }

    @Override
    public Base implicitChild()
    {
        return new ReflectionParameter(holder);
    }

    @Override
    public @NotNull String setIdentifier()
    {
        return "Reflection";
    }

    @Override
    public String toString()
    {
        return identifier + "( " + target + ", " + method + " )";
    }

    @Override
    public void replace(Base oldBase, Base newBase)
    {
        if(target == oldBase)
        {
            target = newBase;
        }
        else if(method == oldBase)
        {
            method = newBase;
        }
    }

    public record ReflectionParamaterRecord(Object object, Class<?> type)
    {

    }
}
