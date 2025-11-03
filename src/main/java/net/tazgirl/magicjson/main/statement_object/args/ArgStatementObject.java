package net.tazgirl.magicjson.main.statement_object.args;

import net.tazgirl.magicjson.main.statement_object.BaseStatementObject;

public class ArgStatementObject extends BaseStatementObject
{

    protected String value;

    @Override
    public Boolean HandleValue(Object content)
    {
        if(content instanceof String contentString)
        {
            value = contentString;
            return true;
        }

        return false;
    }
}
