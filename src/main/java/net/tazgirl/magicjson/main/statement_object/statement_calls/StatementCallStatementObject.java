package net.tazgirl.magicjson.main.statement_object.statement_calls;

import net.tazgirl.magicjson.main.statement_object.BaseStatementObject;
import net.tazgirl.magicjson.main.statement_object.StatementManager;

public class StatementCallStatementObject extends BaseStatementObject
{
    String address;

    @Override
    public void SetConstants()
    {
        identifier = "StatementCall";
    }

    @Override
    public Boolean HandleValue(Object content)
    {
        if(content instanceof String string)
        {
            address = string;
            return true;
        }
        return null;
    }

    @Override
    public Object Resolve()
    {
        return null;
    }

    @Override
    public void SpreadManager(StatementManager newManager)
    {
        manager = newManager;
    }

    @Override
    public String toString()
    {
        return identifier + "( " + address + " )";
    }
}
