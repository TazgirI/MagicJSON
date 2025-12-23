package net.tazgirl.magicjson.statements.objects.numerical_mutators;

import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

public class Subtract extends NumericalMutator
{
    public Subtract(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public @NotNull String setIdentifier()
    {
        return "Sub";
    }

    @Override
    BiFunction<Number, Number, Number> initMyFunction()
    {
        return(number, number2) -> number.doubleValue() - number2.doubleValue();
    }
}
