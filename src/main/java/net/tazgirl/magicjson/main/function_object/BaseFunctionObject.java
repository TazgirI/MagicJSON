package net.tazgirl.magicjson.main.function_object;

import java.util.Map;

public class BaseFunctionObject
{
    public Map<String, Object> args;

    public Object Resolve()
    {
        return null;
    }

    public void SpreadArgs(Map<String, Object> newArgs)
    {
        args = newArgs;
    }

    public Boolean HandleValue(Object content, HandleValueKey key)
    {
        return false;
    }

    public enum HandleValueKey
    {
        CHECK_STATEMENT,
        TRUE,
        FALSE
    }

}
