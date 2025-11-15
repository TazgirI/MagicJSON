package net.tazgirl.magicjson.main.statement_object.compounds.and;

import net.tazgirl.magicjson.main.statement_object.BaseStatementObject;
import net.tazgirl.magicjson.main.statement_object.StatementManager;
import net.tazgirl.magicjson.main.statement_object.compounds.CompoundStatementObject;
import net.tazgirl.magicjson.main.statement_object.interface_categories.ContainsBooleans;
import net.tazgirl.magicjson.main.statement_object.interface_categories.ResolvesBoolean;
import net.tazgirl.magicjson.main.statement_object.interface_categories.ResolvesNumber;

import java.util.List;

public class AndBooleanStatementObject extends CompoundStatementObject<ResolvesBoolean> implements ContainsBooleans, ResolvesBoolean
{

    @Override
    public void SetConstants()
    {
        identifier = "AndBool";
    }

    @Override
    public Boolean Resolve()
    {
        for(ResolvesBoolean object: contents)
        {
            if(!object.Resolve()){return false;}
        }

        return true;
    }


}
