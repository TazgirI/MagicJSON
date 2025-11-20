package net.tazgirl.magicjson.old.main.statement_object.hook.numbers;

public class FloatHookStatementObject extends NumberHookStatementObject<Float>
{
    @Override
    public void SetConstants()
    {
        identifier = "FloatHook";
        returnTypeClass = Float.class;
    }
}
