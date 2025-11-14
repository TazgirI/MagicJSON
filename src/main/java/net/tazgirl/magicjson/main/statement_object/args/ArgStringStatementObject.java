package net.tazgirl.magicjson.main.statement_object.args;

import net.tazgirl.magicjson.main.statement_object.interface_categories.ResolvesString;

public class ArgStringStatementObject extends ArgStatementObject<String> implements ResolvesString
{


    @Override
    public void SetConstants()
    {
        identifier = "ArgString";
        type = String.class;
    }

    @Override
    public String Resolve()
    {
        return null;
    }

    @Override
    public String toString()
    {
        return "";
    }
}
