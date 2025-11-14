package net.tazgirl.magicjson.main.statement_object;

import java.util.function.BiFunction;

public abstract class BaseStatementObject
{
    public String identifier = "";

    public StatementManager manager;

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

    public void SpreadManager(StatementManager newManager)
    {
        this.manager = newManager;
    }

    public BaseStatementObject NullCheckArgs(BaseStatementObject arg1, BaseStatementObject arg2)
    {
        return arg1 != null ? arg1 : arg2;
    }

    public String LogLocation()
    {
        return "Object: " + this.identifier + "\n" +
                "Statement address: " + this.manager.statementAddress.getLocalAddress();
    }

    @Override
    public String toString()
    {
        return "null";
    }

}
