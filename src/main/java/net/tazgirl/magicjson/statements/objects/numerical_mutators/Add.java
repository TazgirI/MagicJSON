package net.tazgirl.magicjson.statements.objects.numerical_mutators;

import net.tazgirl.magicjson.statements.objects.StatementHolder;

import java.util.function.BiFunction;

public class Add extends NumericalMutator
{
    public Add(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    BiFunction<Number, Number, Number> initMyFunction()
    {
        return new BiFunction<Number, Number, Number>()
        {
            @Override
            public Number apply(Number number, Number number2)
            {
                return number.doubleValue() + number2.doubleValue();
            }
        };
    }
}
