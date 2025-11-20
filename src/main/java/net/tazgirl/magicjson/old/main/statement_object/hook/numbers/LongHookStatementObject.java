package net.tazgirl.magicjson.old.main.statement_object.hook.numbers;

public class LongHookStatementObject extends NumberHookStatementObject<Long>
{
    @Override
    public void SetConstants()
    {
        identifier = "LongHook";
        returnTypeClass = Long.class;
    }
}
