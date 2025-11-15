package net.tazgirl.magicjson.main.statement_object.args.numbers;

import net.tazgirl.magicjson.main.statement_object.args.ArgStatementObject;
import net.tazgirl.magicjson.main.statement_object.interface_categories.ResolvesNumber;

public class ArgDoubleStatementObject extends ArgStatementObject<Double> implements ResolvesNumber<Double>
{
    @Override
    public void SetConstants()
    {
        identifier = "ArgDouble";
        type = Double.class;
    }
}
