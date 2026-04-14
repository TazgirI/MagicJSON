package net.tazgirl.magicjson.statements;

import net.tazgirl.magicjson.statements.objects.Base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatementInputVarianceHandler
{
    public List<Object> objects = new ArrayList<>();


    public StatementInputVarianceHandler(Base... inputs)
    {
        for(Base base : inputs)
        {
            if(base != null)
            {
                objects.add(base.resolve());
            }
        }
    }

    public StatementInputVarianceHandler(List<Base> bases)
    {
        for(Base base : bases)
        {
            objects.add(base.resolve());
        }
    }

    // NOTE: Technically you could compress these to pick up multiple classes at once to reduce the amount of loops but that would probably end up beign more expensive and painful in the longrun

    public <T> T getFirstExactInstanceOfType(Class<T> type)
    {
        for(Object object : objects)
        {
            if(object.getClass() == type);
            {
                return (T) object;
            }
        }

        return null;
    }

    public <T> T getFirstInstanceOfType(Class<T> type)
    {
        for(Object object : objects)
        {
            if(type.isInstance(object))
            {
                return (T) object;
            }
        }

        return null;
    }

    public <T> List<T> exactInstancesOfType(Class<T> type)
    {
        List<T> returnList = new ArrayList<>();

        for(Object object : objects)
        {
            if(object.getClass() == type)
            {
                returnList.add((T) object);
            }
        }

        return returnList;
    }

    public <T> List<T> getInstancesOfType(Class<T> type, int limit)
    {
        List<T> returnList = new ArrayList<>();
        int found = 0;

        for(Object object : objects)
        {
            if(type.isInstance(object))
            {
                returnList.add((T) object);
                found++;
                if(found >= limit)
                {
                    return returnList;
                }
            }
        }

        return returnList;
    }

    public <T> List<T> getInstancesOfType(Class<T> type)
    {
        return getInstancesOfType(type, objects.size());
    }
}
