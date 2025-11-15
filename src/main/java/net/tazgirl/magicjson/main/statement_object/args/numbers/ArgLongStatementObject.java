package net.tazgirl.magicjson.main.statement_object.args.numbers;

import net.tazgirl.magicjson.main.statement_object.args.ArgStatementObject;
import net.tazgirl.magicjson.main.statement_object.interface_categories.ResolvesNumber;

public class ArgLongStatementObject extends ArgStatementObject<Long> implements ResolvesNumber<Long>
{
    @Override
    public void SetConstants()
    {
        identifier = "ArgLong";
        type = Long.class;
    }
}
