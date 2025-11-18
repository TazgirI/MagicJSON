package net.tazgirl.magicjson.main.statement_object.hook;

import net.tazgirl.magicjson.main.TextSymbols;

public class StringHookStatementObject extends ReturnHookStatementObject<String>
{

    @Override
    public String ValueConvert(Object value)
    {
        return value.toString();
    }

    @Override
    public void SetConstants()
    {
        identifier = "StringHook";
        returnTypeClass = String.class;
    }
}
