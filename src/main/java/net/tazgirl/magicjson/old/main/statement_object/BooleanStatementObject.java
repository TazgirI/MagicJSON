package net.tazgirl.magicjson.old.main.statement_object;

import net.tazgirl.magicjson.old.main.statement_object.interface_categories.PrimitiveObject;
import net.tazgirl.magicjson.old.main.statement_object.interface_categories.ResolvesBoolean;

public class BooleanStatementObject extends BaseStatementObject implements ResolvesBoolean, PrimitiveObject
{

    Boolean value;

    public BooleanStatementObject(Boolean value)
    {
        this.value = value;
    }

    @Override
    public Boolean Resolve()
    {
        return value;
    }

    @Override
    public void SetConstants()
    {

    }

    @Override
    public Boolean HandleValue(Object content)
    {
        if(content instanceof Boolean contentBoolean)
        {
            value = contentBoolean;
            return true;
        }

        return false;
    }

    @Override
    public void SpreadManager(StatementManager newManager)
    {
        manager = newManager;
    }

    @Override
    public String toString()
    {
        return "BooleanObject( " + value + " )";
    }
}
