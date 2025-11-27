package net.tazgirl.magicjson.old.main.statement_object.args;

import net.tazgirl.magicjson.old.main.statement_object.interface_categories.ResolvesObject;

public class ArgImplicitStatementObject extends ArgStatementObject<Object> implements ResolvesObject
{
    @Override
    public void SetConstants()
    {
        identifier = "Arg";
    }
}
