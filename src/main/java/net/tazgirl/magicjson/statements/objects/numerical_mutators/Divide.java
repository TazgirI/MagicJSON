package net.tazgirl.magicjson.statements.objects.numerical_mutators;

import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

public abstract class Divide extends NumericalMutator
{
    public Divide(StatementHolder holder)
    {
        super(holder);
    }

    public static class Full extends Divide
    {
        public Full(StatementHolder holder)
        {
            super(holder);
        }

        @Override
        public @NotNull String setIdentifier()
        {
            return "Div";
        }

        @Override
        BiFunction<Number, Number, Number> initMyFunction()
        {
            return (number, number2) -> number.doubleValue() / number2.doubleValue();
        }
    }

    public static class Modulus extends Divide
    {
        public Modulus(StatementHolder holder)
        {
            super(holder);
        }

        @Override
        public @NotNull String setIdentifier()
        {
            return "Mod";
        }

        @Override
        BiFunction<Number, Number, Number> initMyFunction()
        {
            return (number, number2) -> number.doubleValue() % number2.doubleValue();
        }
    }

}
