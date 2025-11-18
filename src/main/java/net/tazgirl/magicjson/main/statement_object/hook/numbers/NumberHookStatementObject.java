package net.tazgirl.magicjson.main.statement_object.hook.numbers;

import net.tazgirl.magicjson.main.statement_object.hook.ReturnHookStatementObject;

public abstract class NumberHookStatementObject<R extends Number> extends ReturnHookStatementObject<R>
{

    @Override
    public R ValueConvert(Object value)
    {
        if(value instanceof Number number)
        {
            return AttemptNumberConversion(returnTypeClass, number);
        }

        return null;
    }
}
