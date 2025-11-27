package net.tazgirl.magicjson.old.main.statement_object.args;

import net.tazgirl.magicjson.old.main.statement_object.interface_categories.ResolvesString;

public class ArgStringStatementObject extends ArgStatementObject<String> implements ResolvesString
{
    @Override
    public void SetConstants()
    {
        identifier = "ArgString";
        type = String.class;
    }
}
