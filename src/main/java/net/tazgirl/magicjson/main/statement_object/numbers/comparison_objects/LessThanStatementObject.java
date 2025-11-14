package net.tazgirl.magicjson.main.statement_object.numbers.comparison_objects;

public class LessThanStatementObject extends NumberComparisonStatementObject
{

    @Override
    public void SetConstants()
    {
        test = (a, b) -> a.doubleValue() < b.doubleValue();
        identifier = "LessThan";
    }

}
