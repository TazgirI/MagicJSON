package net.tazgirl.magicjson.main.statement_object.compounds.or;

import net.tazgirl.magicjson.main.statement_object.compounds.CompoundStatementObject;
import net.tazgirl.magicjson.main.statement_object.interface_categories.ContainsBooleans;
import net.tazgirl.magicjson.main.statement_object.interface_categories.ResolvesBoolean;

import java.util.Map;

public class OrBooleanStatementObject extends CompoundStatementObject<ResolvesBoolean> implements ContainsBooleans, ResolvesBoolean
{

    public OrBooleanStatementObject()
    {
        identifier = "OrBool";
    }

    @Override
    public void SpreadArgs(Map<String, Object> newArgs)
    {
        super.SpreadArgs(newArgs);
        for(ResolvesBoolean object: contents)
        {
            object.SpreadArgs(args);
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
