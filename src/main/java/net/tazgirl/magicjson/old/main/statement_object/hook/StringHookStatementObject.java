package net.tazgirl.magicjson.old.main.statement_object.hook;

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
