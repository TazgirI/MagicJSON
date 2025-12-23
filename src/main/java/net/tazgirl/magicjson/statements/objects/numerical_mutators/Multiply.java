package net.tazgirl.magicjson.statements.objects.numerical_mutators;

import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

public class Multiply extends NumericalMutator
{
    public Multiply(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public @NotNull String setIdentifier()
    {
        return "Mult";
    }

    @Override
    BiFunction<Number, Number, Number> initMyFunction()
    {
        return (number, number2) -> number.doubleValue() * number2.doubleValue();
    }

}
