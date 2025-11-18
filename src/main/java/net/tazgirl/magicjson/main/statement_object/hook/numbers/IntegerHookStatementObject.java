package net.tazgirl.magicjson.main.statement_object.hook.numbers;

public class IntegerHookStatementObject extends NumberHookStatementObject<Integer>
{
    @Override
    public void SetConstants()
    {
        identifier = "IntegerHook";
        returnTypeClass = Integer.class;
    }
}
