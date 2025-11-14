package net.tazgirl.magicjson.main.statement_object.compounds.and;

import net.tazgirl.magicjson.main.statement_object.BaseStatementObject;
import net.tazgirl.magicjson.main.statement_object.StatementManager;
import net.tazgirl.magicjson.main.statement_object.compounds.CompoundStatementObject;
import net.tazgirl.magicjson.main.statement_object.interface_categories.ContainsNumbers;
import net.tazgirl.magicjson.main.statement_object.interface_categories.NumericalEvaluator;
import net.tazgirl.magicjson.main.statement_object.interface_categories.ResolvesNumber;

import java.util.function.BiFunction;

public class AndNumericalStatementObject extends CompoundStatementObject<ResolvesNumber<?>> implements ContainsNumbers, NumericalEvaluator
{

    public AndNumericalStatementObject()
    {
        identifier = "AndNum";
    }

    @Override
    public Boolean numericalTest(BiFunction<Number, Number, Boolean> test, BaseStatementObject operand, Boolean operandIsLeft)
    {
        for(ResolvesNumber<?> object: this.contents)
        {
            if(!object.numericalTest(test, operand, operandIsLeft)){return false;}

        }

        return true;
    }

    @Override
    public void SpreadManager(StatementManager newManager)
    {
        super.SpreadManager(newManager);
        for(ResolvesNumber<?> object: contents)
        {
            object.SpreadManager(newManager);
        }
    }
}
