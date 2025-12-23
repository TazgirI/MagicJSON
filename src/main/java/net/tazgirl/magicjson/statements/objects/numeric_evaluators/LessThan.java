package net.tazgirl.magicjson.statements.objects.numeric_evaluators;

import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

public class LessThan extends NumericEvaluatorBase
{
    Boolean andEquals = false;

    public LessThan(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public BiFunction<Number, Number, Boolean> createEvaluator()
    {
        if(andEquals)
        {
            return (number, number2) -> number.doubleValue() <= number2.doubleValue();

        }
        else
        {
            return (number, number2) -> number.doubleValue() < number2.doubleValue();
        }
    }

    @Override
    public BiFunction<Number, Number, Boolean> createDirectionalInverseEvaluator()
    {
        if(andEquals)
        {
            return (number, number2) -> number.doubleValue() >= number2.doubleValue();

        }
        else
        {
            return (number, number2) -> number.doubleValue() > number2.doubleValue();
        }
    }

    @Override
    public @NotNull Boolean HandleUniqueArgument(String string)
    {
        if(!super.HandleUniqueArgument(string))
        {
            if(string.contains(".equal"))
            {
                andEquals = true;
                this.evaluator = createEvaluator();
                this.inverseEvaluator = createDirectionalInverseEvaluator();
                return true;
            }
            return false;
        }
        return true;
    }
}
