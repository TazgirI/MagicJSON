package net.tazgirl.magicjson.main.statement_object;

import java.util.Map;
import java.util.function.BiFunction;

public abstract class BaseStatementObject
{
    public Map<String, Object> args;

    public String identifier = "";

    public BaseStatementObject()
    {

    }

    // One at time
    public Boolean HandleValue(Object content)
    {
        return false;
    }

    public Boolean numericalTest(BiFunction<Number, Number, Boolean> test, BaseStatementObject operand, Boolean operandIsLeft)
    {
        return null;
    }

    public Object Resolve()
    {
        return null;
    }

    public void SetArgs(Map<String, Object> newArgs)
    {
        this.args = newArgs;
    }

    public void SpreadArgs(Map<String, Object> newArgs)
    {
        this.args = newArgs;
    }

    public BaseStatementObject NullCheckArgs(BaseStatementObject arg1, BaseStatementObject arg2)
    {
        return arg1 != null ? arg1 : arg2;
    }

    @Override
    public String toString()
    {
        return "null";
    }

}
