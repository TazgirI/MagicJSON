package net.tazgirl.magicjson.statements.objects.numeric_evaluators;

import net.tazgirl.magicjson.statements.objects.StatementHolder;

import java.util.function.BiFunction;

public class NumericEquals extends NumericEvaluatorBase
{
    //BiFunction<Number, Number, Boolean> function = (number, number2) -> number.doubleValue() == number2.doubleValue();

    public NumericEquals(StatementHolder holder)
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
