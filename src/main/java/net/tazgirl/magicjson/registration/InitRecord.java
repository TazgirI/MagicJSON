package net.tazgirl.magicjson.registration;

import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;

import java.lang.reflect.InvocationTargetException;

public record InitRecord(Class<? extends Base> initClass, Object initArgument)
{
    public Base init(StatementHolder holder)
    {
        try
        {
            Base returnObject = initClass.getDeclaredConstructor(StatementHolder.class).newInstance(holder);
            returnObject.HandleValue(initArgument);
            return returnObject;
        }
        catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException | InstantiationException e)
        {
            throw new RuntimeException(e);
        }
    }
}
