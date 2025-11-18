package net.tazgirl.magicjson.main.statement_object.hook;

import net.tazgirl.magicjson.main.statement_object.BaseStatementObject;
import net.tazgirl.magicjson.main.statement_object.StatementManager;
import net.tazgirl.magicjson.main.statement_object.StringStatementObject;
import net.tazgirl.magicjson.main.statement_object.interface_categories.AbstractObject;
import org.jetbrains.annotations.NotNull;

public class HookParameterStatementObject extends BaseStatementObject implements AbstractObject
{
    public String name;

    public BaseStatementObject value;


    @Override
    public void SetConstants()
    {
        identifier = "HookParameter";
    }

    @Override
    public Boolean HandleValue(@NotNull Object content)
    {
        if(content instanceof String nameString)
        {
            name = nameString;
            return true;
        }

        if(content instanceof BaseStatementObject statementObject)
        {
            value = statementObject;
            return true;
        }
        return false;
    }

    @Override
    public Object Resolve()
    {
        return null;
    }

    @Override
    public void SpreadManager(@NotNull StatementManager newManager)
    {
        manager = newManager;
    }

    @Override
    public String toString()
    {
        return identifier + "( " + name + "  " + value + " )";
    }
}
