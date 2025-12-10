package net.tazgirl.magicjson.statements.objects.numeric_evaluators;

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

    public NumericEvaluatorBase(StatementHolder holder)
    {
        super(holder);

        evaluator = createEvaluator();
        inverseEvaluator = createDirectionalInverseEvaluator();
    }

    @Override
    public Object Resolve()
    {
        boolean returnValue = true;
        for(int i = 0; i < values.size() - 1; i++)
        {
            if(!values.get(i).NumericalTest(values.get(i + 1), this, false))
            {
                returnValue = false;
                if(breakOnFindFalse){return returnValue;}
            }
        }


        return returnValue;
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
