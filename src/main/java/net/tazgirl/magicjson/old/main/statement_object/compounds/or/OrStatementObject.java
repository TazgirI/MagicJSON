package net.tazgirl.magicjson.old.main.statement_object.compounds.or;

import net.tazgirl.magicjson.old.main.statement_object.BaseStatementObject;
import net.tazgirl.magicjson.old.main.statement_object.compounds.CompoundStatementObject;
import net.tazgirl.magicjson.old.main.statement_object.interface_categories.ResolvesBoolean;
import net.tazgirl.magicjson.old.main.statement_object.interface_categories.ResolvesObject;

public class OrStatementObject extends CompoundStatementObject implements ResolvesBoolean
{
    @Override
    public void SetConstants()
    {
        identifier = "Or";
    }

    @Override
    public Boolean Resolve()
    {
        for(BaseStatementObject statementObject: contents)
        {
            if(statementObject instanceof ResolvesBoolean resolvesBoolean && resolvesBoolean.Resolve()){return true;}
            if(statementObject instanceof ResolvesObject && statementObject.Resolve() instanceof Boolean bool && bool){return true;}
        }
    }
}
