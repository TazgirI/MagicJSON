package net.tazgirl.magicjson.old.main.statement_object.compounds.and;

import net.tazgirl.magicjson.old.main.statement_object.BaseStatementObject;
import net.tazgirl.magicjson.old.main.statement_object.interface_categories.ContainsNumbers;
import net.tazgirl.magicjson.old.main.statement_object.interface_categories.NumericalEvaluator;

import java.util.function.BiFunction;

public class AndNumericalStatementObject extends AndStatementObject implements ContainsNumbers, NumericalEvaluator
{

    // An incredibly, incredibly slightly faster varient of And for if you know it will only be used for Numbers

    @Override
    public void SetConstants()
    {
        identifier = "AndNum";
    }

    @Override
    public Boolean numericalTest(BiFunction<Number, Number, Boolean> test, BaseStatementObject operand, Boolean operandIsLeft)
    {
        for(BaseStatementObject statementObject: this.contents)
        {
            if(!statementObject.numericalTest(test, operand, operandIsLeft)){return false;}
        }

        return true;
    }

    @Override
    public Boolean Resolve()
    {
        return null;
    }
}
