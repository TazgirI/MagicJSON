package net.tazgirl.magicjson.old.main.statement_object.args.numbers;

import net.tazgirl.magicjson.old.main.statement_object.args.ArgStatementObject;
import net.tazgirl.magicjson.old.main.statement_object.interface_categories.ResolvesNumber;

public class ArgFloatStatementObject extends ArgStatementObject<Float> implements ResolvesNumber<Float>
{
    @Override
    public void SetConstants()
    {
        identifier = "ArgFloat";
        type = Float.class;
    }
}
