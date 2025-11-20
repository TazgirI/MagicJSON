package net.tazgirl.magicjson.old.main.statement_object.compounds.or;

import net.tazgirl.magicjson.old.main.statement_object.BaseStatementObject;
import net.tazgirl.magicjson.old.main.statement_object.compounds.CompoundStatementObject;
import net.tazgirl.magicjson.old.main.statement_object.interface_categories.ContainsNumbers;
import net.tazgirl.magicjson.old.main.statement_object.interface_categories.NumericalEvaluator;
import net.tazgirl.magicjson.old.main.statement_object.numbers.objects.NumberStatementObject;

import java.util.function.BiFunction;

public class OrNumericalStatementObject extends CompoundStatementObject<NumberStatementObject<?>> implements ContainsNumbers, NumericalEvaluator
{



    @Override
    public void SetConstants()
    {
        identifier = "OrNum";
    }

    @Override
    public Boolean numericalTest(BiFunction<Number, Number, Boolean> test, BaseStatementObject operand, Boolean operandIsLeft)
    {
        for(NumberStatementObject<?> object: this.contents)
        {
            if(object.numericalTest(test, operand, operandIsLeft)){return true;}
        }

        return false;
    }
}
