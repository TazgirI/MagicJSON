package net.tazgirl.magicjson.statements.objects.numeric_evaluators;

import net.tazgirl.magicjson.helpers.NumericalComparisonHandler;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.checkerframework.checker.units.qual.C;
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
    Boolean flip = false;

    public NumericEvaluatorBase(StatementHolder holder)
    {
        super(holder);

        evaluator = createEvaluator();
        inverseEvaluator = createDirectionalInverseEvaluator();
    }

//     t f C
//    t f t
//    f t f
//    F
    @Override
    public Object Resolve()
    {
        if(values.size() == 2)
        {
            // flip ? !value : value
            return flip != NumericalComparisonHandler.RunTest(values.get(0), values.get(1), this);
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
                // flip ? assumption : !assumption;
                return flip == assumption;
            }
        }
        // flip ? !assumption : assumption
        return flip != assumption;

    }

    @Override
    public @NotNull Boolean HandleBase(Base base)
    {
        values.add(base);
        return true;
    }

    @Override
    public @NotNull Boolean HandleUniqueArgument(String string)
    {
        // One of these will always be active by default but this allows for statements to be strongly typed against changes in the default case
        // ****************************************************************
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
        // ****************************************************************


        if(string.equals(".true"))
        {
            assumption = true;
            return true;
        }
        if(string.equals(".false"))
        {
            assumption = false;
            return true;
        }
        if(string.equals(".not") || string.equals(".!"))
        {
            flip = true;
            return true;
        }
        return false;
    }

    @Override
    public void Replace(Base oldBase, Base newBase)
    {
        int index = values.indexOf(oldBase);
        if(index != -1)
        {
            values.set(index, newBase);
        }
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

    public BiFunction<Number, Number, Boolean> getEvaluator()
    {
        return evaluator;
    }

    public BiFunction<Number, Number, Boolean> getDirectionalInverseEvaluator()
    {
        return inverseEvaluator;
    }

    public abstract BiFunction<Number, Number, Boolean> createEvaluator();
    public abstract BiFunction<Number, Number, Boolean> createDirectionalInverseEvaluator();
}
