package net.tazgirl.magicjson.old.main.statement_object.args;

import net.tazgirl.magicjson.old.main.statement_object.BaseStatementObject;
import net.tazgirl.magicjson.old.main.statement_object.StatementManager;
import org.jetbrains.annotations.NotNull;

public abstract class ArgStatementObject<T> extends BaseStatementObject
{

    protected String value;

    protected Class<T> type;

    @Override
    public Boolean HandleValue(@NotNull Object content)
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

    @Override
    public String toString()
    {
        return identifier + "(" + value + ")";
    }

    @Override
    public void SpreadManager(StatementManager newManager)
    {
        manager = newManager;
    }
}
