package net.tazgirl.magicjson.main.statement_object.args;

import net.tazgirl.magicjson.main.statement_object.interface_categories.ResolvesBoolean;

public class ArgBooleanStatementObject extends ArgStatementObject<Boolean> implements ResolvesBoolean
{
    @Override
    public void SetConstants()
    {
        identifier = "ArgBool";
        type = Boolean.class;
    }
}
