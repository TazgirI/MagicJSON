package net.tazgirl.magicjson.main.statement_object.compounds.or;

import net.tazgirl.magicjson.main.statement_object.StatementManager;
import net.tazgirl.magicjson.main.statement_object.compounds.CompoundStatementObject;
import net.tazgirl.magicjson.main.statement_object.interface_categories.ContainsBooleans;
import net.tazgirl.magicjson.main.statement_object.interface_categories.ResolvesBoolean;

public class OrBooleanStatementObject extends CompoundStatementObject<ResolvesBoolean> implements ContainsBooleans, ResolvesBoolean
{

    public OrBooleanStatementObject()
    {
        identifier = "OrBool";
    }

    @Override
    public void SpreadManager(StatementManager newManager)
    {
        super.SpreadManager(newManager);
        for(ResolvesBoolean object: contents)
        {
            object.SpreadManager(newManager);
        }
    }

    @Override
    public Boolean Resolve()
    {
        for(ResolvesBoolean object: contents)
        {
            if(object.Resolve())
            {
                return true;
            }
        }

        return false;
    }
}
