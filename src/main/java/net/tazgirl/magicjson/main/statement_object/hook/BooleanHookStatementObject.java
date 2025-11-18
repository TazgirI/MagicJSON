package net.tazgirl.magicjson.main.statement_object.hook;

import net.tazgirl.magicjson.main.TextSymbols;

public class BooleanHookStatementObject extends ReturnHookStatementObject<Boolean>
{

    @Override
    public Boolean ValueConvert(Object value)
    {
        if(value instanceof Boolean bool)
        {
            return bool;
        }

        if(value instanceof String string && TextSymbols.booleanTokens.get(string.toLowerCase()) instanceof Boolean bool)
        {
            return bool;
        }

        return null;
    }

    @Override
    public void SetConstants()
    {
        identifier = "BoolHook";
        returnTypeClass = Boolean.class;
    }
}
