package net.tazgirl.magicjson.main.statement_object.numbers.comparison_objects;

import net.tazgirl.magicjson.main.statement_object.BaseStatementObject;
import net.tazgirl.magicjson.main.statement_object.interface_categories.NumericalEvaluator;
import net.tazgirl.magicjson.main.statement_object.interface_categories.ResolvesBoolean;

import java.util.Map;
import java.util.function.BiFunction;

public abstract class NumberComparisonStatementObject extends BaseStatementObject implements ResolvesBoolean, NumericalEvaluator
{
    public BaseStatementObject leftOperand = null;
    public BaseStatementObject rightOperand = null;


    public BiFunction<Number, Number, Boolean> test;



    // This doesn't even attempt to check types, will fail loud later so shouldn't matter as any issue type checks that could have caught will be caught in testing
    @Override
    public Boolean HandleValue(Object content)
    {
        if(content instanceof BaseStatementObject contentBaseStatementObject)
        {
            if(leftOperand == null)
            {
                leftOperand = contentBaseStatementObject;
                return true;
            }
            if(rightOperand == null)
            {
                rightOperand = contentBaseStatementObject;
                return true;
            }
        }

        return false;
    }

    @Override
    public Boolean Resolve()
    {
        return rightOperand.numericalTest(test, leftOperand, true);
    }

    @Override
    public void SpreadArgs(Map<String, Object> newArgs)
    {
        super.SpreadArgs(newArgs);

        leftOperand.SpreadArgs(newArgs);
        rightOperand.SpreadArgs(newArgs);
    }

    @Override
    public String toString()
    {
        return identifier + "( " + leftOperand.toString() + " " + rightOperand.toString() + " )";
    }
}
