package net.tazgirl.magicjson.statements.objects.numeric_evaluators;

import net.tazgirl.magicjson.helpers.NumericalComparisonHandler;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public abstract class NumericEvaluatorBase extends Base
{
    List<Base> values = new ArrayList<>();

    BiFunction<Number, Number, Boolean> evaluator;
    BiFunction<Number, Number, Boolean> inverseEvaluator;
    Boolean breakOnFindFalse = true;
    Boolean assumption = true;

    public NumericEvaluatorBase(StatementHolder holder)
    {
        super(holder);

        evaluator = createEvaluator();
        inverseEvaluator = createDirectionalInverseEvaluator();
    }

    @Override
    public Object Resolve()
    {
        if(values.size() == 2)
        {
            return NumericalComparisonHandler.RunTest(values.get(0), values.get(1), this);
        }
        if(values.size() == 1)
        {
            return values.getFirst().Resolve();
        }

        // Allows for sequantial pairs just for sequantial pairs sake i.e 12 & 3 & 4 < 12 & 5 & 6 < 9 || 5 || 1
        // Will check (12 & 3 & 4 < 12 & 5 & 6) + (12 & 5 & 6 < 9 || 5 || 1)
        // By default assumption is true so it functions as (12 & 3 & 4 < 12 & 5 & 6) AND (12 & 5 & 6 < 9 || 5 || 1)
        // Setting assumption to false makes it (12 & 3 & 4 < 12 & 5 & 6) OR (12 & 5 & 6 < 9 || 5 || 1)
        // There's no intended use case I just find it funny
        for(int i = 1; i < values.size(); i++)
        {
            if(NumericalComparisonHandler.RunTest(values.get(i-1), values.get(i), this) != assumption)
            {
                return !assumption;
            }
        }
        return assumption;

    }

    @Override
    public @NotNull Boolean HandleValue(Object object)
    {
        if(object instanceof Base base)
        {
            values.add(base);
            return true;
        }

        DebugUnHandledType(object.getClass());
        return false;
    }

    @Override
    public @NotNull Boolean HandleUniqueArgument(String string)
    {
        // One of these will always be active by default but this allows for statements to be strongly typed against changes in the default case
        if(string.equals(".break"))
        {
            breakOnFindFalse = true;
            return true;
        }
        if(string.equals(".continue"))
        {
            breakOnFindFalse = false;
            return true;
        }
        if(string.contains("true"))
        {
            assumption = true;
            return true;
        }
        if(string.contains("false"))
        {
            assumption = false;
            return true;
        }
        return false;
    }

    @Override
    public Base ImplicitChild()
    {
        return null;
    }

    @Override
    public @NotNull String setIdentifier()
    {
        return "";
    }

    @Override
    public String toString()
    {
        return "";
    }

    @Override
    public Class<?>[] SoftResolve()
    {
        return new Class[0];
    }

    public BiFunction<Number, Number, Boolean> getEvaluator()
    {
        return evaluator;
    }

    public BiFunction<Number, Number, Boolean> getDirectionalInverseEvaluator()
    {
        return inverseEvaluator;
    }

    abstract BiFunction<Number, Number, Boolean> createEvaluator();
    abstract BiFunction<Number, Number, Boolean> createDirectionalInverseEvaluator();
}
