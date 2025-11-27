package net.tazgirl.magicjson.old.main.statement_object.args.numbers;

import net.tazgirl.magicjson.old.main.statement_object.args.ArgStatementObject;
import net.tazgirl.magicjson.old.main.statement_object.interface_categories.ResolvesNumber;

public class ArgIntStatementObject extends ArgStatementObject<Integer> implements ResolvesNumber<Integer>
{
    @Override
    public void SetConstants()
    {
        identifier = "ArgInt";
        type = Integer.class;
    }
}
