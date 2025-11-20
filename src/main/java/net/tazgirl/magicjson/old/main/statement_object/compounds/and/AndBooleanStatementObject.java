package net.tazgirl.magicjson.old.main.statement_object.compounds.and;

import net.tazgirl.magicjson.old.main.statement_object.BaseStatementObject;
import net.tazgirl.magicjson.old.main.statement_object.interface_categories.ContainsBooleans;
import net.tazgirl.magicjson.old.main.statement_object.interface_categories.ResolvesBoolean;

public class AndBooleanStatementObject extends AndNumericalStatementObject implements ContainsBooleans, ResolvesBoolean
{

    // A slightly faster variant of And for if you know you are only putting bools in it

    @Override
    public void SetConstants()
    {
        identifier = "AndBool";
    }

    @Override
    public Boolean Resolve()
    {
        for(BaseStatementObject statementObject: contents)
        {
            if(statementObject.Resolve() instanceof Boolean bool && !bool){return false;}
        }

        return true;
    }


}
