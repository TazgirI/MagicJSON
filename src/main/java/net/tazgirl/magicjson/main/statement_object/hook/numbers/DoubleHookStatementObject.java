package net.tazgirl.magicjson.main.statement_object.hook.numbers;

public class DoubleHookStatementObject extends NumberHookStatementObject<Double>
{
    @Override
    public void SetConstants()
    {
        identifier = "DoubleHook";
        returnTypeClass = Double.class;
    }
}
