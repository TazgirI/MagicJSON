package net.tazgirl.magicjson.main.statement_object.args;

import net.tazgirl.magicjson.main.statement_object.BaseStatementObject;

public abstract class ArgStatementObject<T> extends BaseStatementObject
{

    protected String value;

    Class<T> type;

    @Override
    public Boolean HandleValue(Object content)
    {
        if(content instanceof String contentString)
        {
            value = contentString;
            return true;
        }

        return false;
    }

    @Override
    public T Resolve()
    {
        Object arg = manager.getArgs().get(value);

        if(arg.getClass().isInstance(type))
        {
            return (T) arg;
        }

        return null;
    }
}
