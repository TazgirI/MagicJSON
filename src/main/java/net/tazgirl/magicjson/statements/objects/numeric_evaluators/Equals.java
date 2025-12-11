package net.tazgirl.magicjson.statements.objects.numeric_evaluators;

import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import net.tazgirl.magicjson.statements.objects.compounds.CompoundBase;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class Equals extends NumericEvaluatorBase
{
    //BiFunction<Number, Number, Boolean> function = (number, number2) -> number.doubleValue() == number2.doubleValue();

    public Equals(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public BiFunction<Number, Number, Boolean> createEvaluator()
    {
        return (number, number2) -> number.doubleValue() == number2.doubleValue();
    }

    @Override
    public BiFunction<Number, Number, Boolean> createDirectionalInverseEvaluator()
    {
        return (number, number2) -> number.doubleValue() == number2.doubleValue();
    }
}
