package net.tazgirl.magicjson.main.statement_object;

import net.tazgirl.magicjson.main.statement_object.interface_categories.PrimitiveObject;
import net.tazgirl.magicjson.main.statement_object.interface_categories.ResolvesString;

public class StringStatementObject extends BaseStatementObject implements ResolvesString, PrimitiveObject
{
    String value = "";


    @Override
    public void SetConstants()
    {
        identifier = "String";
    }

    @Override
    public Boolean HandleValue(Object content)
    {
        value = content.toString();

        return true;
    }

    @Override
    public String Resolve()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return identifier + "(" + value + ")";
    }
}
